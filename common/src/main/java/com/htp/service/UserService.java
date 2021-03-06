package com.htp.service;

import com.google.protobuf.ServiceException;
import com.htp.domain.to.User;
import com.htp.exceptions.NoSuchEntityException;

public interface UserService extends GenericServiceInterface <User, Long>{
    User loadById(Long userId) throws ServiceException, NoSuchEntityException;
    /**
     * Method provides operation for login user
     *
     * @param user object, that provides authorization operation
     * @return {@link User} - login record
     * @throws ServiceException
     */
    User authorization(User user) throws ServiceException;

    User create (User user ) throws ServiceException;
}
