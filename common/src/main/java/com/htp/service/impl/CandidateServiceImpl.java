package com.htp.service.impl;

import com.htp.dao.CandidateDao;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.Candidate;
import com.htp.exceptions.DaoException;
import com.htp.exceptions.ServiceException;
import com.htp.service.CandidateService;
import com.htp.service.validator.CandidateAccountValidator;
import com.htp.service.validator.ValidationException;
import com.htp.service.validator.ValidatorInterface;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<Candidate> VALIDATE = CandidateAccountValidator.getInstance();

    private CandidateServiceImpl(){}

    public static CandidateService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final CandidateServiceImpl instance = new CandidateServiceImpl();
    }

    @Override
    public Candidate create(Candidate candidate) throws ServiceException {
        try {
            CandidateDao candidateDao = factory.getCandidateDao();
            if(VALIDATE.isValid(candidate)){
                boolean check = candidateDao.checkCandidate(candidate.getCandidateName(), candidate.getCandidateSurname());
                if (!check){
                    return candidate;
                }else {
                    return null;
                }
            }else {
                throw new ValidationException("VA exc");
            }
        }catch (DaoException | ValidationException e){
            throw new ServiceException("SE exc",e);
        }
    }

    @Override
    public List<Candidate> listAll() throws ServiceException {
        List<Candidate> candidateList;
        try {
            Candidate candidateDao = (Candidate) factory.getCandidateDao();
            candidateList = candidateDao.findAll();
            return candidateList;
        } catch (DaoException e) {
            throw new ServiceException("SE exc", e);
        } catch (com.htp.exception.DaoException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return listAll();
    }

    @Override
    public boolean deleteCandidate(Long id) throws ServiceException {
        try {
            CandidateDao candidateDao = factory.getCandidateDao();
            boolean check = candidateDao.checkCandidateId(id);
            if(!check) {
                return false;
            } else {
                return candidateDao.delete(id);
            }
        } catch (DaoException e) {
            throw new ServiceException("SE exc", e);
        } catch (com.htp.exception.DaoException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) throws ServiceException {
        try {
            CandidateDao candidateDao = factory.getCandidateDao();
            Long id = candidateDao.update(candidate);
            return candidateDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        } catch (com.htp.exception.DaoException e) {
            e.printStackTrace();
        }
        return candidate;
    }
}
