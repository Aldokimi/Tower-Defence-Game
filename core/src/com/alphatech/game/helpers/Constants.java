package com.alphatech.game.helpers;

import com.brashmonkey.spriter.Point;

public class Constants {

    // Units
    public static final Point UNIT_SIZE = new Point(60, 45);// width,heigth
    public static final int UNIT_SPEED = 20;
    public static final int FULL_UNIT_HEALTH_POINTS = 1000;

    // Map
    public static final int PLACEHOLDER_SIZE = 32;
    public static final int MAP_SIZE = 32 * PLACEHOLDER_SIZE;
    public static final int VIEW_SIZE = 38 * PLACEHOLDER_SIZE;
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 896;
    public static final float TIMER_CAPACITY = 174f;

    // Player
    public static final int TURN_TIME = 30;
    public static final int INIT_HEALTH = 1000;
    public static final int INIT_GOLD_COUNT = 10000;

    // Gaining gold
    public static final int GOLDMINE_GAIN = 50;
    public static final int TREASURE_CHEST = 500;

    // Spending gold
    public static final int BUILD_NORMAL_TOWER = 1000;
    public static final int BUILD_GOLDMINE = 3000;
    public static final int BUILD_MULTIATTACK_TOWER = 1300;
    public static final int BUILD_CRAZY_TOWER = 1500;
    public static final int TRAIN_NORMAL_SOLDIER = 500;
    public static final int TRAIN_CRAZY_SOLDIER = 800;

    //Paths
    public enum PathNum {
        FIRST,
        SECOND,
        THIRD,
        FORTH
    }
}
