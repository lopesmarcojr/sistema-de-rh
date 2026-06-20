package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;
import model.entities.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {

    public static Connection conn;

    public EmployeeDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Employee employee) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("INSERT INTO employee "
                                       +    " (Name, Salary, HireDate, DepartmentId, PositionId)"
                                       +    " VALUE (?,?,?,?,?) ",
                                            PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1,employee.getName());
            st.setDouble(2,employee.getSalary());
            st.setDate(3,new java.sql.Date(employee.getHireDate().getTime()));
            st.setInt(4,employee.getDepartmentId().getId());
            st.setInt(5,employee.getPositionId().getId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    employee.setId(id);
                }
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Employee employee) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE employee SET Name = ?, Salary = ?, HireDate = ?, DepartmentId = ?, PositionId = ? "
                    +   "WHERE Id = ?");
            st.setString(1,employee.getName());
            st.setDouble(2,employee.getSalary());
            st.setDate(3,new java.sql.Date(employee.getHireDate().getTime()));
            st.setInt(4,employee.getDepartmentId().getId());
            st.setInt(5,employee.getPositionId().getId());
            st.setInt(6,employee.getId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DBException("No employee found with this id!");
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM employee WHERE Id = ?");
            st.setInt(1,id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DBException("No employee found with this id!");
            }
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public Employee findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT employee.*, department.Id as DepartmentId, department.Name as DepartmentName, "
                                    +      " position.Id as PositionId, position.Name as PositionName from employee "
                                    +      " INNER JOIN department ON department.Id = employee.DepartmentId "
                                    +      " INNER JOIN position ON position.Id = employee.PositionId "
                                    +      " WHERE employee.Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                Employee emp = instantiateEmployee(rs);
                return emp;
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally{
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Employee> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT employee.*, department.Id as DepartmentId, department.Name as DepartmentName, "
                                    +      " position.Id as PositionId, position.Name as PositionName from employee "
                                    +      " INNER JOIN department ON department.Id = employee.DepartmentId "
                                    +      " INNER JOIN position ON position.Id = employee.PositionId");
            rs = st.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while(rs.next()){
                Employee emp = instantiateEmployee(rs);
                employees.add(emp);
            }
            return employees;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Integer countEmployees() {
        PreparedStatement st = null;
        ResultSet rs = null;
        Integer contador = 0;
        try {
            st = conn.prepareStatement("SELECT COUNT(*) as TotalEmployees FROM employee");
            rs = st.executeQuery();
            if(rs.next()){
                contador = rs.getInt("TotalEmployees");
            }
            return contador;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Employee> findPage(int page, int pageSize) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int offset = (page - 1) * pageSize;
        try {
            st = conn.prepareStatement("SELECT employee.*, department.Id as DepartmentId, department.Name as DepartmentName, "
                                    +      " position.Id as PositionId, position.Name as PositionName FROM employee "
                                    +      " INNER JOIN department ON department.Id = employee.DepartmentId "
                                    +      " INNER JOIN position ON position.Id = employee.PositionId "
                                    +      " ORDER BY employee.Id "
                                    +      " LIMIT ? OFFSET ?");
            st.setInt(1,pageSize);
            st.setInt(2,offset);
            rs = st.executeQuery();
            List<Employee> empsByPage = new ArrayList<>();
            while(rs.next()){
                Employee employee = instantiateEmployee(rs);
                empsByPage.add(employee);
            }
            return empsByPage;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Employee> findByFilters(String department, String position, Double salary) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int contador = 0;
        String query = "SELECT employee.*, department.Id as DepartmentId, department.Name as DepartmentName, "
                +      " position.Id as PositionId, position.Name as PositionName from employee "
                +      " INNER JOIN department ON department.Id = employee.DepartmentId "
                +      " INNER JOIN position ON position.Id = employee.PositionId WHERE ";

            if(department != null || position != null || salary != null) {
                if (department != null) {
                    query += " LOWER (department.Name) LIKE ?";
                    contador++;
                }
                if (position != null) {
                    if (contador > 0) {
                        query += " AND LOWER (position.Name) LIKE ? ";
                        contador++;
                    } else {
                        query += " LOWER (position.Name) LIKE ? ";
                        contador++;
                    }
                }
                if (salary != null) {
                    if (contador > 0) {
                        query += " AND employee.salary >= ? ";
                        contador++;
                    } else {
                        query += " employee.salary >= ? ";
                        contador++;
                    }
                }
            }
            else{
                throw new DBException("At least one parameter must be valid!");
            }
        try {
            int indice = 1;
            st = conn.prepareStatement(query);
            if(department != null){
                st.setString(indice,  "%" + department.toLowerCase() + "%");
                indice++;
            }
            if(position != null){
                st.setString(indice, "%" + position.toLowerCase() + "%");
                indice++;
            }
            if(salary != null){
                st.setDouble(indice, salary);
                indice++;
            }
            rs = st.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while(rs.next()){
                Employee emp = instantiateEmployee(rs);
                employeeList.add(emp);
            }
            return employeeList;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Integer countEmployeeByFilters(String department, String position, Double salary) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int contador = 0;
        String query = "SELECT COUNT(*) as TotalEmployee FROM employee "
                +    " INNER JOIN department ON department.Id = employee.DepartmentId "
                +    " INNER JOIN position ON position.Id = employee.PositionId WHERE ";
        if(department != null || position != null || salary != null){
            if(department != null){
                query += " LOWER (department.Name) LIKE ? ";
                contador++;
            }
            if(position != null){
                if(contador > 0){
                    query += " AND LOWER (position.Name) LIKE ? ";
                    contador++;
                }
                else{
                    query += " LOWER (position.Name) LIKE ? ";
                    contador++;
                }
            }
            if(salary != null){
                if(contador > 0){
                    query += " AND salary >= ? ";
                    contador++;
                }
                else{
                    query += " salary >= ? ";
                }
            }
        }
        else{
            throw new DBException("At least one of the parameters must be valid!");
        }
        try{
            st = conn.prepareStatement(query);
            int indice = 1;
            if(department != null){
                st.setString(indice, "%" + department.toLowerCase() + "%");
                indice++;
            }
            if(position != null){
                st.setString(indice, "%" + position.toLowerCase() + "%");
                indice++;
            }
            if(salary != null){
                st.setDouble(indice, salary);
                indice++;
            }
            rs = st.executeQuery();
            int count = 0;
            if(rs.next()){
                count = rs.getInt("TotalEmployee");
            }
            return count;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    public Employee instantiateEmployee(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        employee.setId(rs.getInt("Id"));
        employee.setName(rs.getString("Name"));
        employee.setSalary(rs.getDouble("Salary"));
        employee.setHireDate(rs.getDate("HireDate"));
        employee.setDepartmentId(instantiateDepatment(rs));
        employee.setPositionId(instantiatePosition(rs));
        return employee;
    }

    public Department instantiateDepatment(ResultSet rs) throws SQLException{
        Department department = new Department();
        department.setId(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepartmentName"));
        return department;
    }

    public Position instantiatePosition(ResultSet rs) throws SQLException{
        Position position = new Position();
        position.setId(rs.getInt("PositionId"));
        position.setName(rs.getString("PositionName"));
        return position;
    }
}
