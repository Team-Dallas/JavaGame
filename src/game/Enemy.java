package game;

import Constants.Const;
import ImageLoader.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy {
    private int x, y;
    private int velocity;
    private int width, height;
    private Random rand;
    private int randomIndex;
    private BufferedImage enemy;
    private Rectangle enemyRectangle;


    public Enemy() {
        this.rand = new Random();
        setRandomIndex(this.rand.nextInt(4));
        this.x = Const.SPAWN_POINTS.get(this.randomIndex);
        Road.getOccupiedSpawnPoints()[randomIndex] = true;
        this.y = Const.ENEMY_START_POINT_Y;
        this.width = Const.ENEMY_WIDTH;
        this.height = Const.ENEMY_HEIGHT;
        this.velocity = Const.ENEMY_VELOCITY;
        this.enemyRectangle = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getX() {
        return x;
    }

    public Rectangle getEnemyRectangle() {
        return this.enemyRectangle;
    }


    public void setRandomIndex(int randomIndex) {
        do {
            this.randomIndex = this.rand.nextInt(4);
        }
        while (Road.getOccupiedSpawnPoints()[this.randomIndex]);
    }

    public void tick() {
        this.y += this.velocity;
        this.enemyRectangle.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        int allEnemiesCount = Assets.getAllEnemies().size();
        if (this.y == Const.ENEMY_END_POINT_X) {
            Road.getOccupiedSpawnPoints()[Const.SPAWN_POINTS.indexOf(this.x)] = false;
        }
        if (this.y == Const.ENEMY_START_POINT_Y + this.velocity) {
            enemy = Assets.getAllEnemies().get(this.rand.nextInt(allEnemiesCount));
        }
        g.drawImage(enemy, this.x, this.y, null);
    }
}