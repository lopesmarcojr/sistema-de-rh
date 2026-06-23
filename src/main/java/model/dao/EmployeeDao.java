package model.dao;

import main.java.model.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    void insert(Employee employee);
    void update(Employee employee);
    void deleteById(Integer id);
    Employee findById(Integer id);
    List<Employee> findAll();
    Integer countEmployees();
    List<Employee> findPage(int page, int pageSize);
    List<Employee> findByFilters(String department, String position, Double salary);
    Integer countEmployeeByFilters(String department, String position, Double salary);
    List<Employee> findPageByFilters(String department, String position, Double salary, Integer page, Integer pageSize);

}
