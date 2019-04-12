package com.htp.service.impl;

import com.google.protobuf.ServiceException;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.domain.to.User;
import com.htp.exceptions.DaoException;
import com.htp.exceptions.NoSuchEntityException;
import com.htp.service.UserService;
import com.htp.service.validator.LoginValidator;
import com.htp.service.validator.ValidationException;
import com.htp.service.validator.ValidatorInterface;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final DaoFactory factory = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<User> VALIDATE = LoginValidator.getInstance();

    private UserServiceImpl(){}

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public User loadById(Long userId) throws ServiceException, NoSuchEntityException {
        return null;
    }

    private static class SingletonHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }

    /**
     * Method check login and password information from some user and get user object if authorization success
     * @param user object, that provides authorization operation
     * @return null if client not exists in system; user object if authorization execute correctly
     * @throws ServiceException
     */
    @Override
    public User authorization(User user) throws ServiceException {
        try {
            UserDao userDao = factory.getUserDao();

            if(VALIDATE.isValid(user)) {

                String password = user.getPassword();
                String passwordMD5 = DigestUtils.md5Hex(password);
                user.setPassword(passwordMD5);

                boolean check = userDao.checkUser(user.getLogin(), user.getPassword());
                if (!check) {
                    return null;
                } else {
                    return userDao.getUserNode(user.getLogin(), user.getPassword());
                }
            } else {
                throw new ValidationException("Validation Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public User create(User user) throws ServiceException {
        UserDao userDao = factory.getUserDao();
        try {
            if(VALIDATE.isValid(user)){
                String password = user.getPassword();

                boolean check = false;
                if (!check){
                    long id = 0;
                    try {
                        id = userDao.create(user);
                    } catch (com.htp.exception.DaoException e) {
                        e.printStackTrace();
                    }
                    user.setUserId(id);
                    return user;
                }else {
                    return null;
                }
            }else {
                throw new ValidationException("VA exc");
            }
        }catch (DaoException e){
            throw new ServiceException("SE exc",e);
        }
    }

    @Override
    public Long listAll() {
        return null;
    }

}
