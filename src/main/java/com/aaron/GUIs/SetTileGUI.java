package com.aaron.GUIs;

import com.aaron.Map.Terrain;
import com.aaron.Map.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetTileGUI extends JFrame {
    private JPanel rootPanel;
    private JComboBox tileTypeComboBox;
    private JButton cancelButton;
    private JButton saveButton;
    private JLabel tileTypeLabel;

    private Integer world_x_cord;
    private Integer world_y_cord;
    private Integer region_x_cord;
    private Integer region_y_cord;

    World world;
    WorldGenGUI gui;

    SetTileGUI(WorldGenGUI gui, World world, Integer[] world_cords, Integer[] region_cords, String type) {
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(300, 300));

        world_x_cord = world_cords[0];
        world_y_cord = world_cords[1];

        region_x_cord = region_cords[0];
        region_y_cord = region_cords[1];

        this.world = world;
        this.gui = gui;

        pack();

        tileTypeLabel.setText(type);

        addEventListeners();
        loadFormData();
        
        pack();

        // centering window taken from
        // https://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setVisible(true);
    }

    private void loadFormData() {
        tileTypeComboBox.addItem("Grass");
        tileTypeComboBox.addItem("Road");
        tileTypeComboBox.addItem("Mountain");
        tileTypeComboBox.addItem("water");
        tileTypeComboBox.addItem("Wall");
        tileTypeComboBox.addItem("Cave");
    }

    private void addEventListeners() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer index = tileTypeComboBox.getSelectedIndex();
                Terrain type;
                switch (index) {
                    case 0:
                        type = Terrain.PLAINS;
                        break;
                    case 1:
                        type = Terrain.ROAD;
                        break;
                    case 2:
                        type = Terrain.MOUNTAIN;
                        break;
                    case 3:
                        type = Terrain.OCEAN;
                        break;
                    case 4:
                        type = Terrain.WALL;
                        break;
                    case 5:
                        type = Terrain.CAVE;
                        break;
                    default:
                        type = Terrain.REGION;
                }

                world.getRegion(world_x_cord, world_y_cord).setTile(region_x_cord, region_y_cord, type);
                gui.setRegionView(world_x_cord, world_y_cord);
                dispose();
            }
        });
    }
}
