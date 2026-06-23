package model.service;

import db.DBException;
import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();

    public void insert(Employee employee){
        validateEmployeeData(employee);
        employeeDao.insert(employee);
    }

    public void update(Employee employee){
        validateEmployeeData(employee);
        if(employee.getId() == null){
            throw new DBException("Employee id cannot be null");
        }
        if(employee.getId() <= 0){
            throw new DBException("Employee id should be greater than zero");
        }
        employeeDao.update(employee);
    }

    public Employee findById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }
        return employeeDao.findById(id);
    }

    public void deleteById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }

        employeeDao.deleteById(id);
    }

    public List<Employee> findAll(){
        return employeeDao.findAll();
    }

    public Integer countEmployees(){
        return employeeDao.countEmployees();
    }

    public List<Employee> findPage(Integer page, Integer pageSize){
        validatePagination(page, pageSize);
        return employeeDao.findPage(page, pageSize);
    }

    public List<Employee> findByFilters(String department, String position, Double salary){
        validateFilters(department, position, salary);
        return employeeDao.findByFilters(department, position, salary);
    }

    public Integer countEmployeeByFilters(String department, String position, Double salary){
        validateFilters(department, position, salary);
        return employeeDao.countEmployeeByFilters(department, position, salary);
    }

    public List<Employee> findPageByFilters(String department, String position, Double salary, Integer page, Integer pageSize){
        validateFilters(department, position, salary);
        validatePagination(page, pageSize);
        return employeeDao.findPageByFilters(department, position, salary, page, pageSize);
    }

    private void validatePagination(Integer page, Integer pageSize){
        if(page == null){
            throw new DBException("Page number cannot be null");
        }
        if(page <= 0){
            throw new DBException("Page number should be greater than zero");
        }
        if(pageSize == null){
            throw new DBException("Page size cannot be null");
        }
        if(pageSize <= 0){
            throw new DBException("Page size should be greater than zero");
        }
    }
    private void validateFilters(String department, String position, Double salary){
        if(department == null && position == null && salary == null) {
            throw new DBException("At least one of the parameters must be valid!");
        }
        if(department != null && department.trim().isEmpty()){
            throw new DBException("Department cannot be empty");
        }
        if(position != null && position.trim().isEmpty()){
            throw new DBException("Position cannot be empty");
        }
        if(salary != null && salary <= 0){
            throw new DBException("Salary should be greater than zero");
        }
    }

    private void validateEmployeeData(Employee employee){
        if(employee ==  null){
            throw new DBException("Employee cannot be null");
        }
        if(employee.getName() == null){
            throw new DBException("Employee name cannot be null");
        }
        if(employee.getName().trim().isEmpty()){
            throw new DBException("Employee name cannot be empty");
        }
        if(employee.getSalary() == null){
            throw new DBException("Employee salary cannot be null");
        }
        if(employee.getSalary() <= 0){
            throw new DBException("Employee salary should be greater than zero");
        }
        if(employee.getHireDate() == null){
            throw new DBException("Employee hire date cannot be null");
        }
        if(employee.getDepartmentId() == null){
            throw new DBException("Employee department id cannot be null");
        }
        if(employee.getDepartmentId().getId() == null){
            throw new DBException("Employee department id cannot be null");
        }
        if(employee.getDepartmentId().getId() <= 0){
            throw new DBException("Employee department id should be greater than zero");
        }
        if(employee.getPositionId() == null){
            throw new DBException("Employee position id cannot be null");
        }
        if(employee.getPositionId().getId() == null){
            throw new DBException("Employee position id cannot be null");
        }
        if(employee.getPositionId().getId() <= 0){
            throw new DBException("Employee position id should be greater than zero");
        }
    }

    private void validadeEmployeeId(Integer id){

    }
}
