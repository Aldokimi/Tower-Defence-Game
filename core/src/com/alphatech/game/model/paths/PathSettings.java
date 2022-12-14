package com.alphatech.game.model.paths;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.model.Player;
import com.alphatech.game.model.towers.Placeholder;
import com.alphatech.game.model.units.NormalSoldier;
import com.alphatech.game.model.units.Unit;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.geom.Point2D;
import java.util.*;

public class PathSettings {
    /// Store the coordinates of the corners of the paths
    private HashMap<Constants.PathNum, ArrayList<Point2D.Float>> paths;

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

    // Closest points to barracks
    private ArrayList<BarrackCorner> closestCorners;

    // treasure chest place
    private Point2D.Float treasurePlace;
    private float appearingTime = Constants.INIT_APPEARING_TIME;
    private float disapearingTime = Constants.INIT_DISAPPEARING_TIME;
    private ArrayList<Point2D.Float> tPoints;
    private ArrayList<Point2D.Float> notAllowedPlacesForTreasure;

    public PathSettings() {
        // Paths
        isPathChosen = false;
        chosenPath = null;
        paths = new HashMap<>();
        fillPaths();

        // Closest points to barracks
        closestCorners = new ArrayList<>();

        // Near Blue Castle
        PathArrowBlue1Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue1RegionDraw = new TextureRegionDrawable(PathArrowBlue1Region);
        PathArrowBlue1 = new ImageButton(PathArrowBlue1RegionDraw);
        setArrowSettings(PathArrowBlue1, 137, 712, 0, Constants.PathNum.FIRST);

        PathArrowBlue2Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue2RegionDraw = new TextureRegionDrawable(PathArrowBlue2Region);
        PathArrowBlue2 = new ImageButton(PathArrowBlue2RegionDraw);
        setArrowSettings(PathArrowBlue2, 137, 647, 0, Constants.PathNum.SECOND);

        PathArrowBlue3Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue3RegionDraw = new TextureRegionDrawable(PathArrowBlue3Region);
        PathArrowBlue3 = new ImageButton(PathArrowBlue3RegionDraw);
        setArrowSettings(PathArrowBlue3, 105, 618, 270f, Constants.PathNum.THIRD);

        PathArrowBlue4Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue4RegionDraw = new TextureRegionDrawable(PathArrowBlue4Region);
        PathArrowBlue4 = new ImageButton(PathArrowBlue4RegionDraw);
        setArrowSettings(PathArrowBlue4, 40, 618, 270f, Constants.PathNum.FORTH);

        // Near red Castle
        PathArrowRed1Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed1RegionDraw = new TextureRegionDrawable(PathArrowRed1Region);
        PathArrowRed1 = new ImageButton(PathArrowRed1RegionDraw);
        setArrowSettings(PathArrowRed1, 919, 266, 90f, Constants.PathNum.FIRST);

        PathArrowRed2Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed2RegionDraw = new TextureRegionDrawable(PathArrowRed2Region);
        PathArrowRed2 = new ImageButton(PathArrowRed2RegionDraw);
        setArrowSettings(PathArrowRed2, 855, 266, 90f, Constants.PathNum.SECOND);

        PathArrowRed3Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed3RegionDraw = new TextureRegionDrawable(PathArrowRed3Region);
        PathArrowRed3 = new ImageButton(PathArrowRed3RegionDraw);
        setArrowSettings(PathArrowRed3, 819, 249, 180, Constants.PathNum.THIRD);

        PathArrowRed4Region = new TextureRegion(Textures.PathArrowR);
        PathArrowRed4RegionDraw = new TextureRegionDrawable(PathArrowRed4Region);
        PathArrowRed4 = new ImageButton(PathArrowRed4RegionDraw);
        setArrowSettings(PathArrowRed4, 819, 183, 180, Constants.PathNum.FORTH);

        resetColorsOfPaths();

        // treasure
        tPoints = new ArrayList<>();
        for (Map.Entry<Constants.PathNum, ArrayList<Point2D.Float>> entry : this.paths.entrySet()) {
            for (int i = 1; i < entry.getValue().size(); i++) {
                tPoints.add(entry.getValue().get(i));
            }
        }
        chooseRandomPlaceForTreasureChest();
    }

    public Constants.PathNum getChosenPath() {
        return chosenPath;
    }

    public void setChosenPath(Constants.PathNum chosenPath) {
        this.chosenPath = chosenPath;
    }

