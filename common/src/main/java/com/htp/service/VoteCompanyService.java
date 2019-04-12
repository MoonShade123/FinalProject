package com.htp.service;

import com.htp.domain.to.VoteCompany;
import com.htp.exception.ServiceException;

import java.util.List;

public interface VoteCompanyService extends GenericServiceInterface<VoteCompany, List<VoteCompany>> {

    boolean  deleteVoteCompany (Long id) throws ServiceException, com.htp.exceptions.ServiceException;

    VoteCompany updateVoteCompany (VoteCompany voteCompany) throws ServiceException, com.htp.exceptions.ServiceException;
}

