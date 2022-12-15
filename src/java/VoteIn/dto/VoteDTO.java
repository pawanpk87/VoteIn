
package VoteIn.dto;


public class VoteDTO {

    @Override
    public String toString() {
        return "VoteDTO{" + "candidateId=" + candidateId + ", voterID=" + voterID + '}';
    }
    
    public VoteDTO() {
        
    }

    public VoteDTO(String candidateId, String voterID) {
        this.candidateId = candidateId;
        this.voterID = voterID;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }
    
    private String candidateId;
    private String voterID;    
}