    public Boolean getIsPathChosen() {
        return isPathChosen;
    }

    public void setIsPathChosen(Boolean isPathChosen) {
        this.isPathChosen = isPathChosen;
    }

    public HashMap<Constants.PathNum, ArrayList<Point2D.Float>> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<Constants.PathNum, ArrayList<Point2D.Float>> paths) {
        this.paths = paths;
    }

    /**
     * Used to store the 4 paths inside paths hash map.
     * NOTE: Path starts from BLUE castle and ends in the RED castle
     */
    public void fillPaths() {
        // First Path
        paths.put(Constants.PathNum.FIRST, new ArrayList<>(Arrays.asList(
                new Point2D.Float(85, 705),
                new Point2D.Float(753, 705),
                new Point2D.Float(753, 512),
                new Point2D.Float(883, 512),
                new Point2D.Float(883, 230))));
        // Second path
        paths.put(Constants.PathNum.SECOND, new ArrayList<>(Arrays.asList(
                new Point2D.Float(85, 705),
                new Point2D.Float(85, 640),
                new Point2D.Float(368, 640),
                new Point2D.Float(368, 445),
                new Point2D.Float(562, 445),
                new Point2D.Float(562, 512),
                new Point2D.Float(688, 512),
                new Point2D.Float(688, 288),
                new Point2D.Float(820, 288),
                new Point2D.Float(820, 217))));
        // Third path
        paths.put(Constants.PathNum.THIRD, new ArrayList<>(Arrays.asList(
                new Point2D.Float(80, 705),
                new Point2D.Float(80, 640),
                new Point2D.Float(80, 352),
                new Point2D.Float(427, 352),
                new Point2D.Float(427, 288),
                new Point2D.Float(621, 288),
                new Point2D.Float(621, 218),
                new Point2D.Float(820, 218))));

        // Forth path
        paths.put(Constants.PathNum.FORTH, new ArrayList<>(Arrays.asList(
                new Point2D.Float(20, 640),
                new Point2D.Float(20, 288),
                new Point2D.Float(175, 288),
                new Point2D.Float(175, 199),
                new Point2D.Float(530, 199),
                new Point2D.Float(530, 162),
                new Point2D.Float(820, 162))));

        // Crazy path
        paths.put(Constants.PathNum.CRAZY, new ArrayList<>(Arrays.asList(
                new Point2D.Float(85, 640),
                new Point2D.Float(470, 288),
                new Point2D.Float(561, 288),
                new Point2D.Float(621, 218),
                new Point2D.Float(750, 218),
                new Point2D.Float(820, 200))));

        // Not allow places for treasure chest to appeare
        notAllowedPlacesForTreasure = new ArrayList<>(Arrays.asList(
                new Point2D.Float(85, 705),
                new Point2D.Float(883, 230),
                new Point2D.Float(820, 217),
                new Point2D.Float(80, 705),
                new Point2D.Float(820, 218),
                new Point2D.Float(20, 640),
                new Point2D.Float(820, 162),
                new Point2D.Float(85, 640),
                new Point2D.Float(820, 200),
                new Point2D.Float(80, 640),
                new Point2D.Float(750, 218),
                new Point2D.Float(621, 218)));

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
            Random rand = new Random();
            for (Unit unit : TempUnits) {
                if (unit instanceof NormalSoldier) {
                    if (1 == rand.nextInt(2)) {
                        int num = rand.nextInt(4);
                        unit.setPath(Constants.PathNum.values()[num]);
                    } else {
                        unit.setFromBarrack(true);
                        int ind;
                        if (unit.getColor() == "blue") {
                            ind = rand.nextInt(2);
                        } else {
                            ind = rand.nextInt(2) + 2;
                        }
                        unit.setPath(closestCorners.get(ind).getPath());
                        unit.setPosition(closestCorners.get(ind).getPoint());
                        unit.setNextPathLevel(closestCorners.get(ind).getNextLevel());

                    }
                }
            }
        } else {
            for (Unit u : TempUnits) {
                if (u instanceof NormalSoldier)
                    u.setPath(getChosenPath());

            }
        }
    }

    public ArrayList<BarrackCorner> getClosestCorners() {
        return closestCorners;
    }

    public void setClosestCorners(ArrayList<BarrackCorner> closestCorners) {
        this.closestCorners = closestCorners;
    }

    /**
     * This fuction collect the closest path corners to the barracks where the
     * unit will be trained from and store them in the closestCorners
     *
     * @param barracks
     */
    public void fillClosestCorners(ArrayList<Placeholder> barracks) {
        BarrackCorner nearest = null;
        double min;

        for (Placeholder bpoint : barracks) {
            min = Float.MAX_VALUE;
            nearest = null;
            for (Map.Entry<Constants.PathNum, ArrayList<Point2D.Float>> set : this.paths.entrySet()) {
                if (set.getKey() == Constants.PathNum.CRAZY)
                    continue;
                for (Point2D.Float pp : set.getValue()) {
                    if (Point2D.distance(pp.x, pp.y, bpoint.getX() * 32, bpoint.getY() * 32) < min) {
                        min = Point2D.distance(pp.x, pp.y, bpoint.getX() * 32, bpoint.getY() * 32);
                        nearest = new BarrackCorner(set.getKey(), pp, set.getValue().indexOf(pp));

                    }
                }
            }

            closestCorners.add(nearest);

        }
    }

    /**
     * Place the trasure chest randomly on the map
     */
    private void chooseRandomPlaceForTreasureChest() {
        Point2D.Float newSpotForTreasure = tPoints.get(new Random().nextInt(tPoints.size()));
        while (notAllowedPlacesForTreasure.contains(newSpotForTreasure)) {
            newSpotForTreasure = tPoints.get(new Random().nextInt(tPoints.size()));
        }
        this.treasurePlace = new Point2D.Float(newSpotForTreasure.x, newSpotForTreasure.y);
    }

    /**
     * Place and render the treasures on the map
     *
     * @param batch
     * @param deltaTime
     * @param redPlayer
     * @param bluePlayer
     */
    public void placeTreasureChests(SpriteBatch batch, float deltaTime, Player redPlayer, Player bluePlayer) {

        Sprite sprite = new Sprite(Textures.TREASURE_CHEST);
        if (appearingTime <= Constants.TREASURE_DISAPPEARING_TIME) {
            if (treasurePlace.getX() > 40 || treasurePlace.getY() > 40) {
                sprite.setPosition((float) treasurePlace.getX() + 16, (float) treasurePlace.getY() + 5);
            } else {
                sprite.setPosition((float) treasurePlace.getX() * Constants.PLACEHOLDER_SIZE + 16,
                        (float) treasurePlace.getY() * Constants.PLACEHOLDER_SIZE + 5);
            }
            sprite.setSize(30, 32);
            sprite.draw(batch);
            appearingTime += deltaTime;
        } else {
            disapearingTime -= deltaTime;
            this.treasurePlace = new Point2D.Float(0, 0); // so the treasure cannot be collected after it disapear!!
            if (disapearingTime <= 0.0) {
                appearingTime = 0;
                disapearingTime = Constants.INIT_DISAPPEARING_TIME;
                chooseRandomPlaceForTreasureChest();
                if (treasurePlace.getX() > 40 || treasurePlace.getY() > 40) {
                    sprite.setPosition((float) treasurePlace.getX() - 5, (float) treasurePlace.getY() - 5);
                } else {
                    sprite.setPosition((float) treasurePlace.getX() * Constants.PLACEHOLDER_SIZE,
                            (float) treasurePlace.getY() * Constants.PLACEHOLDER_SIZE);
                }
            }
        }
    }

    public Point2D.Float getTreasurePlace() {
        return treasurePlace;
    }

    public void setTreasurePlace(Point2D.Float treasurePlace) {
        this.treasurePlace = treasurePlace;
    }

    /**
     * time interval between treasures appearance
     */
    public void refreshTreasure() {
        appearingTime = Constants.MAX_APPEARING_TIME + 1;
    }

    /**
     * Check if the treasure is still avaliable for collecting
     *
     * @return boolean
     */
    public Boolean canTakeTreasure() {
        return appearingTime <= Constants.MAX_APPEARING_TIME;
    }

    private void setArrowSettings(ImageButton btn, float x, float y, float rotation, Constants.PathNum path) {
        btn.setPosition(x, y);
        btn.setTransform(true);
        btn.setRotation(rotation);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPathChosen && chosenPath == path) {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                } else {
                    chosenPath = path;
                    isPathChosen = true;
                    switch (path) {

                        case FIRST:
                            colorPath1();
                            break;

                        case SECOND:
                            colorPath2();
                            break;
                        case THIRD:
                            colorPath3();
                            break;
                        case FORTH:
                            colorPath4();
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

}
