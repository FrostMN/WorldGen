package com.aaron.Map;


import com.aaron.Size;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Settlement extends Region {
    private Floor[] floors;

    public Settlement(Size size) {
        super(size);
        this.floors = new Floor[] {
                new Floor(this.dimensions),
                new Floor(this.dimensions),
                new Floor(this.dimensions),
                new Floor(this.dimensions) };
    }

    public void printSettlement() {
        for (int i = this.floors.length - 1; i >= 0; i--) {
            System.out.println("floor #" + i);
            System.out.println(this.floors[i]);
        }
    }

    @Override
    public String toString() {
        switch (this.dimensions) {
            case 16:
                return ".";
            case 32:
                return "+";
            default:
                return "*";
        }
    }

    @Override
    public ImageIcon getIcon() {
        Image img = getImage();
        return new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    @Override
    public Image getImage() {
        try {
            Image img = ImageIO.read(getClass().getResource("../images/townTile.png"));
            return img;
        } catch (Exception e) {
            System.out.println(".getIMage Failed in plains");
            return null;
        }
    }


}
