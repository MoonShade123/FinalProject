package com.htp.dao;

import com.htp.domain.to.User;
import com.htp.exceptions.DaoException;

public interface UserDao extends GenericDAO <User, Long> {
  /** Method get {@link User} object from database by login and password
   * @param login login unique parameter
   * @param password password parameter
   * @return {@link User} object
   * @throws DaoException
   **/
    User getUserNode(String login, String password) throws DaoException;

    /**
     * Method check user node in database by login and password transfers parameters
     *
     * @param login login unique parameter
     * @param password password parameter
     * @return boolean result of operation
     * @throws DaoException
     */
    boolean checkUser(String login, String password) throws DaoException;

}
