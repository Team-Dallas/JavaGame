package game;

import ImageLoader.Assets;
import display.Display;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy {
    private int x, y;
    private int velocity;
    private Random rand;
    private int randomIndex;
    private BufferedImage enemy;


    public Enemy() {
        this.rand = new Random();
        setRandomIndex(this.rand.nextInt(4));
        this.x = Road.getSpawnPoints().get(this.randomIndex);
        Road.getOccupiedSpawnPoints()[this.randomIndex] = true;
        this.y = -200;
        this.velocity = 10;
    }

    public void setRandomIndex(int randomIndex) {
        do {
            this.randomIndex = this.rand.nextInt(4);
        }
        while (Road.getOccupiedSpawnPoints()[this.randomIndex]);
    }

    public void tick() {

        this.y += this.velocity;
    }

    public void render(Graphics g) {
        int allEnemiesCount = Assets.getAllEnimies().size();
        if (this.y == 600) {
            Road.getOccupiedSpawnPoints()[Road.getSpawnPoints().indexOf(this.x)] = false;
        }
        if (this.y == -200 + this.velocity) {
            enemy = Assets.getAllEnimies().get(this.rand.nextInt(allEnemiesCount));
        }
        g.drawImage(enemy, this.x, this.y, null);
    }
}