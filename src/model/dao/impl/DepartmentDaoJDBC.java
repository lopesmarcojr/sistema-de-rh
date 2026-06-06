package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao{

    public static Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                    +   "(Name) "
                    +   "VALUE "
                    +   "(?)",PreparedStatement.RETURN_GENERATED_KEYS
            );
            st.setString(1,department.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                }
            }
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?"
            );
            st.setString(1,department.getName());
            st.setInt(2,department.getId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DBException("No department found with this id!");
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
            st = conn.prepareStatement("DELETE FROM department WHERE id = ?");

            st.setInt(1,id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DBException("No department found with this id!");
            }
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT Id,Name FROM department WHERE Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                Department department = instantiateDepartment(rs);
                return department;
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            List<Department> departments = new ArrayList<>();
            while(rs.next()){
                Department department = instantiateDepartment(rs);
                departments.add(department);
            }
            return departments;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }

    }

    public Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));
        return department;
    }
}
