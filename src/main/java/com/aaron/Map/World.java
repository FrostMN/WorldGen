package com.aaron.Map;

import com.aaron.utilities.Size;

import java.awt.*;
import java.util.Random;

//import java.math.*;

public class World {
    private int dimensions;
    private Size size;
    private Region[][] regions;
    private Long seed;

    public World(Size size, Long seed) {

        System.out.println("in world constructor");

        this.size = size;
        switch (size) {
            case SMALL:
                this.dimensions = 16;
                break;
            case MEDIUM:
                this.dimensions = 18;
                break;
            case LARGE:
                this.dimensions = 20;
                break;
            default:
        }

        this.seed = seed;

        System.out.println("before Region[][] init");

        regions = new Region[this.dimensions][this.dimensions];
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {

                Plain pl = new Plain(size);

                regions[i][j] = pl;
            }
        }


        System.out.println("region 0 0");
        System.out.println(regions[0][0]);

        placeMountians(this.seed);
        placeMountians(this.seed);
        placeMountians(this.seed);
        placeRivers();
        placeForest();
        placeCities(this.seed);
    }

    private void placeMountians(Long seed) {

        Random rand = new Random(seed);
        int x1 = rand.nextInt(this.dimensions);
        int y1 = rand.nextInt(this.dimensions);
        int x2 = rand.nextInt(this.dimensions);
        int y2 = rand.nextInt(this.dimensions);

//        System.out.println("x1:" + x1 );
//        System.out.println("y1:" + y1 );
//        System.out.println("x2:" + x2 );
//        System.out.println("y2:" + y2 );

        int y3 = y2 - y1;
        int x3 = x2 - x1;

//        this.regions[x1][y1] = new Mountain(this.size);
//        this.regions[x2][y2] = new Mountain(this.size);

        double m = 0;
        if ( ( x3 != 0 ) ) {
            m = y3 / x3;
            for (int i = 0; i < this.dimensions; i++) {
                for (int j = 0; j < this.dimensions; j++) {
                    if ( Math.round( j - y1 ) == Math.round(m * ( i - x1 ) )) {
                        this.regions[i][j] = new Mountain(this.size);
                    }
                }
            }
        } else {
            for (int i = 0; i < this.dimensions; i++) {
                for (int j = 0; j < this.dimensions; j++) {
                    if (y1 == j) {
                        this.regions[i][j] = new Mountain(this.size);
                    }
                }
            }
        }

        this.seed++;

//        System.out.println("print sed in moutnain gen");
//        System.out.println(seed);
//        System.out.println(this.seed);
    }

    private void placeRivers() {
    }

    private void placeForest() {
    }

    private void placeCities(long seed) {
        Random rand = new Random(seed);
        int x = rand.nextInt(this.dimensions);
        int y = rand.nextInt(this.dimensions);

        Size settle;

        int sw = rand.nextInt(2);

        switch (sw) {
            case 0:
                settle = Size.SMALL;
                break;
            case 1:
                settle = Size.MEDIUM;
                break;
            default:
                settle = Size.LARGE;
        }

        System.out.println("in place cities");
        this.regions[x][y] = new Settlement(settle);
        this.seed++;
    }

    public void printWorld(){
        String reg = "";
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                reg += this.regions[i][j];
            }
            reg +="\n";
        }
        System.out.println(reg);
    }

    @Override
    public String toString() {
        String reg = "";
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {
                reg += this.regions[i][j];
            }
            reg +="\n";
        }
        return reg;
    }

    public String getRegionMap(int x, int y) {
        return this.regions[x][y].getMap();
    }

//    public ImageIcon getRegionIcon(int x, int y) {
//        return this.regions[x][y].getIcon();
//    }

    public Image getRegionImage(int x, int y) {
        return this.regions[x][y].getRegionImage();
    }

    public Image getTileImage(int x, int y, int regX, int regY) {
        return this.regions[x][y].getTileImage(regX, regY);
    }

    public Size getSize() {
        return this.size;
    }

    public Integer getDimensions() {
        return this.dimensions;
    }

    public Region getRegion(int x, int y) {
        return regions[x][y];
    }

}
