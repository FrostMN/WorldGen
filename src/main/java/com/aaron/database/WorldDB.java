// sql to create the databases for the program
// create database world_gen;
// create user 'world_user'@'localhost' identified by 'need a good password here';
// grant select, insert, update, delete, create, drop, references on world_gen.* to 'world_user'@'localhost';
// then run the InitalizeDB class to setup the schema

package com.aaron.database;
import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<DBWorld> getAllSavedWorlds() {
        ArrayList<DBWorld> saved = new ArrayList<>();

        testDriver(JDBC_DRIVER);

        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
        try (Connection conn = DriverManager.getConnection(DBConfig.db_url, DBConfig.user, DBConfig.password);
             Statement statement = conn.createStatement()) {
            String getWorlds =
                    "SELECT * FROM world_seeds ";

            ResultSet rs = statement.executeQuery(getWorlds);

            while (rs.next()) {
                Integer SeedID = rs.getInt("SeedID");
                String name = rs.getString("name");
                Integer seed = rs.getInt("seed");
                Integer size = rs.getInt("size");
                String notes = rs.getString("notes");

                saved.add(new DBWorld(SeedID, name, seed, size, notes));
            }

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }


        return saved;
    }

    public static void saveCurrentWorld(String seed, Integer size, String name, String notes) {
        System.out.println("in save current world");

        if (worldNameExists(name)) {
            System.out.println("world name exists");
            updateWorld(seed, size, name, notes);
        } else {
            System.out.println("world name does not exists");
            saveNewWorld(seed, size, name, notes);
        }
    }

    public static void saveNewWorld(String seed, Integer size, String name, String notes) {

        testDriver(JDBC_DRIVER);

        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
        try (Connection conn = DriverManager.getConnection(DBConfig.db_url, DBConfig.user, DBConfig.password);
             Statement statement = conn.createStatement()) {
            String newWorldString = "INSERT INTO world_seeds (name, seed, size, notes) VALUES ( ?, ?, ?, ? )";

            PreparedStatement newWorld = conn.prepareStatement(newWorldString);

            newWorld.setString(1, name);
            newWorld.setInt(2, Integer.parseInt(seed));
            newWorld.setInt(3, size);
            newWorld.setString(4, notes);

            newWorld.execute();

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public static void updateWorld(String seed, Integer size, String name, String notes) {
        testDriver(JDBC_DRIVER);

        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
        try (Connection conn = DriverManager.getConnection(DBConfig.db_url, DBConfig.user, DBConfig.password);
             Statement statement = conn.createStatement()) {
            String newWorldString =
                    "UPDATE world_seeds " +
                    "SET name=?, seed=?, size=?, notes=? " +
                    "WHERE name = ?";

            PreparedStatement newWorld = conn.prepareStatement(newWorldString);

            newWorld.setString(1, name);
            newWorld.setInt(2, Integer.parseInt(seed));
            newWorld.setInt(3, size);
            newWorld.setString(4, notes);
            newWorld.setString(5, name);

            newWorld.execute();

            statement.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public static boolean worldNameExists(String name) {
        testDriver(JDBC_DRIVER);

        //Try with resources to open the connection and create a statement. Make sure your language level is 1.7+
        try (Connection conn = DriverManager.getConnection(DBConfig.db_url, DBConfig.user, DBConfig.password);
             Statement statement = conn.createStatement()) {
            String worldExistsString = "SELECT COUNT(*) FROM world_seeds WHERE name = ?";

            PreparedStatement worldExists = conn.prepareStatement(worldExistsString);

            worldExists.setString(1, name);

            ResultSet rs = worldExists.executeQuery();

            int count;
            if(rs.next()) {
                count = rs.getInt(1);
            } else {
                count = 2;
            }

            if (count == 0) {
                statement.close();
                conn.close();
                return false;
            } else {
                statement.close();
                conn.close();
                return true;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }
}
