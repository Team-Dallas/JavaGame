package game;

import ImageLoader.Assets;

import java.awt.*;
import java.util.*;

public class Enemy {
    private int x, y;
    private int velocity;
    private final int[] spawnPoints = {85, 260, 435, 610};
    private Random rand;

    public Enemy() {
        this.rand = new Random();
        this.x = this.spawnPoints[this.rand.nextInt(4)];
        this.y = -200;
        this.velocity = 2;
        Array
    }


    public void tick() {

        this.y += this.velocity;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.enemy, this.x, this.y, null);
    }
}