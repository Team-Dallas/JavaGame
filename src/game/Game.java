package game;

import ImageLoader.Assets;
import ImageLoader.SpriteSheet;
import ImageLoader.gfx;
import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game implements Runnable {
    private String title;
    private int width;
    private int height;

    private Thread thread;
    private boolean isRunning;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private SpriteSheet background;
    private int i;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
        this.i = 23;
    }

    /**
     * Initializes all the graphics and it will get
     * everything ready for our game
     */
    private void init() throws IOException {
        //Initializing a new Display
        this.display = new Display(title, width, height);
        this.background = new SpriteSheet(gfx.loader("/images/RoadTile3.png"));
        Assets.init();
    }
    // Method for updating all the variables in the game
    private void tick() {
        this.i--;
        if (this.i == 0){
            this.i = 23;
        }
    }
    //Method for drawing everything on the canvas
    private void render() throws IOException {
        // Bool is for determing wich background to draw.
        boolean isFirstBckg = true;
        //Setting the bufferStrategy to be the one used in our canvas.
        //Gets the number of buffers that the canvas should use.
        this.bufferStrategy = this.display.getCanvas().getBufferStrategy();
        //If our bufferStrategy doesn't know how many buffers to use
        //we create some manually
        if (bufferStrategy == null) {
            //Create 2 buffers and then return out of the method to prevent errors
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }
        //Create the graphics related to the bufferStrategy
        graphics= this.bufferStrategy.getDrawGraphics();
        //Create and draw the animated background
        this.graphics.drawImage(this.background.crop(0, 0+this.i*this.height , width, height), 0, 0, null);
        //Player Added
        this.graphics.drawImage(Assets.player, 620, 420, null);
        //Enemy Added(for test)
        this.graphics.drawImage(Assets.enemy, 480, 420, null);
        //Enables the buffer
        bufferStrategy.show();
        //Shows everything stored in the Graphics object
        graphics.dispose();
    }
    //Implementing the interface's method
    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isRunning) {
            try {
                thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick();
            try {
                render();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Calls the stop method to stop everything
        stop();
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
