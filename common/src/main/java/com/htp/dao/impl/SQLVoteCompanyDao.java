package com.htp.dao.impl;

import com.htp.dao.Connection_pool.ConnectionPoolException;
import com.htp.dao.VoteCompanyDao;
import com.htp.dao.Connection_pool.ConnectionPool;
import com.htp.domain.to.VoteCompany;
import com.htp.exceptions.DaoException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLVoteCompanyDao implements VoteCompanyDao {
    private static final String SELECT_ALL = "SELECT * FROM vote_company";
    private static final String SELECT_BY_ID = "SELECT * FROM vote_company WHERE Vote_Company = ?";
    private static final String DELETE_VOTECOMPANY = "DELETE FROM vote_company WHERE Vote_Company = ?";
    private static final String INSERT_VOTECOMPANY = "INSERT INTO vote_company (Vote_Company,User_CompanyID,Candidate_CandidateID, Candidate_Consigment_Candidate_ConsigmentID, Vote_CreateTime, Vote_EndTime) "
            + "VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERTED_VOTECOMPANY_ID = "SELECT last_insert_id() as lastId";
    private static final String UPDATE_VOTECOMPANY = "UPDATE vote_company SET " +
            "User_CompanyID = ?,Candidate_CandidateID = ?, Vote_CreateTime = ?, Vote_EndTime = ? WHERE Vote_Company = ?";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String VOTECOMPANY_ID = "Vote_Company";
    private static final String CANDIDATE_ID = "Candidate_CandidateID";
    private static final String CONSIGMENT_ID = "Candidate_Consigment_Candidate_ConsigmentID";
    private static final String VOTECOMPANY_CREATE_TIME = "Vote_CreateTime";
    private static final String VOTECOMPANY_END_TIME = "Vote_EndTime";
    private static final String LAST_VOTECOMPANY_ID = "lastId";


    //Demand Holder Idiom
    private SQLVoteCompanyDao(){}

    public static VoteCompanyDao getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final VoteCompanyDao instance = new SQLVoteCompanyDao();
    }
    @Override
    public VoteCompany findByCreate(Date createTime) throws DaoException {
        return null;
    }

    @Override
    public VoteCompany findByEnd(Date endTime) throws DaoException {
        return null;
    }

    @Override
    public boolean checkVoteConsigmentId(Long id) {
        return false;
    }

    @Override
    public List<VoteCompany> findAll() throws DaoException {
        List<VoteCompany> list = new ArrayList<>();
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_ALL)) {
            ResultSet set = statement.executeQuery();
            while(set.next()) {
                VoteCompany voteCompany = new VoteCompany();
                voteCompany.setVoteCandidateID(set.getLong(CANDIDATE_ID));
                voteCompany.setVoteConsigmentID(set.getLong(CONSIGMENT_ID));
                voteCompany.setCreateTime(set.getDate(VOTECOMPANY_CREATE_TIME));
                voteCompany.setEndTime(set.getDate(VOTECOMPANY_END_TIME));
                list.add(voteCompany);
            }

        }catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return list;
    }

    @Override
    public VoteCompany findById(Long id) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                VoteCompany voteCompany = new VoteCompany();
                voteCompany.setVoteCandidateID(set.getLong(CANDIDATE_ID));
                voteCompany.setVoteConsigmentID(set.getLong(CONSIGMENT_ID));
                voteCompany.setCreateTime(set.getDate(VOTECOMPANY_CREATE_TIME));
                voteCompany.setEndTime(set.getDate(VOTECOMPANY_END_TIME));
                return voteCompany;
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
            PreparedStatement statement = connect.prepareStatement(DELETE_VOTECOMPANY)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            return true;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long create(VoteCompany entity) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(INSERT_VOTECOMPANY);
            PreparedStatement statementTwo = connect.prepareStatement(LAST_INSERTED_VOTECOMPANY_ID)) {
            connect.setAutoCommit(false);
            statement.setLong(1, entity.getVoteCompany());
            statement.setLong(2, entity.getVoteCandidateID());
            statement.setLong(3, entity.getVoteConsigmentID());
            statement.setDate(4, entity.getCreateTime());
            statement.setDate(5, entity.getEndTime());
            statement.executeUpdate();

            ResultSet set = statementTwo.executeQuery();
            set.next();
            return Long.valueOf(set.getInt(LAST_VOTECOMPANY_ID));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Long update(VoteCompany entity) throws DaoException {
        try(Connection connect = pool.getConnection();
            PreparedStatement statement = connect.prepareStatement(UPDATE_VOTECOMPANY)) {
            statement.setLong(1, entity.getVoteCandidateID());
            statement.setLong(2, entity.getVoteConsigmentID());
            statement.setDate(3, entity.getCreateTime());
            statement.setDate(4, entity.getEndTime());
            statement.executeUpdate();
            return entity.getVoteCompany();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

}
