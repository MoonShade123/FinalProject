package com.htp.dao;

import com.htp.domain.to.Candidate;
import com.htp.exceptions.DaoException;

public interface CandidateDao extends GenericDAO<Candidate, Long> {

    Candidate getCandidateName(String name, String surname) throws DaoException;

    boolean checkCandidate(String name, String surname) throws DaoException;


    boolean checkCandidateId(Long id) throws DaoException;
}
