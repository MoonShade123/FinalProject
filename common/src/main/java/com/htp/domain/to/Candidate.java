package com.htp.domain.to;

import com.htp.dao.CandidateDao;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Candidate implements Serializable, CandidateDao {
    private Integer candidateID;
    private String candidateName;
    private String candidateSurname;
    private String candidateEmail;
    private Integer candidatePhone;
    private String candidateInfo;

    public Candidate(Integer candidateID, String candidateName, String candidateSurname, String candidateEmail, Integer candidatePhone, String candidateInfo) {
        this.candidateID = candidateID;
        this.candidateName = candidateName;
        this.candidateSurname = candidateSurname;
        this.candidateEmail = candidateEmail;
        this.candidatePhone = candidatePhone;
        this.candidateInfo = candidateInfo;
    }

    public Candidate() {

    }

    public Integer getCandidateID() {
        return candidateID;
    }

    public Integer setCandidateID(Integer candidateID) {
        return candidateID;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateSurname() {
        return candidateSurname;
    }

    public void setCandidateSurname(String candidateSurname) {
        this.candidateSurname = candidateSurname;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public Integer getCandidatePhone() {
        return candidatePhone;
    }

    public void setCandidatePhone(Integer candidatePhone) {
        this.candidatePhone = candidatePhone;
    }

    public String getCandidateInfo() {
        return candidateInfo;
    }

    public void setCandidateInfo(String candidateInfo) {
        this.candidateInfo = candidateInfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return candidateID.equals(candidate.candidateID) &&
                candidateName.equals(candidate.candidateName) &&
                candidateSurname.equals(candidate.candidateSurname) &&
                Objects.equals(candidateEmail, candidate.candidateEmail) &&
                Objects.equals(candidatePhone, candidate.candidatePhone) &&
                Objects.equals(candidateInfo, candidate.candidateInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateID, candidateName, candidateSurname, candidateEmail, candidatePhone, candidateInfo);
    }

    @Override
    public Candidate getCandidateName(String name, String surname) throws DaoException {
        return null;
    }

    @Override
    public boolean checkCandidate(String name, String surname) throws DaoException {
        return false;
    }

    @Override
    public boolean checkCandidateId(Long id) {
        return false;
    }

    @Override
    public List<Candidate> findAll() throws DaoException, ConnectionPoolException, com.htp.exception.DaoException {
        return null;
    }

    @Override
    public Candidate findById(Long id) throws DaoException, com.htp.exception.DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws DaoException, com.htp.exception.DaoException {
        return false;
    }

    @Override
    public Long create(Candidate entity) throws DaoException, com.htp.exception.DaoException {
        return null;
    }

    @Override
    public Long update(Candidate entity) throws DaoException, com.htp.exception.DaoException {
        return null;
    }
}
