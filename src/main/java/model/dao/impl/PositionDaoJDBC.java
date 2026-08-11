package model.dao.impl;

import db.DB;
import db.DBException;
import model.entities.Department;
import model.entities.Position;
import model.dao.PositionDao;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoJDBC implements PositionDao {

    public Connection conn;

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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT Id, Name FROM position");
            rs = st.executeQuery();
            List<Position> positions = new ArrayList<>();
            while(rs.next()){
                Position position = instantiantePosition(rs);
                positions.add(position);
            }
            return positions;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Position> findPage(int page, int pageSize) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int offset = (page - 1) * pageSize;
        try {
            st = conn.prepareStatement("SELECT Id, Name FROM position ORDER BY Id LIMIT ? OFFSET ?");
            st.setInt(1, pageSize);
            st.setInt(2, offset);
            rs = st.executeQuery();
            List<Position> positions = new ArrayList<>();
            while(rs.next()){
                positions.add(instantiantePosition(rs));
            }
            return positions;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Integer countPosition() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
                st = conn.prepareStatement("SELECT COUNT(*) as TotalPositions FROM position");
                rs = st.executeQuery();
                if(rs.next())
                    return rs.getInt("TotalPositions");
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closePreparedStatement(st);
            DB.closeResultSet(rs);
        }
        return 0;
    }


    public Position instantiantePosition(ResultSet rs) throws SQLException{
        Position position = new Position();
        position.setId(rs.getInt("Id"));
        position.setName(rs.getString("Name"));
        return position;
    }
}
