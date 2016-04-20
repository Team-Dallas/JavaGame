package game;

import Constants.Const;
import ImageLoader.Assets;
import ImageLoader.SpriteSheet;
import ImageLoader.gfx;
import display.Display;
import display.GameOverScreen;
import states.GameState;
import states.State;
import states.StateManager;
import utilities.HighScore;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

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
    private int backgroundFrames;
    private long time = System.nanoTime();
    private long delay;

    public static Player player;
    private ArrayList<Enemy> enemies;
    private static Rectangle enemyBoundingBox;


    private State gameState;

    /**
     * Constructor for creating new game with param
     *
     * @param title  of the game
     * @param width  of the game
     * @param height of the game
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
        this.backgroundFrames = Const.TOTAL_BACKGROUND_FRAMES;
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
        this.backgroundFrames--;
        if (this.backgroundFrames == 0) {
            this.backgroundFrames = Const.TOTAL_BACKGROUND_FRAMES;
        }
//Check all changed variables for the player
        player.tick();
        long elapsed = (System.nanoTime() - time) / (Const.DRAWING_DELAY - (4000 * Enemy.getDifficulty()));
//Check if enough time is passed to add new enemy or if spawnSpot is available
        if (elapsed > (this.delay - Enemy.getDifficulty() * 300)&& Road.isSpotAvailable()) {
            enemies.add(new Enemy());
            time = System.nanoTime();
        }
//Loop for checking all changed variables for the enemies and check if they intersects with the player
        for (int j = 0; j < enemies.size(); j++) {
            enemies.get(j).tick();
            if(enemies.get(j).getY() > Const.ROAD_BOTTOM_BORDER + 200){
                Road.getOccupiedSpawnPoints()[Const.SPAWN_POINTS.indexOf(enemies.get(j).getX())] = false;
                player.setScore(player.getScore() + 50);
                enemies.remove(j);
                continue;
            }
            enemyBoundingBox = enemies.get(j).getEnemyRectangle();
            if (player.getBoundingBox().intersects(enemyBoundingBox)) {
                reset();
                if(player.getLives() == 0){
                    gameOver();
                }
                break;
            }
        }
    }

    //Method for drawing everything on the canvas
    private void render() throws IOException {
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
        this.graphics.drawImage(this.background.crop(0, 0 + this.backgroundFrames * this.height, width, height), 0, 0, null);
        //Player Added
        player.render(this.graphics);
        //Enables the buffer
        for (int j = 0; j < enemies.size(); j++) {
            enemies.get(j).render(this.graphics);
        }
        if (StateManager.getState() != null) {
            StateManager.getState().render(this.graphics);
        }
        //Creating life and score stats.
        this.graphics.setFont(new Font("Verdana", Font.BOLD, 22));
        this.graphics.setColor(Color.RED);
        this.graphics.drawString(String.format("LIVES: %d",player.getLives()),60,30);
        this.graphics.setColor(Color.BLUE);
        this.graphics.drawString(String.format("DIFFICULTY LEVEL: %d",Enemy.getDifficulty()),300,30);
        this.graphics.setColor(Color.orange);
        this.graphics.drawString(String.format("SCORE: %d",player.getScore()),600,30);
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
        System.exit(0);
    }

    private void gameOver() {

        HighScore.writeHighscore(this.player.getScore());
        this.display.closeCanvas();

        new GameOverScreen();
        stop();
    }

    private void reset() {
        Road.setOccupiedSpawnPoints(new boolean[4]);
        enemies.clear();
        player.setX(Const.PLAYER_START_POINT_X);
        player.setY(Const.PLAYER_START_POINT_Y);
        player.setLives(player.getLives() - 1);
    }

}
