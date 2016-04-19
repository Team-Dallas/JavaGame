package game;

import display.Display;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static game.Game.*;

public class InputHandler implements KeyListener{
    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Player.goingUp = true;
            Menu.goingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Player.goingDown = true;
            Menu.goingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Player.goingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Player.goingRight = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }if (keyCode == KeyEvent.VK_ENTER){
            isModeSelected = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            Player.goingUp = false;
            Menu.goingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            Player.goingDown = false;
            Menu.goingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            Player.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            Player.goingRight = false;
        }if (keyCode == KeyEvent.VK_ENTER){
            isModeSelected = false;
        }
    }
}
