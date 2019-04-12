package com.htp.dao.impl;

import com.htp.dao.Connection_pool.ConnectionPool;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.dao.UserDao;
import com.htp.domain.to.User;
import com.htp.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLUserDao implements UserDao {

    private static final String SELECT_ALL = "SELECT * FROM User";
    private static final String SELECT_BY_ID = "SELECT * FROM User WHERE User_Id = ?";
    private static final String SELECT_ONE = "SELECT * FROM User WHERE login = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO User (User_Id,User_Login,User_Password, User_Name, User_Surname,User_Email, User_Mobilephone) "
            + "VALUES ( ?, ?, ?, ?, ?,?, ?)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CLIENT_ID = "User_Id";
    private static final String LOGIN = "User_Login";
    private static final String PASSWORD = "User_Password";
    private static final String ROLE = "role";
    private static final String LAST_ID = "lastId";


    private SQLUserDao(){}

    public static UserDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserDao instance = new SQLUserDao();
    }

    public User getUserNode(String login, String password) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ONE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

            User user = new User();
            user.setUserId(set.getLong(CLIENT_ID));
            user.setLogin(set.getString(LOGIN));
            user.setPassword(set.getString(PASSWORD));
            return user;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    public boolean checkUser(String login, String password) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ONE)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


    public List<User> findAll() throws DaoException {
        List<User> list = new ArrayList<>();
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ALL)) {
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                User user = new User();
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                list.add(user);
            }

        }catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return list;
    }

    @Override
    public User findById(Long id) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                User user = new User();
                user.setUserId(set.getLong(CLIENT_ID));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                return user;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public Long create(User entity) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(INSERT_USER);
            PreparedStatement statementTwo = connect.prepareStatement(LAST_INSERT_ID)) {
            connect.setAutoCommit(false);
            statement.setLong(1, entity.getUserId());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getUserName());
            statement.setString(5, entity.getSurname());
            statement.setString(6, entity.getEmail());
            statement.executeUpdate();

            ResultSet set = statementTwo.executeQuery();
            set.next();
            return Long.valueOf(set.getInt(LAST_ID));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(User entity) throws DaoException {
        return null;
    }
}
