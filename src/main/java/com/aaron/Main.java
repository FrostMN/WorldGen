package com.aaron;

import com.aaron.GUIs.WorldGenOptions;
import com.aaron.Map.Settlement;
import com.aaron.Sprite.Sprite;
import com.aaron.utilities.Size;

public class Main {

    public static void main(String[] args) {
	// write your code here

        WorldGenOptions gui = new WorldGenOptions();

//        World world = new World(Size.SMALL, (long) 124);
//        world.printWorld();
//
//        WorldGenGUI gui = new WorldGenGUI(world);


        Settlement city = new Settlement(Size.SMALL);
        Sprite hero = new Sprite("Darron");

        city.printSettlement();
    }
}
