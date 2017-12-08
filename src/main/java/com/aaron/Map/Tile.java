package com.aaron.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Tile {
    private int type;
    private Image image;

    Tile(int type) {
        this.type = type;

        File f;

        try {
            switch (this.type) {
                case 0:
                    String imageFileName = "src/main/java/com/aaron/images/regionTile.png";
                    f = new File(imageFileName);
                    this.image = ImageIO.read(f);
                    break;
                case 1:
                    imageFileName = "src/main/java/com/aaron/images/grassTile.png";
                    f = new File(imageFileName);
                    this.image = ImageIO.read(f);
                    break;
                case 2:
                    imageFileName = "src/main/java/com/aaron/images/mountainTile.png";
                    f = new File(imageFileName);
                    this.image = ImageIO.read(f);
                    break;
                case 3:
                    imageFileName = "src/main/java/com/aaron/images/regionTile.png";
                    f = new File(imageFileName);
                    this.image = ImageIO.read(f);
                    break;
                default:
                    imageFileName = "src/main/java/com/aaron/images/regionTile.png";
                    f = new File(imageFileName);
                    this.image = ImageIO.read(f);
                    break;
            }
        } catch (Exception e) {
            this.image = null;
        }

    }

    @Override
    public String toString() {
        switch (this.type) {
            case 0:
                return "#";
            case 1:
                return "_";
            case 2:
                return "^";
            default:
                return " ";
        }
    }

    ImageIcon getIcon() {
        return new ImageIcon(getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    Image getImage() {
        System.out.println("In Tile.getImage()");
        System.out.println(this.type);
        System.out.println(this.image);
        return this.image;
    }

    public String getType() {
        String type;
        switch (this.type) {
            case 0:
                type = "generic";
                break;
            case 1:
                type = "grass";
                break;
            case 2:
                type = "mountain";
                break;
            case 3:
                type = "generic";
                break;
            default:
                type = "generic";
                break;
        }
        return type;
    }
}
