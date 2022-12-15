
package VoteIn.dao;

import VoteIn.dbutil.DBConnection;
import VoteIn.dto.CandidateDTO;
import VoteIn.dto.CandidateDetails;
import VoteIn.dto.CandidateInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class CandidateDAO {
     
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7;
    private static Statement st;
    
    static
    {
        try{
            
            st=DBConnection.getConnection().createStatement();            
            
            ps=DBConnection.getConnection().prepareStatement("Select max(candidate_id) from candidate");
            
            ps1=DBConnection.getConnection().prepareStatement("Select username from user_details where adhar_no=?");
            
            ps2=DBConnection.getConnection().prepareStatement("Select distinct city from user_details");
            
            ps3=DBConnection.getConnection().prepareStatement("Insert into candidate values(?,?,?,?,?)"); 
            
            ps4=DBConnection.getConnection().prepareStatement("Select * from candidate where candidate_id=?"); 
            
            ps5=DBConnection.getConnection().prepareStatement("Select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.city=(Select city from user_details where adhar_no=?)"); 
            
            ps6=DBConnection.getConnection().prepareStatement("Update candidate set party=?,city=?,symbol=? where candidate_id=?"); 
            
            ps7=DBConnection.getConnection().prepareStatement("Delete from candidate where candidate_id=?"); 
                        
        }        
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }
    
    public static boolean deleteCandidateByID(String candidateId)throws SQLException
    {
        ps7.setString(1, candidateId);
        return ps7.executeUpdate() != 0;
    }
    
    public static boolean updateCandidate(CandidateDTO candidate)throws SQLException
    {
        ps6.setString(1, candidate.getParty());
        ps6.setString(2, candidate.getCity());
        ps6.setBinaryStream(3, candidate.getSymbol());
        ps6.setString(4, candidate.getCandidateId());        
        
        return ps6.executeUpdate() != 0;
    }
    
    public static boolean addCandidate(CandidateDTO candidate)throws SQLException
    {
        ps3.setString(1, candidate.getCandidateId());
        ps3.setString(2, candidate.getParty());
        ps3.setString(3, candidate.getUserid());
        ps3.setBinaryStream(4, candidate.getSymbol());
        ps3.setString(5, candidate.getCity());
        
        return ps3.executeUpdate() != 0;
    }
    
    public static String getNextId()throws SQLException
    {
        ResultSet rs=ps.executeQuery();
        rs.next();
        String cid=rs.getString(1);
        if(cid == null)
        {
           return "C101"; 
        }
        else 
        {
            long id=Long.parseLong(cid.substring(1));
            return "C"+(id+1);
        }
    }
    
    public static String getUserNameById(String userId)throws SQLException
    {
        ps1.setString(1, userId);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
        {            
            return rs.getString(1);
        }
        else
        {
            return null;
        }
    }
    
    public static ArrayList<String> getCity()throws SQLException
    {
        ArrayList<String> cityList=new ArrayList<>();
        ResultSet rs=ps2.executeQuery();
        while(rs.next())
        {
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    
    public static ArrayList<String> getCandidateIds()throws SQLException
    {
        ArrayList<String> candidateIdList=new ArrayList<>();
        ResultSet rs=st.executeQuery("Select candidate_id from candidate");
        while(rs.next())
        {
            candidateIdList.add(rs.getString(1));
        }
        return candidateIdList;
    }    
    
    public static CandidateDetails getDetailsById(String cid)throws Exception
    {
        ps4.setString(1, cid);        
        ResultSet rs=ps4.executeQuery();        
        CandidateDetails cd=new CandidateDetails();
        /*
        convert image to base64
        Blog  ----> return InputStream object ----->  InputStream need to be convert into bytes array|
                                                                                                                     |
                                                                                                                     |
                                                                                                    base64  <-----
        
        inorder to convert InputStream to byte array
        InputStream has a method called read it takes
        array and copies the data into array based on
        the size of the array
        
        but we don't know the size of the array(image)
        
        
        Soluton:-
        
            inputStream.read(buffer)) 
            takes the image and and
            copies into buffer(array)
            and return how many bytes
            it read.
                   |
                   |
                   |
                   ^
        
                ByteArrayOutputStream has a method called writes
                which takes the array of size 4096 along with starting
                index and endind index from where it will start
                reading and writing the image into ByteArrayOutputStream obj
                 |
                 |
                 ^
        
                 ByteArrayOutputStream has a method called to byte array which
                 will return byte array
        
                 |
                 Now convert into Base64
                 |
                 |
                 ^
                 Encoder has a method called encodeToString which takes 
                 byte array and convert it into string          
        */
        
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        if(rs.next())
        {
            blob=rs.getBlob(4);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer,0,bytesRead);
            }
            
            imageBytes=outputStream.toByteArray();            
            Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);
            
            cd.setSymbol(base64Image);
            cd.setCandidatId(cid);
            cd.setCity(rs.getString(5));
            cd.setParty(rs.getString(2));
            String userid=rs.getString(3);
            cd.setUserId(userid);
            cd.setUsername(getUserNameById(userid));
        }  
        return cd;
    }
    
    public static ArrayList<CandidateInfo> viewCandidates(String adhar_no)throws Exception
    {
        ArrayList<CandidateInfo> candidateList=new ArrayList<>();
        
        ps5.setString(1, adhar_no);
        
        ResultSet rs=ps5.executeQuery();
        
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
            
        while(rs.next())
        {
            blob=rs.getBlob(4);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer,0,bytesRead);
            }
            
            imageBytes=outputStream.toByteArray();            
            Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);
            
            candidateList.add(new CandidateInfo(rs.getString(1),rs.getString(2),rs.getString(3),base64Image));
        }
        
        return candidateList;
    }
    
    public static Map<String,String> getAllParties()throws Exception
    {
        Map<String,String> candidateList=new HashMap<>();
        ResultSet rs=st.executeQuery("Select user_id,symbol from candidate where rownum<=10");
        
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        while(rs.next())
        {
             blob=rs.getBlob(2);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer,0,bytesRead);
            }
            
            imageBytes=outputStream.toByteArray();            
            Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);
            candidateList.put(getUserNameById(rs.getString(1)), base64Image);
        }
        return candidateList;
    }
}
