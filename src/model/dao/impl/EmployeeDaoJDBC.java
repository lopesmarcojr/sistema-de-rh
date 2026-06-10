package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.EmployeeDao;
import model.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {

    public static Connection conn;

    public EmployeeDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Employee employee) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO employee "
                                       +    " (Name, Salary, HireDate, DepartmentId, PositionId)"
                                       +    " VALUE (?,?,?,?,?) ",
                                            PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1,employee.getName());
            st.setDouble(2,employee.getSalary());
            st.setDate(3,new java.sql.Date(employee.getHireDate().getTime()));
            st.setInt(4,employee.getDepartmentId());
            st.setInt(5,employee.getPositionId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    employee.setId(id);
                }
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
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
            st.setInt(4,employee.getDepartmentId());
            st.setInt(5,employee.getPositionId());
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
            st = conn.prepareStatement("DELETE Id,Name FROM employee WHERE Id = ?");
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
            st = conn.prepareStatement("SELECT * FROM employee WHERE Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                Employee emp = instantiateEmployee(rs);
                return emp;
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    public Employee instantiateEmployee(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        employee.setId(rs.getInt("Id"));
        employee.setName(rs.getString("Name"));
        employee.setSalary(rs.getDouble("Salary"));
        employee.setHireDate(rs.getDate("HireDate"));
        employee.setDepartmentId(rs.getInt("DepartmentId"));
        employee.setPositionId(rs.getInt("PositionId"));
        return employee;
    }
}
