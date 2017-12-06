package com.aaron.database;

public class InitalizeDB {
    public static void main(String[] args) {

        System.out.println(DBConfig.db_url);
        System.out.println(DBConfig.user);
        System.out.println(DBConfig.password);


        String dropOldSeeds = "DROP TABLE IF EXISTS world_seeds ";

        String createSchema =
                "CREATE TABLE world_seeds (" +
                        "SeedID INT NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(25) UNIQUE NOT NULL, " +
                        "seed BIGINT NOT NULL, " +
                        "size INT NOT NULL, " +
                        "notes MEDIUMTEXT, " +
                        "PRIMARY KEY (SeedID) ) ";

        String addTestData = "INSERT INTO world_seeds (name, seed, size, notes) " +
                "VALUES ('TestWorld', '123', 0, 'test data' )";

        WorldDB.executeQuery(dropOldSeeds);
        WorldDB.executeQuery(createSchema);
        WorldDB.executeQuery(addTestData);

    }
}
