package com.htp.dao.factory;

import com.htp.dao.CandidateDao;
import com.htp.dao.UserDao;
import com.htp.dao.VoteCompanyDao;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }

    public abstract UserDao getUserDao();
    public abstract CandidateDao getCandidateDao();
    public abstract VoteCompanyDao getVoteCompanyDao();

}
