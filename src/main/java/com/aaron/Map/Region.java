package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Region {
    protected int dimensions;
    private Size size;

    Tile[][] tiles;

    Region (Size size) {
        this.size = size;
        switch (size) {
            case SMALL:
                this.dimensions = 16;
                break;
            case MEDIUM:
                this.dimensions = 18;
                break;
            default:
                this.dimensions = 20;
                break;
        }
    }

    public String getMap() {
        return "";
    }

    public ImageIcon getIcon() {
        Image img = getImage();
        return new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    public Image getImage() {
        try {
            return ImageIO.read(getClass().getResource("../images/waterTile.png"));
        } catch (Exception e) {
            System.out.println(".getIMage Failed in region");
            return null;
        }
    }

//    public ImageIcon getTileIcon(int i, int j) {
//        Tile t = tiles[i][j];
//        return t.getIcon();
//    }
}
