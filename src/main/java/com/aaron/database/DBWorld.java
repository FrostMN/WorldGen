package com.aaron.database;

public class DBWorld {
    Integer SeedID;
    String name;
    Integer seed;
    Integer size;
    String notes;

    public DBWorld(Integer SeedID, String name, Integer seed, Integer size, String notes) {
        this.SeedID = SeedID;
        this.name = name;
        this.seed = seed;
        this.size = size;
        this.notes = notes;
    }

    public Integer getSeedID() {
        return SeedID;
    }

    public String getName() {
        return name;
    }

    public Integer getSeed() {
        return seed;
    }

    public Integer getSize() {
        return size;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return name;
    }
}
