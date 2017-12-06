package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class Mountain extends Region {
    Tile[][] ground;
    Mountain(Size size) {
        super(size);
        ground = new Tile[this.dimensions][this.dimensions];
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                ground[i][j] = new Tile(2);
            }
        }
    }

    @Override
    public String toString() {
        return "M";
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
        return  new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    @Override
    public Image getImage() {
        try {
            Image img = ImageIO.read(getClass().getResource("../images/mountianTile.png"));
            return img;
        } catch (Exception e) {
            System.out.println(".getImage Failed in mountain");
            return null;
        }
    }


}
