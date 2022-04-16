package com.alphatech.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.towers.*;
import com.alphatech.game.utils.units.CrazySoldier;
import com.alphatech.game.utils.units.NormalSoldier;

import java.awt.Point;
import com.alphatech.game.helpers.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private int turnTime;
    private int gold;
    private boolean turn;
    private int health;
    public ArrayList<Placeholder> placeholders;

    @BeforeEach
    void setUp() {
        player = new Player(turnTime, gold, turn, health);
    }

    @Test
    @DisplayName("Player's turn")
    void startTurn() {
        
        player.startTurn();
        assertTrue(player.getTurn());
    }

    @Test
    @DisplayName("Enemy's turn")
    void endTurn() {

        player.endTurn();
        assertFalse(player.getTurn());
    }

    @Test
    @DisplayName("Getting player's gold")
    void getGold() {

        assertEquals(gold, player.getGold());
    }

    @Test
    @DisplayName("Checking if player has enough gold -- he doesn't")
    void hasEnoughGold() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        assertFalse(player.hasEnoughGold(200000));
    }

    @Test
    @DisplayName("Checking if player has enough gold -- he does")
    void hasEnoughGoldYes() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        assertTrue(player.hasEnoughGold(100));
    }
    
    @Test
    @DisplayName("Building a Normal Tower - cost")
    void buildNormalTower() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.buildTower(new NormalTower(placeholders));
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT - Constants.BUILD_NORMAL_TOWER);
    }

    @Test
    @DisplayName("Building a MultiAttack Tower - cost")
    void buildMultiAttackTower() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.buildTower(new MultiAttackTower(placeholders));
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT - Constants.BUILD_MULTIATTACK_TOWER);
    }

    @Test
    @DisplayName("Training a normal soldier - cost")
    void trainNormalSoldier() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.trainUnit(new NormalSoldier(new Point(3, 3)));
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT - Constants.TRAIN_NORMAL_SOLDIER);
    }
    
    @Test
    @DisplayName("Training a crazy soldier - cost")
    void trainCrazySoldier() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.trainUnit(new CrazySoldier(new Point(3, 3)));
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT - Constants.TRAIN_CRAZY_SOLDIER);
    }

    @Test
    @DisplayName("Loser player")
    void lose() {

        player.setHealth(0);
        assertFalse(player.hasWon());
    }

    @Test
    @DisplayName("Winner player")
    void win() {

        player.setHealth(100);
        assertTrue(player.hasWon());
    }

    @Test
    @DisplayName("Build gold mine - cost")
    void buildGoldMine() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.buildGoldMine();
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT - Constants.BUILD_GOLDMINE);
    }

    @Test
    @DisplayName("Collect treasure chest - gain")
    void collectTreasureChest() {

        player.setGold(Constants.INIT_GOLD_COUNT);
        player.collectTreasureChest();
        assertEquals(player.getGold(), Constants.INIT_GOLD_COUNT + Constants.TREASURE_CHEST);
    }
}   
