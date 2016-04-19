package ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Assets {
    public static BufferedImage player, blackCar, taxi, pickUp, camaro, bus;

    private static ArrayList<BufferedImage> allEnemies;

    public static ArrayList<BufferedImage> getAllEnemies() {
        return allEnemies;
    }

    public static void init() {
        try {
            SpriteSheet spriteSheet = new SpriteSheet(gfx.loader("/cars/carsSpriteSheet.png"));
            allEnemies = new ArrayList<>();
            player = spriteSheet.crop(0, 0, 100, 200);
            blackCar = spriteSheet.crop(100, 0, 100, 200);
            camaro = spriteSheet.crop(200, 0, 100, 200);
            pickUp = spriteSheet.crop(300, 0, 100, 200);
            bus = spriteSheet.crop(400, 0, 100, 200);
            taxi = spriteSheet.crop(500, 0, 100, 200);
            allEnemies.add(blackCar);
            allEnemies.add(taxi);
            allEnemies.add(pickUp);
            allEnemies.add(camaro);
            allEnemies.add(bus);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(Assets.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
