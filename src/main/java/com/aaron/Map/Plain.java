package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Plain extends Region {
//    private Tile[][] ground;
    Plain(Size size) {
        super(size);

        ground = new Tile[this.dimensions][this.dimensions];

        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                System.out.println("generting grass");
                ground[i][j] = new Tile(Terrain.PLAINS);
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
    public Image getRegionImage() {
        Image img;

        File f = new File("src/main/java/com/aaron/images/grassTile.png");

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
