package com.aaron.database;

public class InitalizeDB {
    public static void main(String[] args) {

        System.out.println(DBConfig.db_url);
        System.out.println(DBConfig.user);
        System.out.println(DBConfig.password);

        String createSchema =
                "CREATE TABLE world_seeds (" +
                        "SeedID INT NOT NULL AUTO_INCREMENT, " +
                        "seed BIGINT, " +
                        "notes MEDIUMTEXT, " +
                        "PRIMARY KEY (SeedID) ) ";

        WorldDB.executeQuery(createSchema);

    }
}
