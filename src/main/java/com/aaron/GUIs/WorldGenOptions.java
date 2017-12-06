package com.aaron.GUIs;

import com.aaron.Map.World;
import com.aaron.utilities.Size;
import com.aaron.database.DBWorld;
import com.aaron.database.WorldDB;
import com.aaron.utilities.Valid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class WorldGenOptions extends JFrame {


    private JPanel rootPanel;
    private JButton generateButton;
    private JTextArea worldNotesTextArea;
    private JComboBox<String> worldSizeComboBox;
    private JTextField worldSeedTextField;
    private JComboBox<DBWorld> worldsInDB;
    private JTextField worldNameTextField;
    private JButton saveButton;
    private JButton loadButton;
    private JButton generateSeedButton;

    public WorldGenOptions() {
        setupWindow();
        populateForms();
        addEventListeners();
    }

    private void setupWindow() {
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(1000, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        // centering window taken from
        // https://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
    }

    private void addEventListeners() {
        generateSeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worldSeedTextField.setText(generateSeed());
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer size = worldSizeComboBox.getSelectedIndex();
                World world;
                long seed = (long) Integer.parseInt(worldSeedTextField.getText());
                switch (size) {
                    case 0:
                        world = new World(Size.SMALL, seed);
                        break;
                    case 1:
                        world = new World(Size.MEDIUM, seed);
                        break;
                    default:
                        world = new World(Size.LARGE, seed);
                        break;
                }

                WorldGenGUI worldGui = new WorldGenGUI(world);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seed = worldSeedTextField.getText();
                String name = worldNameTextField.getText();
                String notes = worldNotesTextArea.getText();
                Integer size = worldSizeComboBox.getSelectedIndex();

                if ( Valid.seed(seed) && Valid.name(name) && Valid.notes(notes) ) {
                    WorldDB.saveCurrentWorld(seed, size, name, notes );
                }
                fillWorldsInDBComboBox();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO ask if youre sure you wanna not save old data
                Integer selected = worldsInDB.getSelectedIndex();
                DBWorld w = worldsInDB.getItemAt(selected);
                worldSizeComboBox.setSelectedIndex(w.getSize());
                worldSeedTextField.setText(Integer.toString(w.getSeed()));
                worldNameTextField.setText(w.getName());
                worldNotesTextArea.setText(w.getNotes());
            }
        });

        worldsInDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selected = worldsInDB.getSelectedIndex();
                if (selected == 0) {
                    loadButton.setEnabled(false);
                } else {
                    loadButton.setEnabled(true);
                }
            }
        });
    }

    private void populateForms() {
        worldSeedTextField.setText(generateSeed());
        fillWorldSizeComboBox();
        fillWorldsInDBComboBox();
    }

    private void fillWorldsInDBComboBox() {
        worldsInDB.removeAllItems();
        ArrayList<DBWorld> saved = WorldDB.getAllSavedWorlds();
        worldsInDB.addItem(new DBWorld(-1, "Select Saved", 0, 0, ""));
        for (DBWorld w: saved) {
            worldsInDB.addItem(w);
        }
    }

    private void fillWorldSizeComboBox() {
        String[] worldSizes = { "Small", "Medium", "Large" };
        for (String s: worldSizes) {
            worldSizeComboBox.addItem(s);
        }

    }

    private String generateSeed() {
        Random r = new Random();
        return Integer.toString(r.nextInt(999999999));
    }
}
