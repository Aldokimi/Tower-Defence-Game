package com.alphatech.game.persistance;

import com.alphatech.game.utils.paths.BarrackCorner;
import com.alphatech.game.utils.towers.GoldMine;
import com.alphatech.game.utils.towers.MagicTower;
import com.alphatech.game.utils.towers.MultiAttackTower;
import com.alphatech.game.utils.towers.NormalTower;
import com.alphatech.game.utils.towers.Placeholder;
import com.alphatech.game.utils.towers.Tower;
import com.alphatech.game.utils.units.CrazySoldier;
import com.alphatech.game.utils.units.NormalSoldier;
import com.alphatech.game.utils.units.Unit;
import com.alphatech.game.utils.units.UnitSettings;
import com.alphatech.game.view.GameScreen;
import com.alphatech.game.helpers.Constants.PathNum;
import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.awt.geom.Point2D;
import java.util.ArrayList;

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
                } else if (tempRedUnit.getCurrentState().equals("DEAD")) {
                    tempRedUnit.setAnimation(unitControl.dyingRedSoldier1Animation);
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
                } else if (tempRedUnit.getCurrentState().equals("DEAD")) {
                    tempRedUnit.setAnimation(unitControl.dyingRedSoldier3Animation);
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
                } else if (tempBlueUnit.getCurrentState().equals("DEAD")) {
                    tempBlueUnit.setAnimation(unitControl.dyingBlueSoldier1Animation);
                } else {
                    tempBlueUnit.setAnimation(unitControl.idleBlueSoldier1Animation);
                }
            } else if (tempBlueUnitType.equals("CrazySoldier")) {
                tempBlueUnit = new CrazySoldier(tempBlueUnitPosition);
                tempBlueUnit.setState(prefs.getString("BunitsState_" + i, "IDLE"));

                if (tempBlueUnit.getCurrentState().equals("WALK")) {
                    tempBlueUnit.setAnimation(unitControl.walkingBlueSoldier1Animation);
                } else if (tempBlueUnit.getCurrentState().equals("ATTACK")) {
                    tempBlueUnit.setAnimation(unitControl.attackingBlueSoldier1Animation);
                } else if (tempBlueUnit.getCurrentState().equals("DEAD")) {
                    tempBlueUnit.setAnimation(unitControl.dyingBlueSoldier1Animation);
                } else {
                    tempBlueUnit.setAnimation(unitControl.idleBlueSoldier1Animation);
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

        String towerName;
        String parentName;

        int takenPlacesArraySize;

        ArrayList<Placeholder> takenPlaces;
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
            tower.initializeCenterofMeasurement(towerPlaceholder);
            // tower taken places
            takenPlaces = new ArrayList<>();
            takenPlacesArraySize = prefs.getInteger("TowersTakenPlacesSize_" + i, 0);
            for (int j = 0; j < takenPlacesArraySize; j++) {
                towerPlaceholder = new Placeholder(prefs.getFloat(i + "towerTakenPlacesX_" + j, 0),
                        prefs.getFloat(i + "towerTakenPlacesY_" + j, 0));
                towerPlaceholder.setIsFree(prefs.getBoolean(i + "towerTakenPlacesIsFree_" + j, false));
                takenPlaces.add(towerPlaceholder);
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
    }
}
