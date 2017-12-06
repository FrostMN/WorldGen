package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Plain extends Region {
    Tile[][] ground;
    Plain(Size size) {
        super(size);
        ground = new Tile[this.dimensions][this.dimensions];
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                ground[i][j] = new Tile(1);
            }
        }
    }

    @Override
    public String toString() {
        return "~";
    }

    @Override
    public String getMap() {
        String map = "";
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                map += this.ground[i][j];
            }
            map += "\n";
        }
        return map;
    }

    @Override
    public ImageIcon getIcon() {
        Image img = getImage();
        return new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    @Override
    public Image getImage() {
        Image img;

        File f = new File("src/main/java/com/aaron/images/grassTile.png");
//        System.out.println(f.getAbsolutePath());
//        if (f.exists()) {
//            System.out.println("it exists");
//        } else {
//            System.out.println("it does not exist");
//        }

        try {
//            img = ImageIO.read(getClass().getResource("../images/grassTile.png"));
            img = ImageIO.read(f);
            return img;
        } catch (Exception e) {
            System.out.println(".getImage Failed in plains");
            System.out.println(e);
            return null;
        }
    }

//    @Override
//    public ImageIcon getTileIcon(int i, int j) {
//        Tile t = super.tiles[i][j];
//        return t.getIcon();
//    }


}
