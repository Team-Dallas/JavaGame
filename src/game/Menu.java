package game;

import display.Display;

import java.awt.*;

public class Menu {
    public static boolean goingUp;
    public static boolean goingDown;
    public Rectangle playButton;
    public Rectangle quitButton;

    public Menu() {
        this.playButton = new Rectangle(Display.WIDTH + 90, 150, 270, 50);
        this.quitButton = new Rectangle(Display.WIDTH + 90, 250, 120, 50);
    }

    public void render(Graphics g, boolean isNewGame) {
        Font fnt0 = new Font("Need for Font", Font.PLAIN, 34);
        g.setFont(fnt0);
        if (isNewGame) {
            g.setColor(Color.red);
            g.drawString("NEW GAME", playButton.x + 10, playButton.y + 35);
            g.setColor(Color.white);
            g.drawString("QUIT", quitButton.x + 10, quitButton.y + 35);
        } else {
            g.setColor(Color.white);
            g.drawString("NEW GAME", playButton.x + 10, playButton.y + 35);
            g.setColor(Color.red);
            g.drawString("QUIT", quitButton.x + 10, quitButton.y + 35);
        }
    }

    public void tick() {

        if (Game.inMenu) {
            if (goingDown) {
                Game.inMenu = false;
            }
        } else {
            if (goingUp) {
                Game.inMenu = true;
            }
        }
    }
}
