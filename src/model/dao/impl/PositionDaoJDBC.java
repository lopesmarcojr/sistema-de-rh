package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.PositionDao;
import model.entities.Position;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionDaoJDBC implements PositionDao {

    public static Connection conn;

    public PositionDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Position position) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO position (Name) VALUE (?)",PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1,position.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    position.setId(id);
                }
            }
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public void update(Position position) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE position SET Name = ? WHERE Id = ?");
            st.setString(1,position.getName());
            st.setInt(2,position.getId());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DBException("No position found with this id!");
            }
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM position WHERE Id = ?");
                st.setInt(1,id);
                int rowsAffected = st.executeUpdate();
                if(rowsAffected == 0){
                    throw new DBException("No position found with this id!");
                }
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }finally {
                DB.closePreparedStatement(st);
            }
    }

    @Override
    public Position findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT Id, Name FROM position WHERE Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                Position position = instantiantePosition(rs);
                return position;
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
    public List<Position> findAll() {
        return List.of();
    }

    public Position instantiantePosition(ResultSet rs) throws SQLException{
        Position position = new Position();
        position.setId(rs.getInt("Id"));
        position.setName(rs.getString("Name"));
        return position;
    }
}
