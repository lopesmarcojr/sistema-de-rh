package model.service;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class EmployeeService {

    public EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();


}
