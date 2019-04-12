package com.htp.dao;

import com.htp.domain.to.VoteCompany;
import com.htp.exceptions.DaoException;

import java.sql.Date;

public interface VoteCompanyDao extends GenericDAO <VoteCompany, Long> {

    VoteCompany findByCreate(Date createTime) throws DaoException, com.htp.exception.DaoException;
    VoteCompany findByEnd(Date age) throws DaoException, com.htp.exception.DaoException;

    boolean checkVoteConsigmentId(Long id);
}
