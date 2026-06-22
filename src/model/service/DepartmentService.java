package model.service;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class DepartmentService {

    public DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    public void insert(Department department){
        if(department == null){
            throw new DBException("Department cannot be null");
        }
        if(department.getName() == null){
            throw new DBException("Department name cannot be null");
        }
        if(department.getName().trim().isEmpty()){
            throw new DBException("Department name cannot be empty");
        }
        departmentDao.insert(department);
    }

    public void update(Department department){
        if(department == null){
            throw new DBException("Department cannot be null");
        }
        if(department.getId() == null){
            throw new DBException("Department id cannot be null");
        }
        if(department.getId() < 0){
            throw new DBException("Department if should be greater than zero");
        }
        if(department.getName() == null){
            throw new DBException("Department name cannot be null");
        }
        if(department.getName().trim().isEmpty()){
            throw new DBException("Department name cannot be empty");
        }
        departmentDao.update(department);
    }

    public Department findById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id < 0){
            throw new DBException("Id should be greater than zero");
        }
        return departmentDao.findById(id);
    }

    public List<Department> findAll(){
        return departmentDao.findAll();
    }
}
