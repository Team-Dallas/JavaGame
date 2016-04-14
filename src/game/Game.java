package game;

import Constants.Const;
import ImageLoader.Assets;
import ImageLoader.SpriteSheet;
import ImageLoader.gfx;
import display.Display;
import states.GameState;
import states.State;
import states.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements Runnable {
    private String title;
    private int width;
    private int height;

    private Thread thread;
    private boolean isRunning;

    private Display display;
    private InputHandler inputHandler;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private SpriteSheet background;
    private int bckgFrames;
    private long time = System.nanoTime();
    private long delay;
    private ArrayList<Enemy> enemies;

    public static Player player;

    public static Rectangle enemy;

    private State gameState;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
        this.bckgFrames = Const.TOTAL_BACKGROUND_FRAMES;
        this.delay = Const.DELAY;
    }

    /**
     * Initializes all the graphics and it will get
     * everything ready for our game
     */
    private void init() throws IOException {
        //Initializing a new Display
        this.display = new Display(title, width, height);
        this.background = new SpriteSheet(gfx.loader("/images/RoadTile3.png"));
        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        gameState = new GameState();
        StateManager.setState(gameState);

        player = new Player();
        enemies = new ArrayList<>();
    }

    // Method for updating all the variables in the game
    private void tick() {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
        this.bckgFrames--;
        if (this.bckgFrames == 0) {
            this.bckgFrames = Const.TOTAL_BACKGROUND_FRAMES;
        }
        player.tick();
        long elapsed = (System.nanoTime() - time) / Const.DRAWING_DELAY;

        if (elapsed > this.delay && Road.isSpotAvailable()) {
            enemies.add(new Enemy());
            time = System.nanoTime();
        }

        for (int j = 0; j < enemies.size(); j++) {
            enemies.get(j).tick();
        }
        if (player.Collides(enemy)){
            System.out.printf("game over");
        }
    }

    //Method for drawing everything on the canvas
    private void render() throws IOException {
        // Bool is for determing wich background to draw.
        boolean isFirstBckg = true;
        //Setting the bufferStrategy to be the one used in our canvas.
        //Gets the number of buffers that the canvas should use.
        this.bufferStrategy = display.getCanvas().getBufferStrategy();
        //If our bufferStrategy doesn't know how many buffers to use
        //we create some manually
        if (bufferStrategy == null) {
            //Create 2 buffers and then return out of the method to prevent errors
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        //Create the graphics related to the bufferStrategy
        this.graphics = this.bufferStrategy.getDrawGraphics();
        //Create and draw the animated background
        this.graphics.drawImage(this.background.crop(0, 0 + this.bckgFrames * this.height, width, height), 0, 0, null);
        //Player Added
        player.render(this.graphics);
        //Enemy Added(for test)
        // this.graphics.drawImage(Assets.blackCar, 480, 420, null);
        //Enables the buffer
        for (int j = 0; j < enemies.size(); j++) {
            enemies.get(j).render(this.graphics);
        }
        if (StateManager.getState() != null) {
            StateManager.getState().render(this.graphics);
        }
        bufferStrategy.show();
        //Shows everything stored in the Graphics object
        this.graphics.dispose();
    }

    //Implementing the interface's method
    @Override
    public void run() {
        try {
            this.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int fps = Const.FPS;
        double ticksPerFrame = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / ticksPerFrame;
            lastTimeTicked = now;
            if (delta > 0) {
                tick();
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                delta--;

            }


        }
        //Calls the stop method to stop everything
        this.stop();
    }

    //Creating a start method for the Thread to start our game
    public synchronized void start() {
        if (!this.isRunning) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        try {
            if (isRunning) {
                isRunning = false;
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
