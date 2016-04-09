package ImageLoader;

import com.sun.deploy.ui.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
    public static BufferedImage player, enemy;

    public static void init(){
        try {
            SpriteSheet spriteSheet = new SpriteSheet(gfx.loader("/cars/carsSpriteSheet.png"));

            player = spriteSheet.crop(0, 0, 100, 200);
            enemy = spriteSheet.crop(100, 2*200, 100, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
