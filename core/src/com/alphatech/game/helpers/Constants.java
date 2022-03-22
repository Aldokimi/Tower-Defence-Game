package com.alphatech.game.helpers;

import com.brashmonkey.spriter.Point;

public class Constants {

    // Units
    public static final Point UNIT_SIZE = new Point(60, 45);// width,heigth
    public static final int UNIT_SPEED = 20;

    // Map
    public static final int PLACEHOLDER_SIZE = 32;
    public static final int MAP_SIZE = 32 * PLACEHOLDER_SIZE;
    public static final int VIEW_SIZE = 38 * PLACEHOLDER_SIZE;
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 896;

    // Player
    public static final int TURN_TIME = 30;
    public static final int INIT_HEALTH = 100;
    public static final int INIT_GOLD_COUNT = 10000;

}
