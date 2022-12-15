package VoteIn.dao;

import VoteIn.dbutil.DBConnection;
import VoteIn.dto.CandidateInfo;
import VoteIn.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;


public class VoteDAO {
    
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5;
    private static Statement st;
            
    static
    {
        try{
            
            ps1=DBConnection.getConnection().prepareStatement("Select candidate_id from voting where voter_id=?");
            
            ps2=DBConnection.getConnection().prepareStatement("Select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.candidate_id=?");
            
            ps3=DBConnection.getConnection().prepareStatement("Insert into voting values(?,?)");
            
            ps4=DBConnection.getConnection().prepareStatement("Select candidate_id,count(*) as votes_obt from voting group by candidate_id order by votes_obt desc");
            
            st=DBConnection.getConnection().createStatement();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }
    
    public static Map<String,Integer> getResult()throws SQLException
    {
        Map<String,Integer> result=new LinkedHashMap<>();
        ResultSet rs=ps4.executeQuery();
        while(rs.next())
        {
            String candidateID=rs.getString(1);
            int totalVotes=rs.getInt(2);
            result.put(candidateID, totalVotes);
        }
        return result;
    }
    
    public static int getVoteCount()throws SQLException
    {
        ResultSet rs=st.executeQuery("Select count(*) from voting");
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
    
    public static String getCandidateId(String adhar_no)throws SQLException
    {
        ps1.setString(1, adhar_no);
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
    
    public static CandidateInfo getVote(String candidateId)throws Exception
    {
        ps2.setString(1, candidateId);
        ResultSet rs=ps2.executeQuery();
        if(rs.next())
        {
            CandidateInfo cinfo=new CandidateInfo();
            
            Blob blob;
            InputStream inputStream;
            byte[] buffer;
            byte[] imageBytes;
            int bytesRead;
            String base64Image;
            ByteArrayOutputStream outputStream;
        
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
            
            cinfo.setCandidateId(candidateId);
            cinfo.setCandidateName(rs.getString(2));
            cinfo.setParty(rs.getString(3));
            cinfo.setSymbol(base64Image);
            
            return cinfo;
        }
        else
        {
            return null;
        }
    }
    
    public static boolean addVote(VoteDTO voteObj)throws SQLException
    {
        ps3.setString(1, voteObj.getCandidateId());
        ps3.setString(2, voteObj.getVoterID());
        
        return ps3.executeUpdate() == 1;
    }
}
