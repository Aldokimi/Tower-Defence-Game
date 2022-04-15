package com.alphatech.game.utils;

import java.util.ArrayList;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.utils.towers.*;

public class Player {

    private int turnTime;
    private int gold;
    private boolean turn;
    private int health;
    public int goldMineCounter;
    public ArrayList<Unit> units;
    private float timer;

    // Initialized in the game screen
    public NormalTower normalTower;
    public MultiAttackTower multiAttackTower;
    public MagicTower magicTower;
    public GoldMine goldMine;

    public Player() {
        this.turnTime = Constants.TURN_TIME;
        this.gold = Constants.INIT_GOLD_COUNT;
        this.turn = false;
        this.health = Constants.INIT_HEALTH;
        this.units = new ArrayList<>();
        this.goldMineCounter = 0;
        timer = 0f;
    }

    public Player(int turnTime, int gold, boolean turn, int health) {
        this.turnTime = turnTime;
        this.gold = gold;
        this.turn = turn;
        this.health = health;
        this.units = new ArrayList<>();
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

    /**
     * Build a tower
     * 
     * @param tower Tower type
     */
    public void buildTower(Tower tower) {
        if (tower instanceof NormalTower) {
            this.spendGold(Constants.BUILD_NORMAL_TOWER);
        } else if (tower instanceof MultiAttackTower) {
            this.spendGold(Constants.BUILD_MULTIATTACK_TOWER);
        } else if (tower instanceof MagicTower) {
            this.spendGold(Constants.BUILD_CRAZY_TOWER);
        }
    }

    /**
     * Train a unit
     * 
     * @param unit Unit type
     */
    public void trainUnit(Unit unit) {
        if (unit instanceof NormalSoldier) {
            this.spendGold(Constants.TRAIN_NORMAL_SOLDIER);
        } else if (unit instanceof CrazySoldier) {
            this.spendGold(Constants.TRAIN_CRAZY_SOLDIER);
        }
    }

    /**
     * Check if the player has enough gold to build/train sprites
     * (gold amount can't be negative)
     * 
     * @param price
     * @return true if the player has enough gold
     */
    public boolean hasEnoughGold(int price) {
        return this.gold - price >= 0;
    }

    /**
     * Decreasing the player gold
     * 
     * @param goldSpent
     */
    public void spendGold(int goldSpent) {
        if (hasEnoughGold(goldSpent)) {
            this.gold -= goldSpent;
        }
    }

    /**
     * Gold gained
     * 
     * @param goldGained amount of the gained gold
     */
    public void gainGold(int goldGained) {
        this.gold += goldGained;
    }

    public boolean hasWon() {
        if (this.health == 0) {

            return false;
        }
        return true;
    }

    /**
     * Build a goldmine
     */
    public void buildGoldMine() {
        this.spendGold(Constants.BUILD_GOLDMINE);
        this.goldMineCounter++;
    }

    /**
     * Collect treasure
     */
    public void collectTreasureChest() {
        this.gainGold(Constants.TREASURE_CHEST);
    }

    /**
     * Increasing the players' gold as the time elapse
     * 
     * @param deltaTime time between each frame
     */
    public void makeGold(float deltaTime) {
        if (this.goldMineCounter > 0) {
            timer += deltaTime;
            if (timer > 2f) {
                this.gainGold(Constants.GOLDMINE_GAIN * this.goldMineCounter);
                timer -= 2f;
            }
        }
    }

}
