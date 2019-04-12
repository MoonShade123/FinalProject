package com.htp.service;

import com.htp.domain.to.Candidate;
import com.htp.exception.ServiceException;

import java.util.List;

public interface CandidateService extends GenericServiceInterface<Candidate, List<Candidate>> {

    boolean deleteCandidate (Long id) throws ServiceException, com.htp.exceptions.ServiceException;

    Candidate updateCandidate (Candidate candidate) throws ServiceException, com.htp.exceptions.ServiceException;

}
