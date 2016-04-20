package game;

import display.Display;
import display.MenuScreen;

/**
 * Class for launching the game
 */
public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("BULGARIAN TRAFFIC", 800, 600);
        MenuScreen menuScreen = new MenuScreen(game);
        menuScreen.setVisible(true);
    }
}
