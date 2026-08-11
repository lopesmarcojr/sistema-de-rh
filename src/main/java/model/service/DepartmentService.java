package model.service;

import db.DBException;
import model.entities.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.List;

public class DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }

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
        if(department.getId() <= 0){
            throw new DBException("Department id should be greater than zero");
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
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }
        return departmentDao.findById(id);
    }

    public void deleteById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }
    }

    public List<Department> findAll(){
        return departmentDao.findAll();
    }

    public Integer countDepartment(){
        return departmentDao.countDepartment();
    }

    public List<Department> findPage(int page, int pageSize){
        validatePagination(page, pageSize);
        return departmentDao.findPage(page, pageSize);
    }

    public void validatePagination(Integer page, Integer pageSize){
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
}
