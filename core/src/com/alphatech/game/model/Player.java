package com.alphatech.game.model;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.model.towers.*;
import com.alphatech.game.model.units.CrazySoldier;
import com.alphatech.game.model.units.NormalSoldier;
import com.alphatech.game.model.units.Unit;

import java.util.ArrayList;

public class Player {

    private ArrayList<Unit> units;
    private int turnTime;
    private int gold;
    private boolean turn;
    private int health;
    private int goldMineCounter;
    private float timer;

    // Initialized in the game screen
    private NormalTower normalTower;
    private MultiAttackTower multiAttackTower;
    private MagicTower magicTower;
    private GoldMine goldMine;

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

    public void setTurnTime(int time) {
        this.turnTime = time;
    }

    public int getTurnTime() {
        return this.turnTime;
    }

    public int getGoldMineCounter() {
        return goldMineCounter;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public GoldMine getGoldMine() {
        return goldMine;
    }

    public MagicTower getMagicTower() {
        return magicTower;
    }

    public MultiAttackTower getMultiAttackTower() {
        return multiAttackTower;
    }

    public NormalTower getNormalTower() {
        return normalTower;
    }

    public void setGoldMine(GoldMine goldMine) {
        this.goldMine = goldMine;
    }

    public void setMagicTower(MagicTower magicTower) {
        this.magicTower = magicTower;
    }

    public void setMultiAttackTower(MultiAttackTower multiAttackTower) {
        this.multiAttackTower = multiAttackTower;
    }

    public void setNormalTower(NormalTower normalTower) {
        this.normalTower = normalTower;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setGoldMineCounter(int goldMineCounter) {
        this.goldMineCounter = goldMineCounter;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getTimer() {
        return this.timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
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
            this.spendGold(Constants.BUILD_MAGIC_TOWER);
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

    /**
     * Check if a player has lost the game or not
     *
     * @return true if the player has lost the game
     */
    public boolean hasLost() {
        return this.health <= 0;
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

    public void decreaseHealthBy(int x) {
        this.health = this.health - x;
    }
}
