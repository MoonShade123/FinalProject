package com.htp;

import com.htp.dao.Connection_pool.ConnectionPool;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.dao.UserDao;
import com.htp.dao.factory.DaoFactory;
import com.htp.exceptions.DaoException;

public class m {
    private static final ConnectionPool pool = ConnectionPool.getInstance();


    public static void main(String[] args) {
        try {
            pool.init();
            DaoFactory daoFactory =DaoFactory.getDaoFactory();
            UserDao userDao = daoFactory.getUserDao();

            System.out.println(userDao.findById(1L));

        } catch (ConnectionPoolException | DaoException | com.htp.exception.DaoException e) {
            e.printStackTrace();
        }
    }

}
