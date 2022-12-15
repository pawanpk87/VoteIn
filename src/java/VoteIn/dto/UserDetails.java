package VoteIn.dto;

public class UserDetails {

    @Override
    public String toString() {
        return "UserDetails{" + "username=" + username + ", userId=" + userId + ", emailId=" + emailId + ", address=" + address + ", mobileNumber=" + mobileNumber + ", city=" + city + ", password=" + password + '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public UserDetails(String username, String userId, String emailId, String address, String mobileNumber, String city, String password) {
        this.username = username;
        this.userId = userId;
        this.emailId = emailId;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.city = city;
        this.password = password;
    }
    
    public UserDetails() {
        
    }
    
    private String username;
    private String userId;
    private String emailId;
    private String address;
    private String mobileNumber;
    private String city;
    private String password;
}
