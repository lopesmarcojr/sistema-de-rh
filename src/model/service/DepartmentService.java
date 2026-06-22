package model.service;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class DepartmentService {

    public DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


}
