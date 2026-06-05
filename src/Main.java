import db.DB;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Properties props = DB.loadProperties();

    System.out.println(props.getProperty("dburl"));
    System.out.println(props.getProperty("user"));

    Connection conn = DB.getConnection();
    if(conn != null){
        System.out.println("Conexão bem sucedida!");
    }
    DB.closeConnection();
    }

