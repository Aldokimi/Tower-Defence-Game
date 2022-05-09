package com.alphatech.game.model.persistance;

import com.alphatech.game.view.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveGame {
    public SaveGame(GameScreen gameScreen) {
        Preferences prefs = Gdx.app.getPreferences("tower-defense");
        // gold
        prefs.putInteger("Rgold", gameScreen.getRedPlayer().getGold());
        prefs.putInteger("Bgold", gameScreen.getBluePlayer().getGold());

        // turn
        prefs.putBoolean("Rturn", gameScreen.getRedPlayer().getTurn());
        prefs.putBoolean("Bturn", gameScreen.getBluePlayer().getTurn());

        // health
        prefs.putInteger("Rhealth", gameScreen.getRedPlayer().getHealth());
        prefs.putInteger("Bhealth", gameScreen.getBluePlayer().getHealth());

        // game timer
        prefs.putFloat("Timer", gameScreen.getWidth());

        // units
        //  temp
        prefs.putInteger("TempunitsArraySize", gameScreen.getUnitSettings().getTempUnits().size());
        for (int i = 0; i < gameScreen.getUnitSettings().getTempUnits().size(); i++) {
            prefs.putString("TempunitsType_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getClassName());
            prefs.putString("TempunitsState_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getCurrentState());

            prefs.putInteger("TempunitsHealth_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getHealth());
            prefs.putInteger("TempunitsNextPathLevel_" + i,
                    gameScreen.getUnitSettings().getTempUnits().get(i).getNextPathLevel());
            
            prefs.putString("TempunitsColor_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getColor());
            prefs.putBoolean("TempunitsFromBarrack_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getFromBarrack());
            prefs.putBoolean("TempunitsMovedInPath_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getMovedInPath());
            prefs.putBoolean("TempunitsIsXaxis_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getIsXaxis());

            prefs.putFloat("TempunitsPosX_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getPosition().x);
            prefs.putFloat("TempunitsPosY_" + i, gameScreen.getUnitSettings().getTempUnits().get(i).getPosition().y);
        }

        // red 
        prefs.putInteger("RunitsArraySize", gameScreen.getRedPlayer().getUnits().size());
        for (int i = 0; i < gameScreen.getRedPlayer().getUnits().size(); i++) {
            prefs.putString("RunitsType_" + i, gameScreen.getRedPlayer().getUnits().get(i).getClassName());
            prefs.putString("RunitsState_" + i, gameScreen.getRedPlayer().getUnits().get(i).getCurrentState());

            prefs.putInteger("RunitsHealth_" + i, gameScreen.getRedPlayer().getUnits().get(i).getHealth());
            prefs.putInteger("RunitsNextPathLevel_" + i,
                    gameScreen.getRedPlayer().getUnits().get(i).getNextPathLevel());
            prefs.putString("RunitsPath_" + i, gameScreen.getRedPlayer().getUnits().get(i).getPath().name());

            prefs.putString("RunitsColor_" + i, gameScreen.getRedPlayer().getUnits().get(i).getColor());
            prefs.putBoolean("RunitsFromBarrack_" + i, gameScreen.getRedPlayer().getUnits().get(i).getFromBarrack());
            prefs.putBoolean("RunitsMovedInPath_" + i, gameScreen.getRedPlayer().getUnits().get(i).getMovedInPath());
            prefs.putBoolean("RunitsIsXaxis_" + i, gameScreen.getRedPlayer().getUnits().get(i).getIsXaxis());

            prefs.putFloat("RunitsPosX_" + i, gameScreen.getRedPlayer().getUnits().get(i).getPosition().x);
            prefs.putFloat("RunitsPosY_" + i, gameScreen.getRedPlayer().getUnits().get(i).getPosition().y);
        }


        // blue -- real
        prefs.putInteger("BunitsArraySize", gameScreen.getBluePlayer().getUnits().size());
        for (int i = 0; i < gameScreen.getBluePlayer().getUnits().size(); i++) {
            prefs.putString("BunitsType_" + i, gameScreen.getBluePlayer().getUnits().get(i).getClassName());
            prefs.putString("BunitsState_" + i, gameScreen.getBluePlayer().getUnits().get(i).getCurrentState());

            prefs.putInteger("BunitsHealth_" + i, gameScreen.getBluePlayer().getUnits().get(i).getHealth());
            prefs.putInteger("BunitsNextPathLevel_" + i,
                    gameScreen.getBluePlayer().getUnits().get(i).getNextPathLevel());
            prefs.putString("BunitsPath_" + i, gameScreen.getBluePlayer().getUnits().get(i).getPath().name());

            prefs.putString("BunitsColor_" + i, gameScreen.getBluePlayer().getUnits().get(i).getColor());
            prefs.putBoolean("BunitsFromBarrack_" + i, gameScreen.getBluePlayer().getUnits().get(i).getFromBarrack());
            prefs.putBoolean("BunitsMovedInPath_" + i, gameScreen.getBluePlayer().getUnits().get(i).getMovedInPath());
            prefs.putBoolean("BunitsIsXaxis_" + i, gameScreen.getBluePlayer().getUnits().get(i).getIsXaxis());

            prefs.putFloat("BunitsPosX_" + i, gameScreen.getBluePlayer().getUnits().get(i).getPosition().x);
            prefs.putFloat("BunitsPosY_" + i, gameScreen.getBluePlayer().getUnits().get(i).getPosition().y);
        }

        // barracks
        prefs.putInteger("barracksSize", gameScreen.getTowerSettings().getBarrackPlaceholders().size());
        for (int i = 0; i < gameScreen.getTowerSettings().getBarrackPlaceholders().size(); i++) {
            prefs.putFloat("barracksPlaceholderX_" + i,
                    gameScreen.getTowerSettings().getBarrackPlaceholders().get(i).getX());
            prefs.putFloat("barracksPlaceholderY_" + i,
                    gameScreen.getTowerSettings().getBarrackPlaceholders().get(i).getY());
            prefs.putBoolean("barracksPlaceholderIsFree_" + i,
                    gameScreen.getTowerSettings().getBarrackPlaceholders().get(i).getIsFree());
        }

        // barracks corners
        prefs.putInteger("barracksCornersSize", gameScreen.getPathSettings().getClosestCorners().size());
        for (int i = 0; i < gameScreen.getPathSettings().getClosestCorners().size(); i++) {
            prefs.putString("cornerPath_" + i,
                    gameScreen.getPathSettings().getClosestCorners().get(i).getPath().name());
            prefs.putFloat("cornerPointX_" + i,
                    gameScreen.getPathSettings().getClosestCorners().get(i).getPoint().x);
            prefs.putFloat("cornerPointY_" + i,
                    gameScreen.getPathSettings().getClosestCorners().get(i).getPoint().y);
            prefs.putInteger("cornerNextLevel_" + i,
                    gameScreen.getPathSettings().getClosestCorners().get(i).getNextLevel());

        }

        // PlaceHolders
        prefs.putInteger("PlaceHoldersSize",
                gameScreen.getTowerSettings().getPlaceHolders().size());

        for (int i = 0; i < gameScreen.getTowerSettings().getPlaceHolders().size(); i++) {
            prefs.putBoolean("PlaceHoldersSizeIsFree_" + i,
                    gameScreen.getTowerSettings().getPlaceHolders().get(i).getIsFree());
        }

        // Tower
        prefs.putInteger("TowersArraySize", gameScreen.getTowerSettings().getTowers().size());
        for (int i = 0; i < gameScreen.getTowerSettings().getTowers().size(); i++) {
            // Parent name
            prefs.putString("ParentName_" + i, gameScreen.getTowerSettings().getTowers().get(i).getParentName());

            // center of measurement
            if (gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().size() > 0) {
                prefs.putFloat("centerOfMeasurementX_" + i,
                        gameScreen.getTowerSettings().getTowers().get(i).getCenterofMeasurement().getX());
                prefs.putFloat("centerOfMeasurementY_" + i,
                        gameScreen.getTowerSettings().getTowers().get(i).getCenterofMeasurement().getY());
                prefs.putBoolean("centerOfMeasurementFree_" + i,
                        gameScreen.getTowerSettings().getTowers().get(i).getCenterofMeasurement()
                                .getIsFree());

            } else {
                continue;
            }
            // tower name
            prefs.putString("towerName_" + i, gameScreen.getTowerSettings().getTowers().get(i).getClassName());

            // tower taken places
            prefs.putInteger("TowersTakenPlacesSize_" + i,
                    gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().size());
            // iterate over the tower sprites in the array
            for (int j = 0; j < gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().size(); j++) {

                prefs.putString(i + "TowerSpriteType_" + j, gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getTowerType());
                // target units
                prefs.putInteger(i + "TargetUnitsSize_" + j, gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getTargets().size());
                for (int k = 0; k < gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getTargets().size(); k++) {

                    prefs.putString(i + "" + j + "TargetUnitsType_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getClassName());
                    prefs.putString(i + "" + j + "TargetUnitsState_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getCurrentState());

                    prefs.putInteger(i + "" + j + "TargetUnitsHealth_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getHealth());
                    prefs.putInteger(i + "" + j + "TargetUnitsNextPathLevel_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getNextPathLevel());
                    prefs.putString(i + "" + j + "TargetUnitsPath_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPath().name());

                    prefs.putString(i + "" + j + "TargetUnitsColor_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getColor());
                    prefs.putBoolean(i + "" + j + "TargetUnitsFromBarrack_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getFromBarrack());
                    prefs.putBoolean(i + "" + j + "TargetUnitsMovedInPath_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getMovedInPath());
                    prefs.putBoolean(i + "" + j + "TargetUnitsIsXaxis_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getIsXaxis());

                    prefs.putFloat(i + "" + j + "TargetUnitsPosX_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPosition().x);
                    prefs.putFloat(i + "" + j + "TargetUnitsPosY_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getTargets().get(k)
                                    .getPosition().y);
                }

                // Fireball
                prefs.putInteger(i + "FireballSize_" + j, gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getFires().size());
                for (int k = 0; k < gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getFires().size(); k++) {
                    // position
                    prefs.putFloat(i + "" + j + "FireballPosX_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getPosition().x);
                    prefs.putFloat(i + "" + j + "FireballPosY_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getPosition().y);
                    // target
                    prefs.putFloat(i + "" + j + "FireballTargetPosX_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTarget().x);
                    prefs.putFloat(i + "" + j + "FireballTargetPosY_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTarget().y);
                    // firerate
                    prefs.putFloat(i + "" + j + "FireballFireRate_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getFireRate());

                    // towertype
                    prefs.putString(i + "" + j + "FireballTowerType_" + k,
                            gameScreen.getTowerSettings().getTowers().get(i)
                                    .getTakenPlaces().get(j).getFires().get(k)
                                    .getTowerType());
                }
                // updateRate
                prefs.putInteger(i + "UpdateRate_" + j, gameScreen.getTowerSettings().getTowers().get(i)
                        .getTakenPlaces().get(j).getUpdateRate());
                // position
                prefs.putFloat(i + "towerTakenPlacesX_" + j,
                        gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition().getX());
                prefs.putFloat(i + "towerTakenPlacesY_" + j,
                        gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition().getY());
                prefs.putBoolean(i + "towerTakenPlacesIsFree_" + j,
                        gameScreen.getTowerSettings().getTowers().get(i).getTakenPlaces().get(j)
                                .getPosition()
                                .getIsFree());

            }
        }

        // Goldmines
        prefs.putInteger("goldMinesArraySize", gameScreen.getTowerSettings().getGoldMines().size());
        for (int i = 0; i < gameScreen.getTowerSettings().getGoldMines().size(); i++) {
            // Parent name
            prefs.putString("GoldMinesParentName_" + i,
                    gameScreen.getTowerSettings().getGoldMines().get(i).getParentName());

            // tower taken places
            prefs.putInteger("GoldMinesTakenPlacesSize_" + i,
                    gameScreen.getTowerSettings().getGoldMines().get(i).getTakenPlaces().size());

            // iterate over the placeholders in the array
            for (int j = 0; j < gameScreen.getTowerSettings().getGoldMines().get(i).getTakenPlaces().size(); j++) {
                prefs.putFloat(i + "GoldMinesTakenPlacesX_" + j,
                        gameScreen.getTowerSettings().getGoldMines().get(i).getTakenPlaces().get(j)
                                .getX());
                prefs.putFloat(i + "GoldMinesTakenPlacesY_" + j,
                        gameScreen.getTowerSettings().getGoldMines().get(i).getTakenPlaces().get(j)
                                .getY());
                prefs.putBoolean(i + "GoldMinesTakenPlacesIsFree_" + j,
                        gameScreen.getTowerSettings().getGoldMines().get(i).getTakenPlaces().get(j)
                                .getIsFree());
            }

        }

        // Goldmine counter
        prefs.putInteger("redPlayerGoldMines", gameScreen.getRedPlayer().getGoldMineCounter());
        prefs.putInteger("bluePlayerGoldMines", gameScreen.getBluePlayer().getGoldMineCounter());

        // Treasures
        prefs.putFloat("TreasureChestsPosX", gameScreen.getPathSettings().getTreasurePlace().x);
        prefs.putFloat("TreasureChestsPosY", gameScreen.getPathSettings().getTreasurePlace().y);

        // save changes to storage
        prefs.flush();
    }
}
