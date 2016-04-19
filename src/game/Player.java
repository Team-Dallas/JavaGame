package game;

import Constants.Const;
import ImageLoader.Assets;

import java.awt.*;

public class Player {
    private int x, y;
    private int velocity;
    private int width, height;
    private int lives;
    private int score;

    private Rectangle boundingBox;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;

    public Player() {
        this.x = Const.PLAYER_START_POINT_X;
        this.y = Const.PLAYER_START_POINT_Y;
        this.width = Const.PLAYER_WIDTH;
        this.height = Const.PLAYER_HEIGHT;
        this.velocity = Const.PLAYER_VELOCITY;
        this.lives = Const.LIVES;
        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
        this.score = 0;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {return this.score;}

    public void tick() {
        //Update the bounding box's position
        this.score++;
        if (goingUp && this.y > Const.ROAD_TOP_BORDER) {
            this.y -= this.velocity;
        }
        if (goingDown && this.y < Const.ROAD_BOTTOM_BORDER) {
            this.y += this.velocity;
        }
        if (goingLeft && this.x > Const.ROAD_LEFT_BORDER) {
            this.x -= this.velocity;
        }
        if (goingRight && this.x < Const.ROAD_RIGHT_BORDER) {
            this.x += this.velocity;
        }
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    public void render(Graphics g) {
        g.drawImage(Assets.player, this.x, this.y, null);
    }

}
