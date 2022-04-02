package com.alphatech.game.utils;

import java.util.ArrayList;

import com.alphatech.game.helpers.Constants;

public class Player {

    private int turnTime;
    private int gold;
    private boolean turn;
    private int health;
    public ArrayList<Unit> units;
    public ArrayList<Tower> towers;

    public Player() {
        this.turnTime = Constants.TURN_TIME;
        this.gold = Constants.INIT_GOLD_COUNT;
        this.turn = false;
        this.health = Constants.INIT_HEALTH;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    public Player(int turnTime, int gold, boolean turn, int health) {
        this.turnTime = turnTime;
        this.gold = gold;
        this.turn = turn;
        this.health = health;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    // Setters
    public void setTurnTime(int time) {
        this.turnTime = time;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Getters
    public int getTurnTime() {
        return this.turnTime;
    }

    public int getGold() {
        return this.gold;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * Start player's turn
     */
    public void startTurn() {
        this.turn = true;
    }

    /**
     * End player's turn
     */
    public void endTurn() {
        this.turn = false;
    }

    public void buildTower() {

    }

    public void trainUnit(Unit unit) {

    }

    public void spendGold() {

    }

    public void gainGold(int goldGained) {

    }

    public boolean hasWon() {
        if (this.health == 0) {

            return false;
        }
        return true;
    }

    public void buildGoldMine() {

    }

}
