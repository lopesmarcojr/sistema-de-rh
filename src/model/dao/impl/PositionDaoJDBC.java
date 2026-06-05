package model.dao.impl;

import model.dao.PositionDao;

import java.sql.Connection;

public class PositionDaoJDBC implements PositionDao {

    public static Connection conn;

    public PositionDaoJDBC(Connection conn){
        this.conn = conn;
    }
}
