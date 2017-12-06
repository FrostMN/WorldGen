// sql to create the databases for the program
// create database world_gen;
// create user 'world_user'@'localhost' identified by 'need a good password here';
// grant select, insert, update, delete, create, drop, references on world_gen.* to 'world_user'@'localhost';
// then run the InitalizeDB class to setup the schema

package com.aaron.database;
import java.sql.*;

public class WorldDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed

    public static void executeQuery(String QueryString) {

        testDriver(JDBC_DRIVER);

        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
        try (Connection conn = DriverManager.getConnection(DBConfig.db_url, DBConfig.user, DBConfig.password);
             Statement statement = conn.createStatement()) {

//            statement.executeQuery(QueryString);

            statement.execute(QueryString);

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }



    private static void testDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drivers and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }
    }

}
