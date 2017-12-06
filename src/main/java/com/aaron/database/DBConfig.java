package com.aaron.database;

public class DBConfig {

    static String db_url = "jdbc:mysql://127.0.0.1:3306/world_gen";
    static String user = System.getenv("WORLD_MYSQL_USER");
    static String password = System.getenv("WORLD_MYSQL_PW");

}
