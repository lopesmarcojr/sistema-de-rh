package model.dao.impl;

import model.dao.EmployeeDao;

import java.sql.Connection;

public class EmployeeDaoJDBC implements EmployeeDao {

    public static Connection conn;

    public EmployeeDaoJDBC(Connection conn){
        this.conn = conn;
    }
}
