
package VoteIn.dto;

import java.util.Objects;


public class CandidateDetails {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.candidatId);
        hash = 89 * hash + Objects.hashCode(this.userId);
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.symbol);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.party);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateDetails other = (CandidateDetails) obj;
        if (!Objects.equals(this.candidatId, other.candidatId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CandidateDetails{" + "candidatId=" + candidatId + ", userId=" + userId + ", username=" + username + ", symbol=" + symbol + ", city=" + city + ", party=" + party + '}';
    }

    public String getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(String candidatId) {
        this.candidatId = candidatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
    
    public CandidateDetails() {
        
    }
    
    public CandidateDetails(String candidatId, String userId, String username, String symbol, String city, String party) {
        this.candidatId = candidatId;
        this.userId = userId;
        this.username = username;
        this.symbol = symbol;
        this.city = city;
        this.party = party;
    }
    
    private String candidatId;
    private String userId;
    private String username;
    private String symbol;
    private String city;
    private String party;
}
