package Constants;

import java.util.*;

/**
 * Class containing all constant variables for the game
 */
public class Const {
    public static final int PLAYER_VELOCITY = 8;
    public static final int PLAYER_START_POINT_X = 355;
    public static final int PLAYER_START_POINT_Y = 400;
    public static final int PLAYER_WIDTH = 90;
    public static final int PLAYER_HEIGHT = 170;
    public static final int LIVES = 5;

    public static final int ENEMY_VELOCITY = 5;
    public static final int ENEMY_START_POINT_Y = -200;
    public static final int ENEMY_END_POINT_Y = 700;
    public static final int ENEMY_WIDTH = 90;
    public static final int ENEMY_HEIGHT = 170;

    public static final List<Integer> SPAWN_POINTS = new ArrayList<>(Arrays.asList(85, 260, 430, 610));
    public static final boolean[] OCCUPIED_SPAWN_POINTS = new boolean[4];
    public static final int ROAD_LEFT_BORDER = 50;
    public static final int ROAD_RIGHT_BORDER = 655;
    public static final int ROAD_BOTTOM_BORDER = 400;
    public static final int ROAD_TOP_BORDER = -5;

    public static final int FPS = 50;
    public static final int DELAY = 4000;
    public static final int TOTAL_BACKGROUND_FRAMES = 23;
    public static final long DRAWING_DELAY = 370000;
}
