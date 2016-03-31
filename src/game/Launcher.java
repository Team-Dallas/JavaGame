package game;

import display.Display;

/**
 * Class for launching the game
 */
public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Java game", 800, 600);
        game.start();
    }
}
