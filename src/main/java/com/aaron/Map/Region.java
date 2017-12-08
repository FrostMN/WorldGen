package com.aaron.Map;

import com.aaron.utilities.Size;

import javax.imageio.ImageIO;
import java.awt.*;

public class Region {
    protected int dimensions;
    private Size size;

    Tile[][] ground;

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

    // this method should be over written and should never be called
    // but it returns a generic "region tile" for debugging
    public Image getRegionImage() {
        try {
            return ImageIO.read(getClass().getResource("../images/regionTile.png"));
        } catch (Exception e) {
            System.out.println(".getRegionImage Failed in region");
            return null;
        }
    }

    public Image getTileImage(Integer x, Integer y) {
        System.out.println("in getTileImage() of Region");
        Tile t = this.ground[x][y];

        return t.getImage();
    }

    public String getTileType(Integer x, Integer y) {
        return this.ground[x][y].getType();
    }

//    public ImageIcon getTileIcon(int i, int j) {
//        Tile t = ground[i][j];
//        return t.getIcon();
//    }
}
