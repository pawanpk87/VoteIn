package VoteIn.dto;

public class UserDTO {

    @Override
    public String toString() {
        return "UserDTO{" + "userId=" + userId + ", password=" + password + '}';
    }

    public UserDTO(String userIdl, String password) {
        this.userId = userIdl;
        this.password = password;
    }
    
    public UserDTO() {
        
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userIdl) {
        this.userId = userIdl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String userId;
    private String password;
}
