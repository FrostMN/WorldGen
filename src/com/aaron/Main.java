package com.aaron;

import com.aaron.Map.Settlement;
import com.aaron.Map.World;
import com.aaron.Sprite.Sprite;

public class Main {

    public static void main(String[] args) {
	// write your code here
        World world = new World(Size.SMALL);
        world.printWorld();

        WorldGenGUI gui = new WorldGenGUI(world);


        Settlement city = new Settlement(Size.SMALL);
        Sprite hero = new Sprite("Darron");

        city.printSettlement();
    }
}
