package ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class gfx {
    public static BufferedImage loader(String path) throws IOException {

        return ImageIO.read(gfx.class.getResource(path));
    }
}
