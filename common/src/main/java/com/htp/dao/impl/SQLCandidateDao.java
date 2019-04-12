package com.htp.dao.impl;

import com.htp.dao.CandidateDao;
import com.htp.dao.Connection_pool.ConnectionPool;
import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.domain.to.Candidate;
import com.htp.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCandidateDao implements CandidateDao {
    private static final String SELECT_ALL_CANDIDATE = "SELECT * FROM candidate";
    private static final String SELECT_CANDIDATE_BY_ID = "SELECT * FROM candidate WHERE CandidateID = ?";
    private static final String SELECT_ONE_CANDIDATE = "SELECT * FROM candidate WHERE Candidate_Name = ? AND Candidate_Surname = ?";
    private static final String INSERT_CANDIDATE = "INSERT INTO candidate (CandidateID,Candidate_Name,Candidate_Surname, Candidate_Email, Candidate_Phone, Candidate_Info) "
            + "VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERTED_CANDIDATE_ID = "SELECT last_insert_id() as lastId";
    private static final String UPDATE_CANDIDATE = "UPDATE candidate SET " +
            "Candidate_Name = ?, Candidate_Surname = ?,Candidate_Email = ?, Candidate_Phone = ?, Candidate_Info = ? WHERE CandidateID = ?";
    private static final String DELETE_CANDIDATE = "DELETE FROM candidate WHERE CandidateID = ?";


    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CANDIDATE_ID = "CandidateID";
    private static final String CANDIDATE_NAME = "Candidate_Name";
    private static final String CANDIDATE_SURNAME = "Candidate_Surname";
    private static final String CANDIDATE_EMAIL = "Candidate_Email";
    private static final String CANDIDATE_PHONE = "Candidate_Phone";
    private static final String CANDIDATE_INFO = "Candidate_Info";
    private static final String LAST_CANDIDATE_ID = "lastId";


    //Demand Holder Idiom
    private SQLCandidateDao(){}

    public static CandidateDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final CandidateDao instance = new SQLCandidateDao();
    }

    @Override
    public Candidate getCandidateName(String name, String surname) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ONE_CANDIDATE)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet set = statement.executeQuery();

            Candidate candidate = new Candidate();
            candidate.setCandidateID(set.getInt(CANDIDATE_ID));
            candidate.setCandidateName(set.getString(CANDIDATE_NAME));
            candidate.setCandidateSurname(set.getString(CANDIDATE_SURNAME));
            candidate.setCandidateEmail(set.getString(CANDIDATE_EMAIL));
            candidate.setCandidatePhone(set.getInt(CANDIDATE_PHONE));
            candidate.setCandidateInfo(set.getString(CANDIDATE_INFO));
            return candidate;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean checkCandidate(String name, String surname) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ONE_CANDIDATE)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean checkCandidateId(Long id) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_CANDIDATE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            return set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<Candidate> findAll() throws DaoException {
        List<Candidate> candidateList = new ArrayList<>();
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ALL_CANDIDATE)) {
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandidateName(set.getString(CANDIDATE_NAME));
                candidate.setCandidateSurname(set.getString(CANDIDATE_SURNAME));
                candidate.setCandidatePhone(set.getInt(CANDIDATE_PHONE));
                candidateList.add(candidate);
            }

        }catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return candidateList;
    }

    @Override
    public Candidate findById(Long id) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_CANDIDATE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandidateID(set.getInt(CANDIDATE_ID));
                candidate.setCandidateName(set.getString(CANDIDATE_NAME));
                candidate.setCandidateSurname(set.getString(CANDIDATE_SURNAME));
                return candidate;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(DELETE_CANDIDATE)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            return true;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long create(Candidate entity) throws DaoException {
        try(Connection connect = pool.getConnection();
        PreparedStatement statement = connect.prepareStatement(INSERT_CANDIDATE);
        PreparedStatement statementNext = connect.prepareStatement(LAST_INSERTED_CANDIDATE_ID)) {
            connect.setAutoCommit(false);
            statement.setInt(1, entity.getCandidateID());
            statement.setString(2, entity.getCandidateName());
            statement.setString(3, entity.getCandidateSurname());
            statement.setString(4, entity.getCandidateEmail());
            statement.setInt(5, entity.getCandidatePhone());
            statement.setString(6, entity.getCandidateInfo());
            statement.executeUpdate();
            ResultSet set = statementNext.executeQuery();
            set.next();
            return set.getLong(LAST_CANDIDATE_ID);
        }catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(Candidate entity) throws DaoException {
        try(Connection connect = pool.getConnection();
        PreparedStatement statement = connect.prepareStatement(UPDATE_CANDIDATE)) {
            statement.setString(1, entity.getCandidateName());
            statement.setString(2, entity.getCandidateSurname());
            statement.setString(3, entity.getCandidateInfo());
            statement.setInt(4, entity.getCandidatePhone());
            statement.executeUpdate();
            return Long.valueOf(entity.getCandidateID());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }

    }



}
