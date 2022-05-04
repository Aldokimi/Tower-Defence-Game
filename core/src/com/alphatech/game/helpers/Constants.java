package com.alphatech.game.helpers;

import java.awt.geom.Point2D;

public class Constants {

    // Map
    public static final int PLACEHOLDER_SIZE = 32;
    public static final int MAP_SIZE = 32 * PLACEHOLDER_SIZE;
    public static final int VIEW_SIZE = 38 * PLACEHOLDER_SIZE;
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 896;
    public static final float TIMER_CAPACITY = 174f;

    // Units
    public static final Point2D.Float UNIT_SIZE = new Point2D.Float(60, 45);// width,heigth
    public static final int UNIT_SPEED = 20;
    public static final int FULL_UNIT_HEALTH_POINTS = 1000;

    // Towers
    public static final String NORMAL_TOWER = "NormalTower";
    public static final String MULTIATTACK_TOWER = "MultiAttackTower";
    public static final String MAGIC_TOWER = "MagicTower";

    // Player
    public static final int TURN_TIME = 30;
    public static final int INIT_HEALTH = 1000;
    public static final int INIT_GOLD_COUNT = 25000;

    // Gaining gold
    public static final int GOLDMINE_GAIN = 100;
    public static final int TREASURE_CHEST = 500;

    // Spending gold
    public static final int BUILD_NORMAL_TOWER = 1000;
    public static final int BUILD_GOLDMINE = 3000;
    public static final int BUILD_MULTIATTACK_TOWER = 1300;
    public static final int BUILD_MAGIC_TOWER = 1500;
    public static final int TRAIN_NORMAL_SOLDIER = 500;
    public static final int TRAIN_CRAZY_SOLDIER = 800;

    // Paths
    public enum PathNum {
        FIRST,
        SECOND,
        THIRD,
        FORTH,
        CRAZY
    }

    // Fire shooting 
    public static final float PROXIMITY_RANGE = PLACEHOLDER_SIZE;


    //Treasure timing
    public static final int INIT_APPEARING_TIME = 0;
    public static final int MAX_APPEARING_TIME = 20;
    public static final int INIT_DISAPPEARING_TIME = 10;


}
