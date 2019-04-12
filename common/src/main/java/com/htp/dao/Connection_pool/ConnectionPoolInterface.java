package com.htp.dao.Connection_pool;

import java.sql.Connection;

/* Provides a common interface to work with pool of connections.
        */
public interface ConnectionPoolInterface {
    /* Initialize pool with necessary properties from file.
     * @throws ConnectionPoolException
     */
    void init() throws com.htp.dao.Connection_pool.ConnectionPoolException;

    /* Close connection pool
     *
             * @throws ConnectionPoolException
     */
    void dispose() throws com.htp.dao.Connection_pool.ConnectionPoolException;

    /* Getting connection from pool
     *
             * @return connection from pool
     * @throws ConnectionPoolException
     */
    Connection getConnection() throws com.htp.dao.Connection_pool.ConnectionPoolException;

    /**
     * Return connection in  pool
     */
    void putConnection();
}
