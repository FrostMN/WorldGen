package com.aaron.Map;


import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Settlement extends Region {
//    private Floor[] floors;

    public Settlement(Size size) {
        super(size);
        ground = new Tile[this.dimensions][this.dimensions];

        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                ground[i][j] = new Tile(Terrain.PLAINS);
            }
        }
    }


    @Override
    public String toString() {
        switch (this.dimensions) {
            case 16:
                return ".";
            case 18:
                return "+";
            default:
                return "*";
        }
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
    public Image getRegionImage() {
        Image img;
        File f = new File("src/main/java/com/aaron/images/townTile.png");
        try {
            img = ImageIO.read(f);
            return img;
        } catch (Exception e) {
            System.out.println(".getImage Failed in plains");
            System.out.println(e);
            return null;
        }
    }

}
