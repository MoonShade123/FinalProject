package com.htp.dao.impl;

import com.htp.dao.GenericDAO;
import com.htp.dao.Connection_pool.ConnectionPool;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.domain.to.UserRoles;
import com.htp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserRolesDao implements GenericDAO<UserRoles, Long> {

    private static final String USER_ID = "user_id";
    private static final String USER_ROLE = "role";

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String DELETE_BY_ID = "DELETE FROM roles WHERE UserID = ?";
    private static final String CREATE_ROLE = "INSERT INTO roles (UserID, Role) VALUES (?,?)";
    private static final String UPDATE_ROLE = "UPDATE roles SET Role=? WHERE UserID=? LIMIT 1";
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE User_Id = ?";
    private static final String SELECT_ALL_ID = "SELECT  FROM roles";

    public SQLUserRolesDao() {
    }

    //    Demand Holder Idiom
    public static SQLUserRolesDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final SQLUserRolesDao instance = new SQLUserRolesDao();
    }

    @Override
    public List<UserRoles> findAll() throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ID)) {
            ResultSet set = statement.executeQuery();
            ArrayList<UserRoles> list = new ArrayList<>();
            while (set.next()) {
                UserRoles userRoles = new UserRoles();
                userRoles.setUserId(set.getLong(USER_ID));
                userRoles.setRoleName(set.getString(UPDATE_ROLE));
                list.add(userRoles);
            }
            return list;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserRoles findById(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                UserRoles userRoles = new UserRoles();
                userRoles.setUserId(set.getLong(USER_ID));
                userRoles.setRoleName(set.getString(UPDATE_ROLE));
                return userRoles;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long create(UserRoles entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(CREATE_ROLE)) {
            statement.setLong(1, entity.getUserId());
            statement.setString(2, String.valueOf(entity.getRoleName()));
            int rows = statement.executeUpdate();
            return entity.getUserId();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(UserRoles entity) throws DaoException {
        try (Connection connect = pool.getConnection();
             PreparedStatement statement = connect.prepareStatement(UPDATE_ROLE)) {
            statement.setString(1, String.valueOf(entity.getRoleName()));
            statement.setLong(2, entity.getUserId());
            int rows = statement.executeUpdate();
            return 0L;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
