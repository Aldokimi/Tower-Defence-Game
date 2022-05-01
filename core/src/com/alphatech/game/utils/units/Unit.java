package com.alphatech.game.utils.units;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.utils.Player;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.geom.Point2D;

public class Unit {
    protected int speed;
    protected String currentState; // current unit state {IDLE, WALK, ATTACK, DEAD}
    protected Point2D.Float position;
    protected Animation<TextureRegion> animation;
    protected int nextPathLevel; // index of the next element in `paths` array
    protected boolean isXaxis;
    protected Constants.PathNum path;
    protected int health;
    protected boolean movedInPath;
    protected int timeToDie;
    protected Boolean fromBarrack;
    protected String color;

    public Unit(Point2D.Float position) {
        this.position = position;
        this.speed = Constants.UNIT_SPEED;
        this.currentState = "IDLE";
        this.health = 1000; // Constants.FULL_UNIT_HEALTH_POINTS;
        this.movedInPath = false;
        this.fromBarrack = false;
        this.color = null;
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

    public void setMovedInPath(boolean movedInPath) {
        this.movedInPath = movedInPath;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setState(String state) {
        this.currentState = state;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public String getClassName() {
        return "Unit";
    }

    public boolean getIsXaxis() {
        return isXaxis;
    }

    public boolean getMovedInPath() {
        return movedInPath;
    }

    public Boolean getFromBarrack() {
        return fromBarrack;
    }

    public void setFromBarrack(Boolean fromBarrack) {
        this.fromBarrack = fromBarrack;
    }

    public Constants.PathNum getPath() {
        return path;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Point2D.Float getPosition() {
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

    public void attackCastle(Player p, int damage) {
        p.decreaseHealthBy(damage);
    }

    public void collectTreasure() {

    }

    public int getHealth() {
        return health;
    }

    /**
     * Make each unit move one by one (no collapse).
     * must be synced with turnToMove variable in the UnitSettings
     * 
     * @param timeToMove
     */
    public void moveInPath(int timeToMove, int unitsArraySize, UnitSettings setting, String PlayerColor) {
        if (timeToMove >= unitsArraySize && !getMovedInPath() && PlayerColor.equals("RED")) {
            setMovedInPath(true);
            setting.setTurnToRedMove(0);
        }
        if (timeToMove >= unitsArraySize && !getMovedInPath() && PlayerColor.equals("BLUE")) {
            setMovedInPath(true);
            setting.setTurnToBlueMove(0);
        }

    }

    public int getTimeToDie() {
        return timeToDie;
    }

    public void setTimeToDie(int timeToDie) {
        this.timeToDie = timeToDie;
    }

    public Boolean isAlive() {
        return this.health > 0;
    }
}