package game;

import ImageLoader.Assets;
import display.Display;

import java.awt.*;
import java.util.Random;

public class Enemy {
    private int x, y;
    private int velocity;
    private Random rand;

    public Enemy() {
        this.rand = new Random();
        this.x = this.rand.nextInt(700);
        this.y = this.rand.nextInt(100);
        this.velocity = 2;
    }

    public void tick() {

        this.y += this.velocity;
    }
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, this.x, this.y, null);
    }
}