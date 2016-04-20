package game;

import Constants.Const;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Road {
    private static boolean[] occupiedSpawnPoints = Const.OCCUPIED_SPAWN_POINTS;


    public static void setOccupiedSpawnPoints(boolean[] occupiedSpawnPoints) {
        Road.occupiedSpawnPoints = occupiedSpawnPoints;
    }

    public static boolean[] getOccupiedSpawnPoints() {
        return occupiedSpawnPoints;
    }

    public static boolean isSpotAvailable() {
        return !Road.getOccupiedSpawnPoints()[0] || !Road.getOccupiedSpawnPoints()[1] ||
                !Road.getOccupiedSpawnPoints()[2] || !Road.getOccupiedSpawnPoints()[3];
    }
}