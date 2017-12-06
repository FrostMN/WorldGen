package com.aaron.GUIs;

import com.aaron.Map.World;
import com.aaron.utilities.Size;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class WorldGenGUI extends JFrame{
    private JPanel rootPanel;
    private JPanel region;
    private JPanel world;

    private World worldData;

    private JButton[][] regionViewSquares;
    private JButton[][] worldViewSquares;

    private Image[][] regionViewImage;
    private JLabel[][] worldViewImage;

    private HashMap<String, Image> worldMap;

    public WorldGenGUI(World w) {
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(1000, 500));
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.worldData = w;

        pack();
        setVisible(true);

        System.out.println(w.getSize());

        addEventListeners();
        createView(w.getSize());
        pack();

    }

    private void createView(Size size) {
        System.out.println("in createView()");
        System.out.println(size);
        switch (size) {
            case SMALL:
                createRegionView(16);
                createWorldView(16);
                break;
            case MEDIUM:
                createRegionView(18);
                createWorldView(18);
                break;
            case LARGE:
                createRegionView(20);
                createWorldView(20);
                break;
        }
    }

    private void createRegionView(Integer dimensions) {
        region.setLayout(new GridLayout(dimensions, dimensions));
        region.setBorder(new LineBorder(Color.BLACK));

        regionViewSquares = new JButton[dimensions][dimensions];
        regionViewImage = new Image[dimensions][dimensions];


        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
//                ImageIcon icon = worldData.getRegion(0, 0).getTileIcon(i, j);
                b.setIcon(icon);

                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                regionViewSquares[i][j] = b;
            }
        }

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                region.add(regionViewSquares[i][j]);
            }
        }
    }

    private void createWorldView(Integer dimensions) {
        world.setLayout(new GridLayout(dimensions, dimensions));
        world.setBorder(new LineBorder(Color.BLACK));

        worldViewSquares = new JButton[dimensions][dimensions];
        worldViewImage = new JLabel[dimensions][dimensions];

        worldMap = new HashMap<>();

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                try {


                    Image img = worldData.getRegionImage(i, j);


//                    Image img = ImageIO.read(getClass().getResource("images/waterTile.png"));
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));

//                    ImageIcon icon = worldData.getRegionIcon(i, j);

                    b.setIcon(icon);
                    worldViewSquares[i][j] = b;

                    worldMap.put("world_" + i + "_" + j, img);

                } catch ( Exception e ) {

                }
            }
        }

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                world.add(worldViewSquares[i][j]);


//                world.add(new JLabel().setIcon(new ImageIcon(worldMap.get("world_" + i + "_" + j))));
            }
        }
    }

    private void addEventListeners() {
        // the following 2 methods where adapted from
        // http://www.javacreed.com/how-to-capture-key-events-with-jframe-or-window/
        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    Character ch = e.getKeyChar();
                    switch (ch) {
                        case 'w':
                            moveNorth();
                            break;
                        case 'a':
                            moveWest();
                            break;
                        case 's':
                            moveSouth();
                            break;
                        case 'd':
                            moveEast();
                            break;
                        case 'e':
                            System.out.println("inventory");
                            break;
                        case 'r':
                            System.out.println("rest");
                            break;
                        case '<':
                            System.out.println("down");
                            break;
                        case '>':
                            System.out.println("up");
                            break;
                        default:
                            System.out.println(ch);
                            break;
                    }
                }
                // Pass the KeyEvent to the next KeyEventDispatcher in the chain
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    private void moveNorth() {
        System.out.println("in moveNorth()");
    }

    private void moveWest() {
        System.out.println("in moveWest()");
    }

    private void moveSouth() {
        System.out.println("in moveSouth()");
    }

    private void moveEast() {
        System.out.println("in moveEast()");
    }

}
