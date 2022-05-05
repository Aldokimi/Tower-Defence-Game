package com.alphatech.simulation;

import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.towers.GoldMine;
import com.alphatech.game.utils.towers.MagicTower;
import com.alphatech.game.utils.towers.MultiAttackTower;
import com.alphatech.game.utils.towers.NormalTower;
import com.alphatech.game.utils.towers.Placeholder;
import com.alphatech.game.utils.towers.TowerSprite;
import com.alphatech.game.utils.units.CrazySoldier;
import com.alphatech.game.utils.units.NormalSoldier;
import com.alphatech.game.helpers.Constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Simulation {

    private Player blueCastle;
    private Player redCastle;
    private ArrayList<Placeholder> placeholders;

    public Simulation() {
        placeholders = new ArrayList<Placeholder>();
        fillPlaceHolders();
        blueCastle = new Player();
        redCastle = new Player();
        this.simulateActions();
    }

    @Test
    @DisplayName("Simulate game play")
    public void simulateActions() {
        // red player's turn
        redCastle.startTurn();
        // check turn
        assertTrue(redCastle.getTurn());
        // build normal tower
        if (redCastle.hasEnoughGold(Constants.BUILD_NORMAL_TOWER)) {
            NormalTower normalTower1 = new NormalTower(placeholders);
            normalTower1.releaseAvailablePlaces();
            normalTower1.build();
            // check if tower is available
            TowerSprite tempPlaceholder = new TowerSprite(new Placeholder(21, 8), "None");
            // placeholder we want to build on
            if (normalTower1.getAvailablePlaces().contains(tempPlaceholder.getPosition())) {
                normalTower1.addTower(tempPlaceholder.getPosition(), Constants.NORMAL_TOWER);
                // check if tower's placeholder is taken
                assertTrue(normalTower1.getTakenPlaces().contains(tempPlaceholder));
                redCastle.buildTower(normalTower1);
                // check money
                assertEquals(Constants.INIT_GOLD_COUNT - Constants.BUILD_NORMAL_TOWER, redCastle.getGold());
            }
        }
        // train normal soldier
        if (redCastle.hasEnoughGold(Constants.TRAIN_NORMAL_SOLDIER)) {
            NormalSoldier normalSoldier1 = new NormalSoldier(new Point2D.Float(820, 217));
            redCastle.trainUnit(normalSoldier1);
        }
        redCastle.endTurn();
        blueCastle.startTurn();
        // blue player's turn
        if (blueCastle.getTurn()) {
            // build goldmine
            if (blueCastle.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                GoldMine goldMine1 = new GoldMine(placeholders);
                goldMine1.releaseAvailablePlaces();
                goldMine1.build();
                // placeholder we want to build on
                Placeholder tempPlaceholder = new Placeholder(13, 15);
                if (goldMine1.getAvailablePlaces().contains(tempPlaceholder)) {
                    goldMine1.addPlaceholder(tempPlaceholder);
                    assertTrue(goldMine1.getTakenPlaces().contains(tempPlaceholder));
                    blueCastle.buildGoldMine();
                }
            }
            // train crazy soldier
            if (blueCastle.hasEnoughGold(Constants.TRAIN_CRAZY_SOLDIER)) {
                CrazySoldier crazySoldier1 = new CrazySoldier(new Point2D.Float(85, 640));
                blueCastle.trainUnit(crazySoldier1);
                // Crazy Soldier collects treasure chest
                Point2D.Float position;
                position = new Point2D.Float(40, 15);
                crazySoldier1.setPosition(position);
                Point2D.Float treasurePlace = new Point2D.Float(40, 15);
                assertEquals(crazySoldier1.getPosition(), treasurePlace);
                blueCastle.setGold(Constants.INIT_GOLD_COUNT);
                blueCastle.collectTreasureChest();
                assertEquals(blueCastle.getGold(), Constants.INIT_GOLD_COUNT + Constants.TREASURE_CHEST);
            }
        }
        blueCastle.endTurn();
        redCastle.startTurn();
        if (redCastle.getTurn()) {
            // build multiattack tower
            if (redCastle.hasEnoughGold(Constants.BUILD_MULTIATTACK_TOWER)) {
                MultiAttackTower multiAttackTower1 = new MultiAttackTower(placeholders);
                multiAttackTower1.releaseAvailablePlaces();
                multiAttackTower1.build();
                TowerSprite tempPlaceholder = new TowerSprite(new Placeholder(6, 19), "None");
                // placeholder we want to build on
                if (multiAttackTower1.getAvailablePlaces().contains(tempPlaceholder.getPosition())) {
                    multiAttackTower1.addTower(tempPlaceholder.getPosition(), Constants.MULTIATTACK_TOWER);
                    assertTrue(multiAttackTower1.getTakenPlaces().contains(tempPlaceholder));
                    redCastle.buildTower(multiAttackTower1);
                }
            }
            // build magic tower
            if (redCastle.hasEnoughGold(Constants.BUILD_MAGIC_TOWER)) {
                MagicTower magicTower1 = new MagicTower(placeholders);
                magicTower1.releaseAvailablePlaces();
                magicTower1.build();
                TowerSprite tempPlaceholder = new TowerSprite(new Placeholder(22, 6), "None");
                // placeholder we want to build on
                if (magicTower1.getAvailablePlaces().contains(tempPlaceholder.getPosition())) {
                    magicTower1.addTower(tempPlaceholder.getPosition(), Constants.MAGIC_TOWER);
                    assertTrue(magicTower1.getTakenPlaces().contains(tempPlaceholder));
                    redCastle.buildTower(magicTower1);
                }
            }
        }
        redCastle.endTurn();
        blueCastle.startTurn();
    }

    public void fillPlaceHolders() {
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 28; y++) {
                if (// near blue castle
                (x == 6 && y == 21) || (y == 23 && (x == 5 || x == 7 || x == 10 || x == 12 || x == 14 || x == 17)) ||
                        ((x == 8 || x == 10) && y == 21) || (y == 19 && x == 6)
                        || ((y == 16 || y == 18) && (x == 2 || x == 0)) || ((y == 15 || y == 18) && x == 4) ||
                        (y == 19 && x == 8)
                        || (y == 14 && (x == 2 || x == 0) || (x == 21 && y == 23)) ||
                        (y == 21 && x == 12) || (y == 12 && (x == 2 || x == 0))
                        || (y == 10 && (x == 3 || x == 4 || x == 6)) ||
                        (x == 17 && (y == 21) || (x == 19 && y == 21) || (y == 23 && x == 24))
                        || (y == 21 && x == 15) ||
                        ((y == 19 || y == 17) && (x == 11 || x == 13)) || (x == 13 && y == 15) || (x == 11 && y == 14)
                        || (y == 10 && (x == 8 || x == 10)) || (y == 8 && (x == 1 || x == 5))
                        || (y == 5 && x == 6) ||
                        // near red castle
                        (y == 8 && (x == 21 || x == 23)) || (y == 4 && x == 21)
                        || (y == 6 && x == 22) || (x == 23 && y == 6)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 14 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && (x == 16 || x == 25 || x == 27 || x == 29))
                        || (y == 16 && x == 17) || (y == 16 && x == 29) || (x == 23 && y == 16) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 14) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 22)) || ((y == 22 || y == 17) && x == 25)
                        || ((x == 7 || x == 13 || x == 17) && y == 7)) {
                    this.placeholders.add(new Placeholder(x, y));
                }
            }
        }
    }

}
