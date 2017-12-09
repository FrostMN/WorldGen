package com.aaron.Map;

public class Floor {
    Tile[][] map;
    int size;
    Floor(int size) {
        this.size = size;
        this.map = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.map[i][j] = new Tile(Terrain.FLOOR);
            }
        }

    }

    @Override
    public String toString() {
        String row = "";
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                row += this.map[i][j];
            }
            row += "\n";
        }
        return row;
    }
}
