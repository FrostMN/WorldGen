package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Mountain extends Region {
//    Tile[][] ground;
    Mountain(Size size) {
        super(size);
        ground = new Tile[this.dimensions][this.dimensions];

        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                ground[i][j] = new Tile(Terrain.MOUNTAIN);
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
    public Image getRegionImage() {
        Image img;
        File f = new File("src/main/java/com/aaron/images/mountainTile.png");
        try {
            img = ImageIO.read(f);
            return img;
        } catch (Exception e) {
            System.out.println(".getImage Failed in plains");
            System.out.println(e);
            return null;
        }
    }

//    @Override
//    public ImageIcon getIcon() {
//        Image img = getImage();
//        return  new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));
//    }
//
//    @Override
//    public Image getImage() {
//        try {
//            Image img = ImageIO.read(getClass().getResource("../images/mountianTile.png"));
//            return img;
//        } catch (Exception e) {
//            System.out.println(".getImage Failed in mountain");
//            return null;
//        }
//    }


}
