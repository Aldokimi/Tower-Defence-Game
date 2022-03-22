package com.alphatech.game.utils;

import com.brashmonkey.spriter.Point;

import com.alphatech.game.helpers.Constants;
import com.badlogic.gdx.graphics.Texture;

public class Unit {
    protected int speed;
    protected Texture unitType;
    protected Point position;

    public Unit(Point position) {
        this.position = position;
        this.speed = Constants.UNIT_SPEED;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setTexture(Texture unitType) {
        this.unitType = unitType;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Point getPosition() {
        return this.position;
    }

    public Texture getTexture() {
        return this.unitType;
    }

    public void attackCastle() {

    }

    public void collectTreasure() {

    }
}