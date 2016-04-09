package ImageLoader;

import com.sun.deploy.ui.ImageLoader;
import game.Enemy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Assets {
    public static BufferedImage player, enemy, taxi, bus;
    private static Random rand;

    public static void init() {
        try {
            SpriteSheet spriteSheet = new SpriteSheet(gfx.loader("/cars/carsSpriteSheet.png"));

            player = spriteSheet.crop(0, 0, 100, 200);
//            enemy = spriteSheet.crop(100, 0, 100, 200);
            bus = spriteSheet.crop(400, 0, 100, 200);
            taxi = spriteSheet.crop(500, 0, 100, 200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
