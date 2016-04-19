package ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImagePanel extends JPanel {
    BufferedImage bgPanel = Assets.load("/images/image_layout.png");

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bgPanel, 0, 0, getWidth(), getHeight(), this);
    }
}
