package com.aaron.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Tile {
    private Terrain type;
    private String image;

    Tile(Terrain type) {
        this.type = type;
        this.image = setImage(this.type);
    }

    private String setImage(Terrain type) {
        switch (type) {
            case PLAINS:
                return "src/main/java/com/aaron/images/grassTile.png";
            case MOUNTAIN:
                return "src/main/java/com/aaron/images/mountainTile.png";
            case SETTLEMENT:
                return "src/main/java/com/aaron/images/townTile.png";
            case CAVE:
                return "src/main/java/com/aaron/images/caveTile.png";
            case OCEAN:
                return "src/main/java/com/aaron/images/waterTile.png";
            case DIRT:
                return "src/main/java/com/aaron/images/dirtTile.png";
            case AIR:
                return "src/main/java/com/aaron/images/airTile.png";
            case FOREST:
                return "src/main/java/com/aaron/images/regionTile.png";
            case ROAD:
                return "src/main/java/com/aaron/images/roadTile.png";
            case WALL:
                return "src/main/java/com/aaron/images/wallTile.png";
            default:
                return "src/main/java/com/aaron/images/regionTile.png";
        }
    }

    @Override
    public String toString() {
        switch (this.type) {
            case PLAINS:
                return "~";
            case SETTLEMENT:
                return "_";
            case MOUNTAIN:
                return "^";
            default:
                return "NEEDS A toString() added in Tile.java";
        }
    }

    ImageIcon getIcon() {
        return new ImageIcon(getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    Image getImage() {
        File f;
        try {
            f = new File(this.image);
            return ImageIO.read(f);
        } catch (Exception e) {
            return null;
        }
    }

    public String getType() {
        switch (this.type) {
            case PLAINS:
                return "Grass";
            case MOUNTAIN:
                return "Mountain";
            case SETTLEMENT:
                return "Town";
            case CAVE:
                return "Cave";
            case OCEAN:
                return "Water";
            case DIRT:
                return "Dirt";
            case AIR:
                return "Air";
            case FOREST:
                return "Forest";
            default:
                return "NEEDS A getType() added in Tile.java";
        }
    }

    public void setType(Terrain type) {
        this.type = type;
        this.image = setImage(type);
    }
}
