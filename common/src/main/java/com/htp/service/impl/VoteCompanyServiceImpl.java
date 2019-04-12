package com.htp.service.impl;

import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.dao.VoteCompanyDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.VoteCompany;
import com.htp.exceptions.DaoException;
import com.htp.exceptions.ServiceException;
import com.htp.service.VoteCompanyService;

import java.util.List;

public class VoteCompanyServiceImpl implements VoteCompanyService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();

    private VoteCompanyServiceImpl(){}

    public static VoteCompanyService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final VoteCompanyServiceImpl instance = new VoteCompanyServiceImpl();
    }

    @Override
    public VoteCompany create(VoteCompany voteCompany) throws ServiceException {
        try {
            VoteCompanyDao voteCompanyDao = factory.getVoteCompanyDao();
            Long check = voteCompanyDao.create(voteCompany);

            if(check == 0) {
                return null;
            } else {
                long id = voteCompanyDao.create(voteCompany);
                voteCompany.setVoteConsigmentID(id);
                return voteCompany;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        } catch (com.htp.exception.DaoException e) {
            e.printStackTrace();
        }
        return voteCompany;
    }

    @Override
    public List<VoteCompany> listAll() throws ServiceException {
        List<VoteCompany> voteCompanyList;
        try {
            VoteCompanyDao voteCompanyDao = factory.getVoteCompanyDao();
            voteCompanyList = voteCompanyDao.findAll();
            return voteCompanyList;
        } catch (DaoException | ConnectionPoolException | com.htp.exception.DaoException e) {
            throw new ServiceException("SE exc", e);
        }
    }

    @Override
    public boolean deleteVoteCompany(Long id) throws ServiceException {
        try {
            VoteCompanyDao voteCompanyDao = factory.getVoteCompanyDao();
            boolean check = voteCompanyDao.checkVoteConsigmentId(id);
            if(!check) {
                return false;
            } else {
                return voteCompanyDao.delete(id);
            }
        } catch (DaoException | com.htp.exception.DaoException e) {
            throw new ServiceException("SE exc", e);
        }
    }

    @Override
    public VoteCompany updateVoteCompany(VoteCompany voteCompany) throws ServiceException {
        try {
            VoteCompanyDao voteCompanyDao = factory.getVoteCompanyDao();
            Long id = voteCompanyDao.update(voteCompany);
            return voteCompanyDao.findById(id);
        } catch (DaoException | com.htp.exception.DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }
}
