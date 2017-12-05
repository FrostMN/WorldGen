package com.aaron.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Tile {
    private int type;


    Tile(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (this.type) {
            case 0:
                return "#";
            case 1:
                return "_";
            case 2:
                return "^";
            default:
                return " ";
        }
    }

    ImageIcon getIcon() {
        return new ImageIcon(getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
    }

    private Image getImage() {
        String img = "";
        try {
            switch (this.type) {
                case 0:
                    return ImageIO.read(getClass().getResource("../images/grassTile.png"));
                case 1:
                    return ImageIO.read(getClass().getResource("../images/grassTile.png"));
                case 2:
                    return ImageIO.read(getClass().getResource("../images/mountainTile.png"));
                case 3:
                    return ImageIO.read(getClass().getResource("../images/grassTile.png"));
                default:
                    return ImageIO.read(getClass().getResource("../images/grassTile.png"));
            }
        } catch (Exception e) {
            return null;
        }
    }

}
