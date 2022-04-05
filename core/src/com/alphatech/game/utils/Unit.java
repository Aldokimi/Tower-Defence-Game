package com.alphatech.game.utils;

import com.alphatech.game.helpers.Constants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.brashmonkey.spriter.Point;

public class Unit {
    protected int speed;
    protected Texture unitType;
    protected Point position;
    protected Animation<TextureRegion> animation;

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

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
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

    public Animation<TextureRegion> getAnimation() {
        return this.animation;
    }

    public void attackCastle() {

    }

    public void collectTreasure() {

    }

}