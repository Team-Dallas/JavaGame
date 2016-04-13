package Constants;

import java.util.*;

public class Const {
    public static final int PLAYER_VELOCITY = 8;
    public static final int PLAYER_START_POINT_X = 620;
    public static final int PLAYER_START_POINT_Y = 400;
    public static final int PLAYER_WIDTH = 105;
    public static final int PLAYER_HEIGHT = 106;

    public static final int ENEMY_VELOCITY = 10;
    public static final int ENEMY_START_POINT_Y = -200;
    public static final int ENEMY_END_POINT_X = 700;


    public static final List<Integer> SPAWN_POINTS = new ArrayList<>(Arrays.asList(85, 260, 430, 610));
    public static final boolean[] OCCUPIED_SPAWN_POINTS = new boolean[4];
    public static final int ROAD_LEFT_BORDER = 50;
    public static final int ROAD_RIGHT_BORDER = 655;
    public static final int ROAD_BOTTOM_BORDER = 400;
    public static final int ROAD_TOP_BORDER = -5;


    public static final int FPS = 50;
    public static final int DELAY = 2000;
    public static final int TOTAL_BACKGROUND_FRAMES = 23;
    public static final long DRAWING_DELAY = 400000;
}
