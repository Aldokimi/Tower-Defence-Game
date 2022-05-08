package com.alphatech.game.model.persistance;

import com.alphatech.game.view.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveGame {
    public SaveGame(GameScreen gameScreen) {
        Preferences prefs = Gdx.app.getPreferences("tower-defense");
        // gold
        prefs.putInteger("Rgold", gameScreen.redPlayer.getGold());
        prefs.putInteger("Bgold", gameScreen.bluePlayer.getGold());

        // turn
        prefs.putBoolean("Rturn", gameScreen.redPlayer.getTurn());
        prefs.putBoolean("Bturn", gameScreen.bluePlayer.getTurn());

        // health
        prefs.putInteger("Rhealth", gameScreen.redPlayer.getHealth());
        prefs.putInteger("Bhealth", gameScreen.bluePlayer.getHealth());

        // game timer
        prefs.putFloat("Timer", gameScreen.width);

        // units
        // red
        prefs.putInteger("RunitsArraySize", gameScreen.redPlayer.units.size());
        for (int i = 0; i < gameScreen.redPlayer.units.size(); i++) {
            prefs.putString("RunitsType_" + i, gameScreen.redPlayer.units.get(i).getClassName());
            prefs.putString("RunitsState_" + i, gameScreen.redPlayer.units.get(i).getCurrentState());

            prefs.putInteger("RunitsHealth_" + i, gameScreen.redPlayer.units.get(i).getHealth());
            prefs.putInteger("RunitsNextPathLevel_" + i,
                    gameScreen.redPlayer.units.get(i).getNextPathLevel());
            prefs.putString("RunitsPath_" + i, gameScreen.redPlayer.units.get(i).getPath().name());

            prefs.putString("RunitsColor_" + i, gameScreen.redPlayer.units.get(i).getColor());
            prefs.putBoolean("RunitsFromBarrack_" + i, gameScreen.redPlayer.units.get(i).getFromBarrack());
            prefs.putBoolean("RunitsMovedInPath_" + i, gameScreen.redPlayer.units.get(i).getMovedInPath());
            prefs.putBoolean("RunitsIsXaxis_" + i, gameScreen.redPlayer.units.get(i).getIsXaxis());

            prefs.putFloat("RunitsPosX_" + i, gameScreen.redPlayer.units.get(i).getPosition().x);
            prefs.putFloat("RunitsPosY_" + i, gameScreen.redPlayer.units.get(i).getPosition().y);
        }

        // blue
        prefs.putInteger("BunitsArraySize", gameScreen.bluePlayer.units.size());
        for (int i = 0; i < gameScreen.bluePlayer.units.size(); i++) {
            prefs.putString("BunitsType_" + i, gameScreen.bluePlayer.units.get(i).getClassName());
            prefs.putString("BunitsState_" + i, gameScreen.bluePlayer.units.get(i).getCurrentState());

            prefs.putInteger("BunitsHealth_" + i, gameScreen.bluePlayer.units.get(i).getHealth());
            prefs.putInteger("BunitsNextPathLevel_" + i,
                    gameScreen.bluePlayer.units.get(i).getNextPathLevel());
            prefs.putString("BunitsPath_" + i, gameScreen.bluePlayer.units.get(i).getPath().name());

            prefs.putString("BunitsColor_" + i, gameScreen.bluePlayer.units.get(i).getColor());
            prefs.putBoolean("BunitsFromBarrack_" + i, gameScreen.bluePlayer.units.get(i).getFromBarrack());
            prefs.putBoolean("BunitsMovedInPath_" + i, gameScreen.bluePlayer.units.get(i).getMovedInPath());
            prefs.putBoolean("BunitsIsXaxis_" + i, gameScreen.bluePlayer.units.get(i).getIsXaxis());

            prefs.putFloat("BunitsPosX_" + i, gameScreen.bluePlayer.units.get(i).getPosition().x);
            prefs.putFloat("BunitsPosY_" + i, gameScreen.bluePlayer.units.get(i).getPosition().y);
        }

        // barracks
        prefs.putInteger("barracksSize", gameScreen.towerSettings.getBarrackPlaceholders().size());
        for (int i = 0; i < gameScreen.towerSettings.getBarrackPlaceholders().size(); i++) {
            prefs.putFloat("barracksPlaceholderX_" + i,
                    gameScreen.towerSettings.getBarrackPlaceholders().get(i).getX());
            prefs.putFloat("barracksPlaceholderY_" + i,
                    gameScreen.towerSettings.getBarrackPlaceholders().get(i).getY());
            prefs.putBoolean("barracksPlaceholderIsFree_" + i,
                    gameScreen.towerSettings.getBarrackPlaceholders().get(i).getIsFree());
        }

        // barracks corners
        prefs.putInteger("barracksCornersSize", gameScreen.pathSettings.getClosestCorners().size());
        for (int i = 0; i < gameScreen.pathSettings.getClosestCorners().size(); i++) {
            prefs.putString("cornerPath_" + i,
                    gameScreen.pathSettings.getClosestCorners().get(i).getPath().name());
            prefs.putFloat("cornerPointX_" + i,
                    gameScreen.pathSettings.getClosestCorners().get(i).getPoint().x);
            prefs.putFloat("cornerPointY_" + i,
                    gameScreen.pathSettings.getClosestCorners().get(i).getPoint().y);
            prefs.putInteger("cornerNextLevel_" + i,
                    gameScreen.pathSettings.getClosestCorners().get(i).getNextLevel());

        }

        // PlaceHolders
        prefs.putInteger("PlaceHoldersSize",
                gameScreen.towerSettings.getPlaceHolders().size());

        for (int i = 0; i < gameScreen.towerSettings.getPlaceHolders().size(); i++) {
            prefs.putBoolean("PlaceHoldersSizeIsFree_" + i,
                    gameScreen.towerSettings.getPlaceHolders().get(i).getIsFree());
        }

        // Tower
        prefs.putInteger("TowersArraySize", gameScreen.towerSettings.getTowers().size());
        for (int i = 0; i < gameScreen.towerSettings.getTowers().size(); i++) {
            // Parent name
            prefs.putString("ParentName_" + i, gameScreen.towerSettings.getTowers().get(i).getParentName());

            // center of measurement
            if (gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().size() > 0) {
                prefs.putFloat("centerOfMeasurementX_" + i,
                        gameScreen.towerSettings.getTowers().get(i).getCenterofMeasurement().getX());
                prefs.putFloat("centerOfMeasurementY_" + i,
                        gameScreen.towerSettings.getTowers().get(i).getCenterofMeasurement().getY());
                prefs.putBoolean("centerOfMeasurementFree_" + i,
                        gameScreen.towerSettings.getTowers().get(i).getCenterofMeasurement()
                                .getIsFree());

            } else {
                continue;
            }
            // tower name
            prefs.putString("towerName_" + i, gameScreen.towerSettings.getTowers().get(i).getClassName());

            // tower taken places
            prefs.putInteger("TowersTakenPlacesSize_" + i,
                    gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().size());
            // iterate over the tower sprites in the array
            for (int j = 0; j < gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().size(); j++) {

                prefs.putString(i + "TowerSpriteType_" + j, gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getTowerType());
                // target units
                prefs.putInteger(i + "TargetUnitsSize_" + j, gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getTargets().size());
                for (int k = 0; k < gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getTargets().size(); k++) {

                    prefs.putString(i + "" + j + "TargetUnitsType_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getClassName());
                    prefs.putString(i + "" + j + "TargetUnitsState_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getCurrentState());

                    prefs.putInteger(i + "" + j + "TargetUnitsHealth_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getHealth());
                    prefs.putInteger(i + "" + j + "TargetUnitsNextPathLevel_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getNextPathLevel());
                    prefs.putString(i + "" + j + "TargetUnitsPath_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPath().name());

                    prefs.putString(i + "" + j + "TargetUnitsColor_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getColor());
                    prefs.putBoolean(i + "" + j + "TargetUnitsFromBarrack_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getFromBarrack());
                    prefs.putBoolean(i + "" + j + "TargetUnitsMovedInPath_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getMovedInPath());
                    prefs.putBoolean(i + "" + j + "TargetUnitsIsXaxis_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getIsXaxis());

                    prefs.putFloat(i + "" + j + "TargetUnitsPosX_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPosition().x);
                    prefs.putFloat(i + "" + j + "TargetUnitsPosY_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPosition().y);
                }

                // Fireball
                prefs.putInteger(i + "FireballSize_" + j, gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getFires().size());
                for (int k = 0; k < gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getFires().size(); k++) {
                    // position
                    prefs.putFloat(i + "" + j + "FireballPosX_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getPosition().x);
                    prefs.putFloat(i + "" + j + "FireballPosY_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getPosition().y);
                    // target
                    prefs.putFloat(i + "" + j + "FireballTargetPosX_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTarget().x);
                    prefs.putFloat(i + "" + j + "FireballTargetPosY_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTarget().y);
                    // firerate
                    prefs.putFloat(i + "" + j + "FireballFireRate_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getFireRate());

                    // towertype
                    prefs.putString(i + "" + j + "FireballTowerType_" + k,
                            gameScreen.towerSettings.getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTowerType());
                }
                // updateRate
                prefs.putInteger(i + "UpdateRate_" + j, gameScreen.towerSettings.getTowers().get(i)
                        .getTakenPlaces().get(j).getUpdateRate());
                // position
                prefs.putFloat(i + "towerTakenPlacesX_" + j,
                        gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition().getX());
                prefs.putFloat(i + "towerTakenPlacesY_" + j,
                        gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition().getY());
                prefs.putBoolean(i + "towerTakenPlacesIsFree_" + j,
                        gameScreen.towerSettings.getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition()
                                .getIsFree());

            }
        }

        // Goldmines
        prefs.putInteger("goldMinesArraySize", gameScreen.towerSettings.getGoldMines().size());
        for (int i = 0; i < gameScreen.towerSettings.getGoldMines().size(); i++) {
            // Parent name
            prefs.putString("GoldMinesParentName_" + i,
                    gameScreen.towerSettings.getGoldMines().get(i).getParentName());

            // tower taken places
            prefs.putInteger("GoldMinesTakenPlacesSize_" + i,
                    gameScreen.towerSettings.getGoldMines().get(i).getTakenPlaces().size());

            // iterate over the placeholders in the array
            for (int j = 0; j < gameScreen.towerSettings.getGoldMines().get(i).getTakenPlaces().size(); j++) {
                prefs.putFloat(i + "GoldMinesTakenPlacesX_" + j,
                        gameScreen.towerSettings.getGoldMines().get(i).getTakenPlaces().get(j)
                                .getX());
                prefs.putFloat(i + "GoldMinesTakenPlacesY_" + j,
                        gameScreen.towerSettings.getGoldMines().get(i).getTakenPlaces().get(j)
                                .getY());
                prefs.putBoolean(i + "GoldMinesTakenPlacesIsFree_" + j,
                        gameScreen.towerSettings.getGoldMines().get(i).getTakenPlaces().get(j)
                                .getIsFree());
            }

        }

        // Goldmine counter
        prefs.putInteger("redPlayerGoldMines", gameScreen.redPlayer.getGoldMineCounter());
        prefs.putInteger("bluePlayerGoldMines", gameScreen.bluePlayer.getGoldMineCounter());

        // Treasures
        prefs.putFloat("TreasureChestsPosX", gameScreen.pathSettings.getTreasurePlace().x);
        prefs.putFloat("TreasureChestsPosY", gameScreen.pathSettings.getTreasurePlace().y);

        // save changes to storage
        prefs.flush();
    }
}
