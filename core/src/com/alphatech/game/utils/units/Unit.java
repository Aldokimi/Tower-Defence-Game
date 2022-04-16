package com.alphatech.game.utils.units;

import com.alphatech.game.helpers.Constants;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Point;

public class Unit {
    protected int speed;
    protected String currentState; // current unit state {IDLE, WALK, ATTACK, DEAD}
    protected Point position;
    protected Animation<TextureRegion> animation;
    protected int nextPathLevel; // index of the next element in `paths` array
    protected boolean isXaxis;
    protected Constants.PathNum path;
    protected int health;

    public Unit(Point position) {
        this.position = position;
        this.speed = Constants.UNIT_SPEED;
        this.currentState = "IDLE";
        this.health = 1000; // Constants.FULL_UNIT_HEALTH_POINTS;
    }

    public void setNextPathLevel(int nextPathLevel) {
        this.nextPathLevel = nextPathLevel;
    }

    public void setIsXaxis(boolean isXaxis) {
        this.isXaxis = isXaxis;
    }

    public void setPath(Constants.PathNum path) {
        this.path = path;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setState(String state) {
        this.currentState = state;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public boolean getIsXaxis() {
        return isXaxis;
    }

    public Constants.PathNum getPath() {
        return path;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Point getPosition() {
        return this.position;
    }

    public String getCurrentState() {
        return this.currentState;
    }

    public int getNextPathLevel() {
        return nextPathLevel;
    }

    public Animation<TextureRegion> getAnimation() {
        return this.animation;
    }

    public void attackCastle() {

    }

    public void collectTreasure() {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Boolean isAlive() {
        return this.health > 0;
    }
}