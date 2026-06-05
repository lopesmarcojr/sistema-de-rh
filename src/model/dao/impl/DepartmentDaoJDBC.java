package model.dao.impl;

import model.dao.DepartmentDao;

import java.sql.Connection;

public class DepartmentDaoJDBC implements DepartmentDao{

    public static Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }
}
