package com.htp.domain.to;

import java.sql.Date;
import java.util.Objects;

public class VoteCompany {
    private Long voteCompany;
    private Long voteCandidateID;
    private Long voteConsigmentID;
    private Date createTime;
    private Date endTime;

    public VoteCompany(Date createTime, Date endTime) {
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public VoteCompany() {

    }

    public Long getVoteCompany() {
        return voteCompany;
    }

    public void setVoteCompany(Long voteCompany) {
        this.voteCompany = voteCompany;
    }

    public Long getVoteCandidateID() {
        return voteCandidateID;
    }

    public void setVoteCandidateID(Long voteCandidateID) {
        this.voteCandidateID = voteCandidateID;
    }

    public Long getVoteConsigmentID() {
        return voteConsigmentID;
    }

    public void setVoteConsigmentID(Long voteConsigmentID) {
        this.voteConsigmentID = voteConsigmentID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getSimpleName());
            sb.append("\n");
            sb.append("Company ID: ");
            sb.append(this.voteCompany);
            sb.append("\n");
            sb.append("Country ID: ");
            sb.append(this.voteConsigmentID);
            sb.append("\n");
            sb.append("Candidate ID: ");
            sb.append(this.voteCandidateID);
            sb.append("\n");

            return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteCompany that = (VoteCompany) o;
        return voteCompany.equals(that.voteCompany) &&
                voteCandidateID.equals(that.voteCandidateID) &&
                voteConsigmentID.equals(that.voteConsigmentID) &&
                createTime.equals(that.createTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteCompany, voteCandidateID, voteConsigmentID, createTime, endTime);
    }
}
