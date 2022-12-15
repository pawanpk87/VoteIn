/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoteIn.dao;

import VoteIn.dbutil.DBConnection;
import VoteIn.dto.UserDTO;
import VoteIn.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UserDAO {
    
    private static PreparedStatement ps,ps1,ps2,ps3;
    private static Statement st;
    
    static
    {
        try{
            st=DBConnection.getConnection().createStatement(); 
            
            ps=DBConnection.getConnection().prepareStatement("Select user_type from user_details where adhar_no=? and password=?");
            
            ps1=DBConnection.getConnection().prepareStatement("Delete from user_details where adhar_no=?");
            
            ps2=DBConnection.getConnection().prepareStatement("Select username,email,mobile_no,address,city from user_details where adhar_no=?");
            
            ps3=DBConnection.getConnection().prepareStatement("Update user_details set username=?,email=?,mobile_no=?,address=?,city=? where adhar_no=?");
                        
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }
    
    public static boolean updateUserById(UserDetails user) throws SQLException
    {
        ps3.setString(1, user.getUsername());
        ps3.setString(2, user.getEmailId());
        ps3.setString(3, user.getMobileNumber());
        ps3.setString(4, user.getAddress());
        ps3.setString(5,user.getCity());
        ps3.setString(6, user.getUserId());
        int count=ps3.executeUpdate();
        return count != 0;
    }
    
    public static UserDetails getUserDetailsById(String adharNo) throws SQLException
    {
        ps2.setString(1, adharNo);
        UserDetails user=new UserDetails();
        ResultSet rs=ps2.executeQuery();
        if(rs.next())
        {
            user.setUserId(adharNo);
            user.setUsername(rs.getString(1));
            user.setEmailId(rs.getString(2));
            user.setMobileNumber(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            return user;
        }
        else
            return null;
    }
    
    public static boolean deleteUserById(String adharNo) throws SQLException
    {
        ps1.setString(1, adharNo);
        int count=ps1.executeUpdate();
        return count != 0;
    }
    
     public static ArrayList<String> getUserIds()throws SQLException
     {
        ArrayList<String> userIdList=new ArrayList<>();
        ResultSet rs=st.executeQuery("Select adhar_no from user_details");
        while(rs.next())
        {
            userIdList.add(rs.getString(1));
        }
        return userIdList;
     }
    
    public static ArrayList<UserDetails> getAllUsersDetails()throws SQLException
    {
        ArrayList<UserDetails> allUsers=new ArrayList<>();
        ResultSet rs=st.executeQuery("Select username,adhar_no,email,address,mobile_no,city from user_details");
        while(rs.next())
        {
            allUsers.add(new UserDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),"nopassword"));
        }
        return allUsers;
    }
    
    public static String validateUser(UserDTO user) throws SQLException
    {
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
    
        ResultSet rs=ps.executeQuery();
        
        if(rs.next())
        {
            return rs.getString(1);
        }
        
        return null;
    }
}
