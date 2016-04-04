package ImageLoader;

import com.sun.deploy.ui.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
    public static BufferedImage player, enemy;

    public static void init(){
        try {
            SpriteSheet spriteSheet = new SpriteSheet(gfx.loader("/cars/carsSpriteSheet.png"));

            player = spriteSheet.crop(0, 0, 105, 106);
            enemy = spriteSheet.crop(105, 2*106, 105, 106 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
