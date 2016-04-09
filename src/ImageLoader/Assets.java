package ImageLoader;

import com.sun.deploy.ui.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Assets {
    public static BufferedImage player, enemy, taxi, pickUp, camaro, bus;
    private static Random rand;
    private static ArrayList<BufferedImage> allEnimies;

    public static ArrayList<BufferedImage> getAllEnimies() {
        return allEnimies;
    }

    public static void init(){
        try {
            SpriteSheet spriteSheet = new SpriteSheet(gfx.loader("/cars/carsSpriteSheet.png"));
            rand = new Random();
            allEnimies = new ArrayList<BufferedImage>();

            player = spriteSheet.crop(0, 0, 100, 200);
            enemy = spriteSheet.crop(100, 0, 100, 200);
            allEnimies.add(enemy);
            taxi = spriteSheet.crop(500, 0, 100, 200);
            allEnimies.add(taxi);
            pickUp = spriteSheet.crop(300, 0, 100, 200);
            allEnimies.add(pickUp);
            camaro = spriteSheet.crop(200, 0, 100, 200);
            allEnimies.add(camaro);
            bus = spriteSheet.crop(400, 0, 100, 200);
            allEnimies.add(bus);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
