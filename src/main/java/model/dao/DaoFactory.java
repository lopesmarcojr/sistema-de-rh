package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.EmployeeDaoJDBC;
import model.dao.impl.PositionDaoJDBC;

public class DaoFactory {

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }

    public static EmployeeDao createEmploeeyDao(){
        return new EmployeeDaoJDBC(DB.getConnection());
    }

    public static PositionDao createPositionDao(){
        return new PositionDaoJDBC(DB.getConnection());
    }
}
