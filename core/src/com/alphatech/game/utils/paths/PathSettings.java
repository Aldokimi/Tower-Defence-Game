package com.alphatech.game.utils.paths;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.units.Unit;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class PathSettings {
    /// Store the coordinates of the corners of the paths
    private HashMap<Constants.PathNum, ArrayList<Point>> paths;

    /// To decide if a path is chosen
    private Boolean isPathChosen;
    private Constants.PathNum chosenPath;

    /// the arrow button for path 1 blue side
    private TextureRegion PathArrowBlue1Region;
    private TextureRegionDrawable PathArrowBlue1RegionDraw;
    private ImageButton PathArrowBlue1;

    /// the arrow button for path 2 blue side
    private TextureRegion PathArrowBlue2Region;
    private TextureRegionDrawable PathArrowBlue2RegionDraw;
    private ImageButton PathArrowBlue2;

    /// the arrow button for path 3 blue side
    private TextureRegion PathArrowBlue3Region;
    private TextureRegionDrawable PathArrowBlue3RegionDraw;
    private ImageButton PathArrowBlue3;

    /// the arrow button for path 4 blue side
    private TextureRegion PathArrowBlue4Region;
    private TextureRegionDrawable PathArrowBlue4RegionDraw;
    private ImageButton PathArrowBlue4;

    /// the arrow button for path 1 Red side
    private TextureRegion PathArrowRed1Region;
    private TextureRegionDrawable PathArrowRed1RegionDraw;
    private ImageButton PathArrowRed1;

    /// the arrow button for path 2 blue side
    private TextureRegion PathArrowRed2Region;
    private TextureRegionDrawable PathArrowRed2RegionDraw;
    private ImageButton PathArrowRed2;

    /// the arrow button for path 3 blue side
    private TextureRegion PathArrowRed3Region;
    private TextureRegionDrawable PathArrowRed3RegionDraw;
    private ImageButton PathArrowRed3;

    /// the arrow button for path 4 blue side
    private TextureRegion PathArrowRed4Region;
    private TextureRegionDrawable PathArrowRed4RegionDraw;
    private ImageButton PathArrowRed4;

    public PathSettings() {
        // Paths
        isPathChosen = false;
        chosenPath = null;
        paths = new HashMap<>();
        fillPaths();

        // Near Blue Castle
        PathArrowBlue1Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue1RegionDraw = new TextureRegionDrawable(PathArrowBlue1Region);
        PathArrowBlue1 = new ImageButton(PathArrowBlue1RegionDraw);
        PathArrowBlue1.setPosition(136, 712);
        PathArrowBlue1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.FIRST) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.FIRST;
                    isPathChosen = true;
                    colorPath1();
                }
            }
        });

        PathArrowBlue2Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue2RegionDraw = new TextureRegionDrawable(PathArrowBlue2Region);
        PathArrowBlue2 = new ImageButton(PathArrowBlue2RegionDraw);
        PathArrowBlue2.setPosition(paths.get(Constants.PathNum.SECOND).get(0).x * Constants.PLACEHOLDER_SIZE + 9,
                paths.get(Constants.PathNum.SECOND).get(0).y * Constants.PLACEHOLDER_SIZE + 9);
        PathArrowBlue2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.SECOND) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.SECOND;
                    isPathChosen = true;
                    colorPath2();
                }
            }
        });

        PathArrowBlue3Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue3RegionDraw = new TextureRegionDrawable(PathArrowBlue3Region);
        PathArrowBlue3 = new ImageButton(PathArrowBlue3RegionDraw);
        PathArrowBlue3.setPosition(paths.get(Constants.PathNum.THIRD).get(0).x * Constants.PLACEHOLDER_SIZE + 9,
                paths.get(Constants.PathNum.THIRD).get(0).y * Constants.PLACEHOLDER_SIZE + 9);
        PathArrowBlue3.setTransform(true);
        PathArrowBlue3.setRotation(270f);
        PathArrowBlue3.setColor(255, 50, 40, 100);
        PathArrowBlue3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.THIRD) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.THIRD;
                    isPathChosen = true;
                    colorPath3();
                }

            }
        });

        PathArrowBlue4Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue4RegionDraw = new TextureRegionDrawable(PathArrowBlue4Region);
        PathArrowBlue4 = new ImageButton(PathArrowBlue4RegionDraw);
        PathArrowBlue4.setPosition(paths.get(Constants.PathNum.FORTH).get(0).x * Constants.PLACEHOLDER_SIZE + 9,
                paths.get(Constants.PathNum.FORTH).get(0).y * Constants.PLACEHOLDER_SIZE + 9);
        PathArrowBlue4.setTransform(true);
        ;
        PathArrowBlue4.setRotation(270f);
        PathArrowBlue4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.FORTH) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.FORTH;
                    isPathChosen = true;
                    colorPath4();
                }

            }
        });

        // Near red Castle
        PathArrowRed1Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed1RegionDraw = new TextureRegionDrawable(PathArrowRed1Region);
        PathArrowRed1 = new ImageButton(PathArrowRed1RegionDraw);
        PathArrowRed1.setPosition(919, 266);
        PathArrowRed1.setTransform(true);
        PathArrowRed1.setRotation(90);
        PathArrowRed1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.FIRST) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.FIRST;
                    isPathChosen = true;
                    colorPath1();
                }

            }
        });

        PathArrowRed2Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed2RegionDraw = new TextureRegionDrawable(PathArrowRed2Region);
        PathArrowRed2 = new ImageButton(PathArrowRed2RegionDraw);
        PathArrowRed2.setPosition(
                paths.get(Constants.PathNum.SECOND).get(paths.get(Constants.PathNum.SECOND).size() - 1).x
                        * Constants.PLACEHOLDER_SIZE - 10,
                paths.get(Constants.PathNum.SECOND).get(paths.get(Constants.PathNum.SECOND).size() - 1).y
                        * Constants.PLACEHOLDER_SIZE + 10);
        PathArrowRed2.setTransform(true);
        PathArrowRed2.setRotation(90);
        PathArrowRed2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.SECOND) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.SECOND;
                    isPathChosen = true;
                    colorPath2();
                }
            }
        });

        PathArrowRed3Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed3RegionDraw = new TextureRegionDrawable(PathArrowRed3Region);
        PathArrowRed3 = new ImageButton(PathArrowRed3RegionDraw);
        PathArrowRed3.setPosition(
                paths.get(Constants.PathNum.THIRD).get(paths.get(Constants.PathNum.THIRD).size() - 1).x
                        * Constants.PLACEHOLDER_SIZE + 19,
                paths.get(Constants.PathNum.THIRD).get(paths.get(Constants.PathNum.THIRD).size() - 1).y
                        * Constants.PLACEHOLDER_SIZE + 25);
        PathArrowRed3.setTransform(true);
        PathArrowRed3.setRotation(180);
        PathArrowRed3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.THIRD) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.THIRD;
                    isPathChosen = true;
                    colorPath3();
                }

            }
        });

        PathArrowRed4Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed4RegionDraw = new TextureRegionDrawable(PathArrowRed4Region);
        PathArrowRed4 = new ImageButton(PathArrowRed4RegionDraw);
        PathArrowRed4.setPosition(
                paths.get(Constants.PathNum.FORTH).get(paths.get(Constants.PathNum.FORTH).size() - 1).x
                        * Constants.PLACEHOLDER_SIZE + 19,
                paths.get(Constants.PathNum.FORTH).get(paths.get(Constants.PathNum.FORTH).size() - 1).y
                        * Constants.PLACEHOLDER_SIZE - 9);
        PathArrowRed4.setTransform(true);
        PathArrowRed4.setRotation(180);
        PathArrowRed4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == Constants.PathNum.FORTH) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = Constants.PathNum.FORTH;
                    isPathChosen = true;
                    colorPath4();
                }
            }
        });
        resetColorsOfPaths();
    }

    public void setPaths(HashMap<Constants.PathNum, ArrayList<Point>> paths) {
        this.paths = paths;
    }

    public void setIsPathChosen(Boolean isPathChosen) {
        this.isPathChosen = isPathChosen;
    }

    public void setChosenPath(Constants.PathNum chosenPath) {
        this.chosenPath = chosenPath;
    }

    public Constants.PathNum getChosenPath() {
        return chosenPath;
    }

    public Boolean getIsPathChosen() {
        return isPathChosen;
    }

    public HashMap<Constants.PathNum, ArrayList<Point>> getPaths() {
        return paths;
    }

    /**
     * Used to store the 4 paths inside paths hash map.
     * NOTE: Path starts from BLUE castle and ends in the RED castle
     */

    public void fillPaths() {
        // First Path
        paths.put(Constants.PathNum.FIRST, new ArrayList<>(Arrays.asList(
                new Point(85, 705),
                new Point(753, 705),
                new Point(753, 512),
                new Point(883, 512),
                new Point(883, 230))));
        // Second path
        paths.put(Constants.PathNum.SECOND, new ArrayList<>(Arrays.asList(
                new Point(4, 20),
                new Point(12, 20),
                new Point(12, 14),
                new Point(18, 14),
                new Point(18, 16),
                new Point(22, 16),
                new Point(22, 9),
                new Point(27, 9),
                new Point(27, 8))));
        // Third path
        paths.put(Constants.PathNum.THIRD, new ArrayList<>(Arrays.asList(
                new Point(3, 19),
                new Point(3, 11),
                new Point(14, 11),
                new Point(14, 9),
                new Point(20, 9),
                new Point(20, 7),
                new Point(25, 7))));

        // Forth path
        paths.put(Constants.PathNum.FORTH, new ArrayList<>(Arrays.asList(
                new Point(1, 19),
                new Point(1, 9),
                new Point(6, 9),
                new Point(6, 7),
                new Point(17, 7),
                new Point(17, 6),
                new Point(25, 6))));

    }

    /**
     * used to color the arrow of the first path
     */
    public void colorPath1() {

        PathArrowRed1.getImage().setColor(Color.BLACK);
        PathArrowRed2.getImage().setColor(Color.RED);
        PathArrowRed3.getImage().setColor(Color.RED);
        PathArrowRed4.getImage().setColor(Color.RED);
        PathArrowBlue1.getImage().setColor(Color.BLACK);
        PathArrowBlue2.getImage().setColor(Color.VIOLET);
        PathArrowBlue3.getImage().setColor(Color.VIOLET);
        PathArrowBlue4.getImage().setColor(Color.VIOLET);
    }

    /**
     * used to color the arrow of the second path
     */
    public void colorPath2() {

        PathArrowRed1.getImage().setColor(Color.RED);
        PathArrowRed2.getImage().setColor(Color.BLACK);
        PathArrowRed3.getImage().setColor(Color.RED);
        PathArrowRed4.getImage().setColor(Color.RED);
        PathArrowBlue1.getImage().setColor(Color.VIOLET);
        PathArrowBlue2.getImage().setColor(Color.BLACK);
        PathArrowBlue3.getImage().setColor(Color.VIOLET);
        PathArrowBlue4.getImage().setColor(Color.VIOLET);
    }

    /**
     * used to color the arrow of the Third path
     */
    public void colorPath3() {

        PathArrowRed1.getImage().setColor(Color.RED);
        PathArrowRed2.getImage().setColor(Color.RED);
        PathArrowRed3.getImage().setColor(Color.BLACK);
        PathArrowRed4.getImage().setColor(Color.RED);
        PathArrowBlue1.getImage().setColor(Color.VIOLET);
        PathArrowBlue2.getImage().setColor(Color.VIOLET);
        PathArrowBlue3.getImage().setColor(Color.BLACK);
        PathArrowBlue4.getImage().setColor(Color.VIOLET);
    }

    /**
     * used to color the arrow of the forth path
     */
    public void colorPath4() {

        PathArrowRed1.getImage().setColor(Color.RED);
        PathArrowRed2.getImage().setColor(Color.RED);
        PathArrowRed3.getImage().setColor(Color.RED);
        PathArrowRed4.getImage().setColor(Color.BLACK);
        PathArrowBlue1.getImage().setColor(Color.VIOLET);
        PathArrowBlue2.getImage().setColor(Color.VIOLET);
        PathArrowBlue3.getImage().setColor(Color.VIOLET);
        PathArrowBlue4.getImage().setColor(Color.BLACK);
    }

    /**
     * used to reset color the arrows
     */
    public void resetColorsOfPaths() {

        PathArrowRed1.getImage().setColor(Color.RED);
        PathArrowRed2.getImage().setColor(Color.RED);
        PathArrowRed3.getImage().setColor(Color.RED);
        PathArrowRed4.getImage().setColor(Color.RED);
        PathArrowBlue1.getImage().setColor(Color.VIOLET);
        PathArrowBlue2.getImage().setColor(Color.VIOLET);
        PathArrowBlue3.getImage().setColor(Color.VIOLET);
        PathArrowBlue4.getImage().setColor(Color.VIOLET);
    }

    /**
     * Sets arrows visibility for each players' turn
     * 
     * @param playerTurn
     */
    public void setArrowsVisibility(boolean playerTurn) {
        if (playerTurn) {
            PathArrowRed1.setVisible(false);
            PathArrowRed2.setVisible(false);
            PathArrowRed3.setVisible(false);
            PathArrowRed4.setVisible(false);

            PathArrowBlue1.setVisible(true);
            PathArrowBlue2.setVisible(true);
            PathArrowBlue3.setVisible(true);
            PathArrowBlue4.setVisible(true);
        } else {
            PathArrowRed1.setVisible(true);
            PathArrowRed2.setVisible(true);
            PathArrowRed3.setVisible(true);
            PathArrowRed4.setVisible(true);

            PathArrowBlue1.setVisible(false);
            PathArrowBlue2.setVisible(false);
            PathArrowBlue3.setVisible(false);
            PathArrowBlue4.setVisible(false);
        }
    }

    /**
     * Add arrows as actors in gameStage
     * 
     * @param gameStage
     */
    public void addArrowsToGameStage(Stage gameStage) {
        gameStage.addActor(PathArrowBlue1);
        gameStage.addActor(PathArrowBlue2);
        gameStage.addActor(PathArrowBlue3);
        gameStage.addActor(PathArrowBlue4);

        gameStage.addActor(PathArrowRed1);
        gameStage.addActor(PathArrowRed2);
        gameStage.addActor(PathArrowRed3);
        gameStage.addActor(PathArrowRed4);
    }

    /**
     * Choosing a random path for each Unit if the player did not select a path
     */
    public void chooseRandomPath(ArrayList<Unit> TempUnits) {
        if (!getIsPathChosen()) {
            for (Unit unit : TempUnits) {
                Random rand = new Random();
                int num = rand.nextInt(4);
                unit.setPath(Constants.PathNum.values()[num]);
            }
        } else {
            for (Unit u : TempUnits) {
                u.setPath(getChosenPath());
            }
        }
    }
}
