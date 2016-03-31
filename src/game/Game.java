package game;

import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private String title;
    private int width;
    private int height;

    private Thread thread;
    private boolean isRunning;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;

    }

    /**
     * Initializes all the graphics and it will get
     * everything ready for our game
     */
    private void init() {
        //Initializing a new Display
        this.display = new Display(title, width, height);
    }
    // Method for updating all the variables in the game
    private void tick() {

    }
    //Method for drawing everything on the canvas
    private void render() {
        //Setting the bufferStrategy to be the one used in our canvas
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
        //Enables the buffer
        bufferStrategy.show();
        //Shows everything stored in the Graphics object
        graphics.dispose();
    }
    //Implementing the interface's method
    @Override
    public void run() {
        init();

        while (isRunning) {
            tick();
            render();
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
