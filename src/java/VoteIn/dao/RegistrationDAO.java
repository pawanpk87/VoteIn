package VoteIn.dao;

import VoteIn.dbutil.DBConnection;
import VoteIn.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegistrationDAO {
    
    /*
    ps -> for checking is user present or not
    ps1-> for registration 
    */
    private static PreparedStatement ps,ps1;
    
    static
    {
        try{
            ps=DBConnection.getConnection().prepareStatement("Select * from user_details where adhar_no=?");
            
            ps1=DBConnection.getConnection().prepareStatement("Insert into user_details values(?,?,?,?,?,?,?,?)");
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }
    
    public static boolean searchUser(String userId)throws SQLException 
    {
        /*
        here userId is same as adhar number
        */
        ps.setString(1, userId);
        return ps.executeQuery().next();
    }    
    
    public static boolean registerUser(UserDetails user) throws SQLException
    {
        ps1.setString(1, user.getUserId());
        ps1.setString(2, user.getPassword());
        ps1.setString(3, user.getUsername());
        ps1.setString(4, user.getAddress());
        ps1.setString(5,user.getCity());
        ps1.setString(6, user.getEmailId());
        ps1.setString(7, user.getMobileNumber());        
        ps1.setString(8, "Voter");
        
        return ps1.executeUpdate() == 1;
    }
}
