package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Road {
    private static final ArrayList<Integer> SPAWN_POINTS = new ArrayList<Integer>() {{
        add(85);
        add(260);
        add(435);
        add(610);
    }};
    private static final boolean[] occupiedSpawnPoints = new boolean[4];

    public static ArrayList<Integer> getSpawnPoints() {
        return SPAWN_POINTS;
    }

    public static boolean[] getOccupiedSpawnPoints() {
        return occupiedSpawnPoints;
    }

}