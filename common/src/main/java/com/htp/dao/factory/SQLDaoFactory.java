package com.htp.dao.factory;

import com.htp.dao.CandidateDao;
import com.htp.dao.UserDao;
import com.htp.dao.VoteCompanyDao;
import com.htp.dao.impl.SQLCandidateDao;
import com.htp.dao.impl.SQLUserDao;
import com.htp.dao.impl.SQLVoteCompanyDao;

public class SQLDaoFactory extends DaoFactory {

    private static final SQLDaoFactory instance = new SQLDaoFactory();

    private SQLDaoFactory(){}

    public static SQLDaoFactory getInstance(){
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return SQLUserDao.getInstance();
    }

    @Override
    public CandidateDao getCandidateDao() {
        return SQLCandidateDao.getInstance();
    }

    @Override
    public VoteCompanyDao getVoteCompanyDao() {
        return SQLVoteCompanyDao.getInstance();
    }
}
