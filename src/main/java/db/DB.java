package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import main.java.db.DBException;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    public static Properties loadProperties(){
        Properties props = new Properties();
        try(InputStream fs = DB.class.getClassLoader().getResourceAsStream("db.properties")){
            props.load(fs);
        }catch (IOException e){
            throw new main.java.db.DBException(e.getMessage());
        }
        return props;
    }

    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
                conn = null;
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }
            catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement st){
        if(st != null){
            try{
                st.close();
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }


}
