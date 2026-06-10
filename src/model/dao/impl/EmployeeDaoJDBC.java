package model.dao.impl;

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

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }
}
