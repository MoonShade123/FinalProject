package com.htp.service;

import com.htp.exceptions.ServiceException;

import java.util.List;

public interface GenericServiceInterface<T, K> {
    /**
     * Method adding object in database and creates the appropriate entry there
     *
     * @param entity object necessary to adding in database
     * @return {@link T} object, that method can create
     * @throws com.htp.exceptions.ServiceException
     */
    T create(T entity) throws com.htp.exceptions.ServiceException, com.google.protobuf.ServiceException;

    /** Method provides viewing all information and package this information in view object
     *
     * @return {@link K} object necessary for view all objects
     * @throws com.htp.exceptions.ServiceException
     */

    K listAll() throws ServiceException;

    /*List<T> findAll() throws ServiceException;

    T findById(K id) throws ServiceException;

    boolean delete(K id) throws ServiceException;

    K update(T entity) throws ServiceException;*/
}
