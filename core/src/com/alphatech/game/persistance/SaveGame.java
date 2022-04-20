package com.alphatech.game.persistance;

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
                prefs.putInteger("TowersArraySize", gameScreen.towerSettings.towers.size());
                for (int i = 0; i < gameScreen.towerSettings.towers.size(); i++) {
                        // Parent name
                        prefs.putString("ParentName_" + i, gameScreen.towerSettings.towers.get(i).getParentName());

                        // center of measurement
                        if (gameScreen.towerSettings.towers.get(i).getTakenPlaces().size() > 0) {
                                prefs.putFloat("centerOfMeasurementX_" + i,
                                                gameScreen.towerSettings.towers.get(i).getCenterofMeasurement().getX());
                                prefs.putFloat("centerOfMeasurementY_" + i,
                                                gameScreen.towerSettings.towers.get(i).getCenterofMeasurement().getY());
                                prefs.putBoolean("centerOfMeasurementFree_" + i,
                                                gameScreen.towerSettings.towers.get(i).getCenterofMeasurement()
                                                                .getIsFree());

                        } else {
                                continue;
                        }
                        // tower name
                        prefs.putString("towerName_" + i, gameScreen.towerSettings.towers.get(i).getClassName());

                        // tower taken places
                        prefs.putInteger("TowersTakenPlacesSize_" + i,
                                        gameScreen.towerSettings.towers.get(i).getTakenPlaces().size());
                        // iterate over the placeholders in the array
                        for (int j = 0; j < gameScreen.towerSettings.towers.get(i).getTakenPlaces().size(); j++) {
                                prefs.putFloat(i + "towerTakenPlacesX_" + j,
                                                gameScreen.towerSettings.towers.get(i).getTakenPlaces().get(j).getPosition().getX());
                                prefs.putFloat(i + "towerTakenPlacesY_" + j,
                                                gameScreen.towerSettings.towers.get(i).getTakenPlaces().get(j).getPosition().getY());
                                prefs.putBoolean(i + "towerTakenPlacesIsFree_" + j,
                                                gameScreen.towerSettings.towers.get(i).getTakenPlaces().get(j).getPosition()
                                                                .getIsFree());
                        }
                }

                // Goldmines
                prefs.putInteger("goldMinesArraySize", gameScreen.towerSettings.goldMines.size());
                for (int i = 0; i < gameScreen.towerSettings.goldMines.size(); i++) {
                        // Parent name
                        prefs.putString("GoldMinesParentName_" + i,
                                        gameScreen.towerSettings.goldMines.get(i).getParentName());

                        // tower taken places
                        prefs.putInteger("GoldMinesTakenPlacesSize_" + i,
                                        gameScreen.towerSettings.goldMines.get(i).getTakenPlaces().size());

                        // iterate over the placeholders in the array
                        for (int j = 0; j < gameScreen.towerSettings.goldMines.get(i).getTakenPlaces().size(); j++) {
                                prefs.putFloat(i + "GoldMinesTakenPlacesX_" + j,
                                                gameScreen.towerSettings.goldMines.get(i).getTakenPlaces().get(j)
                                                                .getX());
                                prefs.putFloat(i + "GoldMinesTakenPlacesY_" + j,
                                                gameScreen.towerSettings.goldMines.get(i).getTakenPlaces().get(j)
                                                                .getY());
                                prefs.putBoolean(i + "GoldMinesTakenPlacesIsFree_" + j,
                                                gameScreen.towerSettings.goldMines.get(i).getTakenPlaces().get(j)
                                                                .getIsFree());
                        }

                }

                // goldmine counter
                prefs.putInteger("redPlayerGoldMines", gameScreen.redPlayer.getGoldMineCounter());
                prefs.putInteger("bluePlayerGoldMines", gameScreen.bluePlayer.getGoldMineCounter());

                // save changes to storage
                prefs.flush();
        }
}
