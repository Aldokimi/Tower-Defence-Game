package com.alphatech.game.model.persistance;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Constants.PathNum;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.model.paths.BarrackCorner;
import com.alphatech.game.model.towers.*;
import com.alphatech.game.model.units.CrazySoldier;
import com.alphatech.game.model.units.NormalSoldier;
import com.alphatech.game.model.units.Unit;
import com.alphatech.game.model.units.UnitSettings;
import com.alphatech.game.view.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadGame {
    public LoadGame(GameScreen gameScreen) {
        Preferences prefs = Gdx.app.getPreferences("tower-defense");
        ((Game) Gdx.app.getApplicationListener()).setScreen(gameScreen);

        // setting the stored data
        // gold
        gameScreen.redPlayer.setGold(prefs.getInteger("Rgold", Constants.INIT_GOLD_COUNT));
        gameScreen.bluePlayer.setGold(prefs.getInteger("Bgold", Constants.INIT_GOLD_COUNT));

        // turn
        gameScreen.redPlayer.setTurn(prefs.getBoolean("Rturn", false));
        gameScreen.bluePlayer.setTurn(prefs.getBoolean("Bturn", false));

        // health
        gameScreen.redPlayer.setHealth(prefs.getInteger("Rhealth", Constants.INIT_HEALTH));
        gameScreen.bluePlayer.setHealth(prefs.getInteger("Bhealth", Constants.INIT_HEALTH));

        // timer
        gameScreen.width = prefs.getFloat("Timer", 174f);

        // units
        UnitSettings unitControl = new UnitSettings();

        // red
        int redUnitsSize = prefs.getInteger("RunitsArraySize", 0);
        Unit tempRedUnit;
        String tempRedUnitType;
        Point2D.Float tempRedUnitPosition;
        for (int i = 0; i < redUnitsSize; i++) {
            // Type
            tempRedUnitType = prefs.getString("RunitsType_" + i, "Unit");

            // Position
            tempRedUnitPosition = new Point2D.Float(prefs.getFloat("RunitsPosX_" + i, 0),
                    prefs.getFloat("RunitsPosY_" + i, 0));

            // Setting current unit state

            // Initializing the class
            if (tempRedUnitType.equals("NormalSoldier")) {
                tempRedUnit = new NormalSoldier(tempRedUnitPosition);
                tempRedUnit.setState(prefs.getString("RunitsState_" + i, "IDLE"));

                if (tempRedUnit.getCurrentState().equals("WALK")) {
                    tempRedUnit.setAnimation(unitControl.walkingRedSoldier1Animation);
                } else if (tempRedUnit.getCurrentState().equals("ATTACK")) {
                    tempRedUnit.setAnimation(unitControl.attackingRedSoldier1Animation);
                } else {
                    tempRedUnit.setAnimation(unitControl.idleRedSoldier1Animation);
                }
            } else if (tempRedUnitType.equals("CrazySoldier")) {
                tempRedUnit = new CrazySoldier(tempRedUnitPosition);
                tempRedUnit.setState(prefs.getString("RunitsState_" + i, "IDLE"));

                if (tempRedUnit.getCurrentState().equals("WALK")) {
                    tempRedUnit.setAnimation(unitControl.walkingRedSoldier3Animation);
                } else if (tempRedUnit.getCurrentState().equals("ATTACK")) {
                    tempRedUnit.setAnimation(unitControl.attackingRedSoldier3Animation);
                } else {
                    tempRedUnit.setAnimation(unitControl.idleRedSoldier3Animation);
                }

            } else {// (tempUnitType.equals("Unit")) rare case
                tempRedUnit = new Unit(tempRedUnitPosition);
            }
            tempRedUnit.setColor(prefs.getString("RunitsColor_" + i, ""));
            tempRedUnit.setFromBarrack(prefs.getBoolean("RunitsFromBarrack_" + i, false));
            tempRedUnit.setMovedInPath(prefs.getBoolean("RunitsMovedInPath_" + i, false));
            tempRedUnit.setIsXaxis(prefs.getBoolean("RunitsIsXaxis_" + i, false));

            // Health
            tempRedUnit.setHealth(prefs.getInteger("RunitsHealth_" + i, 1000));

            // paths
            tempRedUnit.setNextPathLevel(prefs.getInteger("RunitsNextPathLevel_" + i, 1));
            tempRedUnit.setPath(PathNum.valueOf(prefs.getString("RunitsPath_" + i, "FIRST")));

            gameScreen.redPlayer.units.add(tempRedUnit);
        }

        // Blue
        int blueUnitsSize = prefs.getInteger("BunitsArraySize", 0);
        Unit tempBlueUnit;

        String tempBlueUnitType;
        Point2D.Float tempBlueUnitPosition;
        for (int i = 0; i < blueUnitsSize; i++) {
            // Type
            tempBlueUnitType = prefs.getString("BunitsType_" + i, "Unit");

            // Position
            tempBlueUnitPosition = new Point2D.Float(prefs.getFloat("BunitsPosX_" + i, 0),
                    prefs.getFloat("BunitsPosY_" + i, 0));

            // Setting current unit state

            // Initializing the class
            if (tempBlueUnitType.equals("NormalSoldier")) {
                tempBlueUnit = new NormalSoldier(tempBlueUnitPosition);
                tempBlueUnit.setState(prefs.getString("BunitsState_" + i, "IDLE"));

                if (tempBlueUnit.getCurrentState().equals("WALK")) {
                    tempBlueUnit.setAnimation(unitControl.walkingBlueSoldier1Animation);
                } else if (tempBlueUnit.getCurrentState().equals("ATTACK")) {
                    tempBlueUnit.setAnimation(unitControl.attackingBlueSoldier1Animation);
                } else {
                    tempBlueUnit.setAnimation(unitControl.idleBlueSoldier1Animation);
                }
            } else if (tempBlueUnitType.equals("CrazySoldier")) {
                tempBlueUnit = new CrazySoldier(tempBlueUnitPosition);
                tempBlueUnit.setState(prefs.getString("BunitsState_" + i, "IDLE"));

                if (tempBlueUnit.getCurrentState().equals("WALK")) {
                    tempBlueUnit.setAnimation(unitControl.walkingBlueSoldier3Animation);
                } else if (tempBlueUnit.getCurrentState().equals("ATTACK")) {
                    tempBlueUnit.setAnimation(unitControl.attackingBlueSoldier3Animation);
                } else {
                    tempBlueUnit.setAnimation(unitControl.idleBlueSoldier3Animation);
                }

            } else {// (tempUnitType.equals("Unit")) rare case
                tempBlueUnit = new Unit(tempBlueUnitPosition);
            }
            tempBlueUnit.setColor(prefs.getString("BunitsColor_" + i, ""));
            tempBlueUnit.setFromBarrack(prefs.getBoolean("BunitsFromBarrack_" + i, false));
            tempBlueUnit.setMovedInPath(prefs.getBoolean("BunitsMovedInPath_" + i, false));
            tempBlueUnit.setIsXaxis(prefs.getBoolean("BunitsIsXaxis_" + i, false));

            // Health
            tempBlueUnit.setHealth(prefs.getInteger("BunitsHealth_" + i, 1000));

            // paths
            tempBlueUnit.setNextPathLevel(prefs.getInteger("BunitsNextPathLevel_" + i, 1));
            tempBlueUnit.setPath(PathNum.valueOf(prefs.getString("BunitsPath_" + i, "FIRST")));

            gameScreen.bluePlayer.units.add(tempBlueUnit);
        }

        // barracks
        int barracksArraySize = prefs.getInteger("barracksSize", 0);
        Placeholder tempPlaceholder;
        ArrayList<Placeholder> barracksArray = new ArrayList<>();

        for (int i = 0; i < barracksArraySize; i++) {
            tempPlaceholder = new Placeholder(prefs.getFloat("barracksPlaceholderX_" + i, 0),
                    prefs.getFloat("barracksPlaceholderY_" + i, 0));
            tempPlaceholder.setIsFree(prefs.getBoolean("barracksPlaceholderIsFree_" + i, false));
            barracksArray.add(tempPlaceholder);
        }
        gameScreen.towerSettings.setBarrackPlaceholders(barracksArray);

        // barracks corners
        int barracksCornersSize = prefs.getInteger("barracksCornersSize", 0);
        ArrayList<BarrackCorner> barracksCorners = new ArrayList<>();
        BarrackCorner tmpBarrackCorner;
        for (int i = 0; i < barracksCornersSize; i++) {
            tmpBarrackCorner = new BarrackCorner(PathNum.valueOf(prefs.getString("cornerPath_" + i, "")),
                    new Point2D.Float(prefs.getFloat("cornerPointX_" + i, 0), prefs.getFloat("cornerPointY_" + i, 0)),
                    prefs.getInteger("cornerNextLevel_" + i, 0));

            barracksCorners.add(tmpBarrackCorner);
        }
        gameScreen.pathSettings.setClosestCorners(barracksCorners);

        // Place holders
        int placeholdersSize = prefs.getInteger("PlaceHoldersSize", 0);

        for (int i = 0; i < placeholdersSize; i++) {
            gameScreen.towerSettings.getPlaceHolders().get(i)
                    .setIsFree(prefs.getBoolean("PlaceHoldersSizeIsFree_" + i, false));
        }

        // Towers
        int towersArraySize = prefs.getInteger("TowersArraySize", 0);
        Placeholder towerPlaceholder;
        Tower tower;

        ArrayList<Tower> totalTowers = new ArrayList<>();
        CopyOnWriteArrayList<Unit> totalTargets = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<FireBall> fireballs = new CopyOnWriteArrayList<>();
        ArrayList<TowerSprite> takenPlaces;

        String towerName;
        String parentName;

        int takenPlacesArraySize;
        int targetUnitsArraySize;
        int fireBallArraySize;

        Unit tempTargetUnit;
        FireBall tempFireBall;
        TowerSprite towerSprite;

        String tempTargetUnitType;
        Point2D.Float tempTargetUnitPosition;

        for (int i = 0; i < towersArraySize; i++) {
            // parent name
            parentName = prefs.getString("ParentName_" + i, "");
            // get center of measurement
            towerPlaceholder = new Placeholder(prefs.getFloat("centerOfMeasurementX_" + i, 0),
                    prefs.getFloat("centerOfMeasurementY_" + i, 0));
            towerPlaceholder.setIsFree(prefs.getBoolean("centerOfMeasurementFree_" + i, false));

            // towername
            towerName = prefs.getString("towerName_" + i, "");

            // initialize
            if (towerName.equals("MultiAttackTower")) {

                if (parentName.equals("BLUE")) {
                    tower = new MultiAttackTower(Textures.BLUE_MULTI_ATTACK_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");

                    gameScreen.bluePlayer.multiAttackTower = new MultiAttackTower(Textures.BLUE_MULTI_ATTACK_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");
                    gameScreen.bluePlayer.multiAttackTower.initializeCenterofMeasurement(towerPlaceholder);
                } else {
                    tower = new MultiAttackTower(Textures.RED_MULTI_ATTACK_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.multiAttackTower = new MultiAttackTower(Textures.RED_MULTI_ATTACK_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.multiAttackTower.initializeCenterofMeasurement(towerPlaceholder);
                }
            } else if (towerName.equals("MagicTower")) {

                if (parentName.equals("BLUE")) {
                    tower = new MagicTower(Textures.BLUE_MAGIC_TOWER, gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");
                    gameScreen.bluePlayer.magicTower = new MagicTower(Textures.BLUE_MAGIC_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");
                    gameScreen.bluePlayer.magicTower.initializeCenterofMeasurement(towerPlaceholder);
                } else {
                    tower = new MagicTower(Textures.RED_MAGIC_TOWER, gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.magicTower = new MagicTower(Textures.RED_MAGIC_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.magicTower.initializeCenterofMeasurement(towerPlaceholder);
                }
            } else {// Normal Tower
                if (parentName.equals("BLUE")) {
                    tower = new NormalTower(Textures.BLUE_NORMAL_TOWER, gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");
                    gameScreen.bluePlayer.normalTower = new NormalTower(Textures.BLUE_NORMAL_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "BLUE");
                    gameScreen.bluePlayer.normalTower.initializeCenterofMeasurement(towerPlaceholder);

                } else {
                    tower = new NormalTower(Textures.RED_NORMAL_TOWER, gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.normalTower = new NormalTower(Textures.RED_NORMAL_TOWER,
                            gameScreen.towerSettings.getPlaceHolders(),
                            "RED");
                    gameScreen.redPlayer.normalTower.initializeCenterofMeasurement(towerPlaceholder);
                }
            }

            // set center of measurement
            takenPlaces = new ArrayList<>();

            tower.initializeCenterofMeasurement(towerPlaceholder);

            // tower taken places
            takenPlacesArraySize = prefs.getInteger("TowersTakenPlacesSize_" + i, 0);
            for (int j = 0; j < takenPlacesArraySize; j++) {
                totalTargets.clear();
                // tower position
                towerPlaceholder = new Placeholder(prefs.getFloat(i + "towerTakenPlacesX_" + j, 0),
                        prefs.getFloat(i + "towerTakenPlacesY_" + j, 0));
                towerPlaceholder.setIsFree(prefs.getBoolean(i + "towerTakenPlacesIsFree_" + j, false));

                towerSprite = new TowerSprite(towerPlaceholder,
                        prefs.getString(i + "TowerSpriteType_" + j, "NormalTower"));

                // target units
                targetUnitsArraySize = prefs.getInteger(i + "TargetUnitsSize_" + j, 0);
                for (int k = 0; k < targetUnitsArraySize; k++) {
                    // Type
                    tempTargetUnitType = prefs.getString(i + "" + j + "TargetUnitsType_" + k, "Unit");

                    // Position
                    tempTargetUnitPosition = new Point2D.Float(prefs.getFloat(i + "" + j + "TargetUnitsPosX_" + k, 0),
                            prefs.getFloat(i + "" + j + "TargetUnitsPosY_" + k, 0));

                    // Initializing the class
                    // Setting current unit state
                    if (tempTargetUnitType.equals("NormalSoldier")) {

                        tempTargetUnit = new NormalSoldier(tempTargetUnitPosition);
                        tempTargetUnit.setState(prefs.getString(i + "" + j + "TargetUnitsState_" + k, "IDLE"));
                        tempTargetUnit.setColor(prefs.getString(i + "" + j + "TargetUnitsColor_" + k, ""));

                        if (tempTargetUnit.getColor().equals("blue")) {
                            if (tempTargetUnit.getCurrentState().equals("WALK")) {
                                tempTargetUnit.setAnimation(unitControl.walkingBlueSoldier1Animation);
                            } else if (tempTargetUnit.getCurrentState().equals("ATTACK")) {
                                tempTargetUnit.setAnimation(unitControl.attackingBlueSoldier1Animation);
                            } else {
                                tempTargetUnit.setAnimation(unitControl.idleBlueSoldier1Animation);
                            }
                        } else {// red
                            if (tempTargetUnit.getCurrentState().equals("WALK")) {
                                tempTargetUnit.setAnimation(unitControl.walkingRedSoldier1Animation);
                            } else if (tempTargetUnit.getCurrentState().equals("ATTACK")) {
                                tempTargetUnit.setAnimation(unitControl.attackingRedSoldier1Animation);
                            } else {
                                tempTargetUnit.setAnimation(unitControl.idleRedSoldier1Animation);
                            }
                        }
                    } else if (tempTargetUnitType.equals("CrazySoldier")) {
                        tempTargetUnit = new CrazySoldier(tempTargetUnitPosition);
                        tempTargetUnit.setState(prefs.getString(i + "" + j + "TargetUnitsState_" + k, "IDLE"));
                        tempTargetUnit.setColor(prefs.getString(i + "" + j + "TargetUnitsColor_" + k, ""));

                        if (tempTargetUnit.getColor().equals("blue")) {
                            if (tempTargetUnit.getCurrentState().equals("WALK")) {
                                tempTargetUnit.setAnimation(unitControl.walkingBlueSoldier3Animation);
                            } else if (tempTargetUnit.getCurrentState().equals("ATTACK")) {
                                tempTargetUnit.setAnimation(unitControl.attackingBlueSoldier3Animation);
                            } else {
                                tempTargetUnit.setAnimation(unitControl.idleBlueSoldier3Animation);
                            }
                        } else {// red
                            if (tempTargetUnit.getCurrentState().equals("WALK")) {
                                tempTargetUnit.setAnimation(unitControl.walkingRedSoldier3Animation);
                            } else if (tempTargetUnit.getCurrentState().equals("ATTACK")) {
                                tempTargetUnit.setAnimation(unitControl.attackingRedSoldier3Animation);
                            } else {
                                tempTargetUnit.setAnimation(unitControl.idleRedSoldier3Animation);
                            }
                        }

                    } else {// (tempUnitType.equals("Unit")) rare case
                        tempTargetUnit = new Unit(tempTargetUnitPosition);
                    }
                    tempTargetUnit.setFromBarrack(prefs.getBoolean(i + "" + j + "TargetUnitsFromBarrack_" + k, false));
                    tempTargetUnit.setMovedInPath(prefs.getBoolean(i + "" + j + "TargetUnitsMovedInPath_" + k, false));
                    tempTargetUnit.setIsXaxis(prefs.getBoolean(i + "" + j + "TargetUnitsIsXaxis_" + k, false));

                    // Health
                    tempTargetUnit.setHealth(prefs.getInteger(i + "" + j + "TargetUnitsHealth_" + k, 1000));

                    // paths
                    tempTargetUnit.setNextPathLevel(prefs.getInteger(i + "" + j + "TargetUnitsNextPathLevel_" + k, 1));
                    tempTargetUnit
                            .setPath(PathNum.valueOf(prefs.getString(i + "" + j + "TargetUnitsPath_" + k, "FIRST")));

                    totalTargets.add(tempTargetUnit);
                }

                // Fireball
                fireBallArraySize = prefs.getInteger(i + "FireballSize_" + j, 0);
                fireballs = new CopyOnWriteArrayList<>();
                for (int k = 0; k < fireBallArraySize; k++) {
                    tempFireBall = new FireBall(new Point2D.Float(prefs.getFloat(i + "" + j + "FireballPosX_" + k),
                            prefs.getFloat(i + "" + j + "FireballPosY_" + k)),
                            new Point2D.Float(prefs.getFloat(i + "" + j + "FireballTargetPosX_" + k),
                                    prefs.getFloat(i + "" + j + "FireballTargetPosY_" + k)),
                            prefs.getFloat(i + "" + j + "FireballFireRate_" + k, 0),
                            prefs.getString(i + "" + j + "FireballTowerType_" + k));

                    fireballs.add(tempFireBall);
                }
                // setting targets
                towerSprite.setTargets(totalTargets);
                // setting fireballs
                towerSprite.setFires(fireballs);
                // setting update rate
                towerSprite.setUpdateRate(prefs.getInteger(i + "UpdateRate_" + j, 0));

                takenPlaces.add(towerSprite);
            }
            tower.setTakenPlaces(takenPlaces);

            if (tower instanceof MultiAttackTower) {
                if (tower.getParentName().equals("BLUE")) {

                    gameScreen.bluePlayer.multiAttackTower.setTakenPlaces(takenPlaces);
                } else {

                    gameScreen.redPlayer.multiAttackTower.setTakenPlaces(takenPlaces);
                }
            } else if (tower instanceof MagicTower) {
                if (tower.getParentName().equals("BLUE")) {

                    gameScreen.bluePlayer.magicTower.setTakenPlaces(takenPlaces);
                } else {

                    gameScreen.redPlayer.magicTower.setTakenPlaces(takenPlaces);
                }
            } else {
                if (tower.getParentName().equals("BLUE")) {

                    gameScreen.bluePlayer.normalTower.setTakenPlaces(takenPlaces);
                } else {
                    gameScreen.redPlayer.normalTower.setTakenPlaces(takenPlaces);
                }
            }

            totalTowers.add(tower);
        }
        gameScreen.towerSettings.setTowers(totalTowers);

        // Goldmine counter & Goldmines
        gameScreen.redPlayer.setGoldMineCounter(prefs.getInteger("redPlayerGoldMines"));
        gameScreen.bluePlayer.setGoldMineCounter(prefs.getInteger("bluePlayerGoldMines"));

        int goldMinesArraySize = prefs.getInteger("goldMinesArraySize", 0);
        GoldMine tmpGoldMine;
        int takenGPlacesArraySize;
        ArrayList<Placeholder> takenGoldminePlaces;
        Placeholder goldMinePlaceholder;
        ArrayList<GoldMine> goldMines = new ArrayList<>();
        String goldParentName;
        for (int i = 0; i < goldMinesArraySize; i++) {
            goldParentName = prefs.getString("GoldMinesParentName_" + i, "");

            if (goldParentName.equals("BLUE")) {
                tmpGoldMine = new GoldMine(Textures.BLUE_GOLD_MINE,
                        gameScreen.towerSettings.getPlaceHolders(), "BLUE");

                gameScreen.bluePlayer.goldMine = new GoldMine(Textures.BLUE_GOLD_MINE,
                        gameScreen.towerSettings.getPlaceHolders(), "BLUE");

            } else {
                tmpGoldMine = new GoldMine(Textures.RED_GOLD_MINE,
                        gameScreen.towerSettings.getPlaceHolders(), "RED");
                gameScreen.redPlayer.goldMine = new GoldMine(Textures.RED_GOLD_MINE,
                        gameScreen.towerSettings.getPlaceHolders(), "RED");
            }

            takenGoldminePlaces = new ArrayList<>();
            takenGPlacesArraySize = prefs.getInteger("GoldMinesTakenPlacesSize_" + i, 0);
            for (int j = 0; j < takenGPlacesArraySize; j++) {
                goldMinePlaceholder = new Placeholder(prefs.getFloat(i +
                        "GoldMinesTakenPlacesX_" + j, 0),
                        prefs.getFloat(i + "GoldMinesTakenPlacesY_" + j, 0));
                goldMinePlaceholder.setIsFree(prefs.getBoolean(i +
                        "GoldMinesTakenPlacesIsFree_" + j, false));
                takenGoldminePlaces.add(goldMinePlaceholder);
            }
            tmpGoldMine.setTakenPlaces(takenGoldminePlaces);
            if (tmpGoldMine.getParentName().equals("BLUE")) {

                gameScreen.bluePlayer.goldMine.setTakenPlaces(takenGoldminePlaces);
            } else {
                gameScreen.redPlayer.goldMine.setTakenPlaces(takenGoldminePlaces);
            }
            goldMines.add(tmpGoldMine);
        }
        gameScreen.towerSettings.setGoldMines(goldMines);

        // Treasures
        gameScreen.pathSettings.setTreasurePlace(
                new Point2D.Float(prefs.getFloat("TreasureChestsPosX"), prefs.getFloat("TreasureChestsPosY")));
    }
}
