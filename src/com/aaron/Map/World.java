package com.aaron.Map;

import com.aaron.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

//import java.math.*;

public class World {
    private int dimentions;
    private Size size;
    private Region[][] regions;

    public World(Size size) {
        this.size = size;
        switch (size) {
            case SMALL:
                this.dimentions = 16;
                break;
            case MEDIUM:
                this.dimentions = 32;
                break;
            case LARGE:
                this.dimentions = 64;
                break;
            default:
        }

        regions = new Region[this.dimentions][this.dimentions];
        for (int i = 0; i < this.dimentions; i++) {
            for (int j = 0; j < this.dimentions; j++) {
                regions[i][j] = new Plain(size);
            }
        }

        placeMountians();
        placeMountians();
        placeMountians();
        placeRivers();
        placeForest();
        placeCities();
    }

    private void placeMountians() {
        Random rand = new Random();
        int x1 = rand.nextInt(this.dimentions);
        int y1 = rand.nextInt(this.dimentions);
        int x2 = rand.nextInt(this.dimentions);
        int y2 = rand.nextInt(this.dimentions);

        System.out.println("x1:" + x1 );
        System.out.println("y1:" + y1 );
        System.out.println("x2:" + x2 );
        System.out.println("y2:" + y2 );

        int y3 = y2 - y1;
        int x3 = x2 - x1;

//        this.regions[x1][y1] = new Mountain(this.size);
//        this.regions[x2][y2] = new Mountain(this.size);

        double m = 0;
        if ( ( x3 != 0 ) ) {
            m = y3 / x3;
            for (int i = 0; i < this.dimentions; i++) {
                for (int j = 0; j < this.dimentions; j++) {
                    if ( Math.round( j - y1 ) == Math.round(m * ( i - x1 ) )) {
                        this.regions[i][j] = new Mountain(this.size);
                    }
                }
            }
        } else {
            for (int i = 0; i < this.dimentions; i++) {
                for (int j = 0; j < this.dimentions; j++) {
                    if (y1 == j) {
                        this.regions[i][j] = new Mountain(this.size);
                    }
                }
            }
        }
    }

    private void placeRivers() {
    }

    private void placeForest() {
    }

    private void placeCities() {
        Random rand = new Random();
        int x = rand.nextInt(this.dimentions);
        int y = rand.nextInt(this.dimentions);

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
    }

    public void printWorld(){
        String reg = "";
        for (int i = 0; i < this.dimentions; i++) {
            for (int j = 0; j < this.dimentions; j++) {
                reg += this.regions[i][j];
            }
            reg +="\n";
        }
        System.out.println(reg);
    }

    @Override
    public String toString() {
        String reg = "";
        for (int i = 0; i < this.dimentions; i++) {
            for (int j = 0; j < this.dimentions; j++) {
                reg += this.regions[i][j];
            }
            reg +="\n";
        }
        return reg;
    }

    public String getRegionMap(int x, int y) {
        return this.regions[x][y].getMap();
    }

    public ImageIcon getRegionIcon(int x, int y) {
        return this.regions[x][y].getIcon();
    }

    public Image getRegionImage(int x, int y) {
        return this.regions[x][y].getImage();
    }


    public Size getSize() {
        return this.size;
    }

    public Region getRegion(int x, int y) {
        return regions[x][y];
    }
}
