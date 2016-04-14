package game;

import Constants.Const;
import ImageLoader.Assets;

import java.awt.*;

public class Player {
    private int x, y;
    private int velocity;
    private int width, height;

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
        this.boundingBox = new Rectangle(this.width, this.height);

        goingUp = false;
        goingDown = false;
        goingLeft = false;
        goingRight = false;
    }

    //Checks if the player intersects with something
    public boolean Collides(Rectangle r) {
        if (this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }

    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

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
    }

    public void render(Graphics g) {
        g.drawImage(Assets.player, this.x, this.y, null);
    }

}
