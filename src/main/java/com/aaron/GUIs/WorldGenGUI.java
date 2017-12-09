package com.aaron.GUIs;

import com.aaron.Map.Region;
import com.aaron.Map.World;
import com.aaron.utilities.Size;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

    private Integer RegionTIleSize;

    public WorldGenGUI(World w) {
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(1200, 800));
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.worldData = w;
        this.RegionTIleSize = 48;

        System.out.println(w.getSize());

        addEventListeners();
        createView(w.getSize());
        pack();

        // centering window taken from
        // https://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        pack();
        setVisible(true);
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
                JButton btn = new JButton();
                btn.setMargin(buttonMargin);

                Border emptyBorder = BorderFactory.createEmptyBorder();
                btn.setBorder(emptyBorder);

                Integer[] worldCords = {0, 0};
                Integer[] regionCords = {i, j};

                Region reg = worldData.getRegion(0, 0);

                String tileType = reg.getTileType(i, j);

                Image tileImage = reg.getTileImage(i, j);
                Image scaledTile = tileImage.getScaledInstance(this.RegionTIleSize,
                        this.RegionTIleSize, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon( scaledTile );

                btn.setIcon(icon);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        editTile(worldData, worldCords, regionCords, tileType);

                    }
                });
                regionViewSquares[i][j] = btn;
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

                Border emptyBorder = BorderFactory.createEmptyBorder();
                b.setBorder(emptyBorder);


                try {
                    Image img = worldData.getRegionImage(i, j);
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(16,16, Image.SCALE_DEFAULT));

                    String cords = "Y: " + i + ", Y: " + j;


                    b.setIcon(icon);

                    int x = i;
                    int y = j;

                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println( "World: " + cords );

                            // TODO implement this method
                            setRegionView(x, y);
                        }
                    });
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

    void setRegionView(Integer x, Integer y) {
        int dimensions = worldData.getDimensions();

        region.removeAll();

        region.setLayout(new GridLayout(dimensions, dimensions));
        region.setBorder(new LineBorder(Color.BLACK));

        regionViewSquares = new JButton[dimensions][dimensions];
        regionViewImage = new Image[dimensions][dimensions];

        Region r = worldData.getRegion(x, y);

        System.out.println(r);

        System.out.println(r.getMap());

        System.out.println(r.getTileType(x, y));


        System.out.println("in setRegionView(x, y)");

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);


                Border emptyBorder = BorderFactory.createEmptyBorder();
                b.setBorder(emptyBorder);


                Integer[] worldCords = {x, y};
                Integer[] regionCords = {i, j};

                Image tileImage = r.getTileImage(i, j);

                System.out.println("in setRegionView(x, y) print tileImage: ");
                System.out.println(tileImage);

                System.out.println("in setRegionView(x, y) print itle type");
                String tileType = r.getTileType(i, j);

                System.out.println("in setregion print itle type");

                Image scaledTile = tileImage.getScaledInstance(this.RegionTIleSize,
                        this.RegionTIleSize, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon( scaledTile );

                b.setIcon(icon);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        editTile(worldData, worldCords, regionCords, tileType);

                    }
                });

                regionViewSquares[i][j] = b;
            }
        }

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                region.add(regionViewSquares[i][j]);
            }
        }
        pack();
    }

    private void editTile(World world, Integer[] WorldCords, Integer[] regionCords, String tileType) {

        SetTileGUI TileGui = new SetTileGUI(this, world, WorldCords, regionCords, tileType);

        JDialog mod = new JDialog(TileGui, Dialog.ModalityType.APPLICATION_MODAL);

//        setRegionView(WorldCords[0], WorldCords[1]);

        // TODO need to make the gui affect the map

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
