package ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverPanel extends JPanel {
    BufferedImage bgPanel = Assets.load("/images/game_over.png");

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bgPanel, 0, 0, getWidth(), getHeight(), this);
    }

}
