package display;

import game.InputHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creating display for the game
 */
public class Display extends Canvas {

    private String title;
    private int width;
    private int height;

    private JFrame frame;
    private Canvas canvas;

    /**
     * Constructor for creating the display and visualize the frame with param;
     * @param inputTitle - title will be visualized on the top of the screen
     * @param inputWidth - width of the display
     * @param inputHeight - height of the display
     */
    public Display(String inputTitle, int inputWidth, int inputHeight) {
        this.title = inputTitle;
        this.width = inputWidth;
        this.height = inputHeight;

        this.createFrame();
    }

    /**
     * Method for creating the frame and canvas of the game
     */
    private void createFrame() {
        //Set settings for creating the frame of the display
        Dimension dimensions = new Dimension(this.width, this.height);
        this.frame = new JFrame(this.title);
        this.frame.setSize(this.width, this.height);
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        //Set settings for creating the canvas where the game will be
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(dimensions);
        this.canvas.setMaximumSize(dimensions);
        this.canvas.setMinimumSize(dimensions);
        //Adding the canvas to the frame and then pack it together
        this.frame.add(canvas);
        this.frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
