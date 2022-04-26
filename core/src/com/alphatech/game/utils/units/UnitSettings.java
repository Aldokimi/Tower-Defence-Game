package com.alphatech.game.utils.units;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Constants.PathNum;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.paths.PathSettings;
import com.alphatech.game.utils.towers.TowerSettings;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class UnitSettings {

    // Units
    private ArrayList<Unit> TempUnits;// to Store units before adding them to the user units
    private TextureRegion sold1region;
    private TextureRegionDrawable sold1regiondraw;
    private ImageButton soldier1;
    private TextureRegion sold3region;
    private TextureRegionDrawable sold3regiondraw;
    private ImageButton soldier3;
    private Point2D.Float unitsPosition;
    private int turnToBlueMove;
    private int turnToRedMove;

    // Units animation
    private Animation<TextureRegion> idleRedSoldier1Animation;
    private Animation<TextureRegion> walkingRedSoldier1Animation;
    private Animation<TextureRegion> attackingRedSoldier1Animation;
    private Animation<TextureRegion> dyingRedSoldier1Animation;

    private Animation<TextureRegion> idleRedSoldier3Animation;
    private Animation<TextureRegion> walkingRedSoldier3Animation;
    private Animation<TextureRegion> attackingRedSoldier3Animation;
    private Animation<TextureRegion> dyingRedSoldier3Animation;

    private Animation<TextureRegion> idleBlueSoldier1Animation;
    private Animation<TextureRegion> walkingBlueSoldier1Animation;
    private Animation<TextureRegion> attackingBlueSoldier1Animation;
    private Animation<TextureRegion> dyingBlueSoldier1Animation;

    private Animation<TextureRegion> idleBlueSoldier3Animation;
    private Animation<TextureRegion> walkingBlueSoldier3Animation;
    private Animation<TextureRegion> attackingBlueSoldier3Animation;
    private Animation<TextureRegion> dyingBlueSoldier3Animation;

    // Units counter
    private int unitCountSoldier1;
    private int unitCountSoldier3;
    private BitmapFont unitCounter;

    // Castles health bars
    private TextureRegionDrawable CastleHealthBarFrameB;
    private TextureRegionDrawable CastleHealthBarFrameR;

    private ProgressBarStyle healthBarBStyle;
    private ProgressBarStyle healthBarRStyle;

    public UnitSettings(final Player bluePlayer, final Player redPlayer, final TowerSettings towerSettings ,final  PathSettings pathSettings) {
        // Unit counter
        unitCounter = new BitmapFont();

        //fill nearest corners array
        pathSettings.fillClosestCorners(towerSettings.getBarrackPlaceholders());

        // Health bar for castles
        CastleHealthBarFrameB = new TextureRegionDrawable(Textures.CASTLE_HEALTH_BAR_FRAME);

        CastleHealthBarFrameR = new TextureRegionDrawable(Textures.CASTLE_HEALTH_BAR_FRAME);

        healthBarBStyle = new ProgressBar.ProgressBarStyle();
        healthBarBStyle.background = new TextureRegionDrawable(new TextureRegion(Textures.VERTICAL_HEALTH_BAR));

        healthBarRStyle = new ProgressBar.ProgressBarStyle();
        healthBarRStyle.background = new TextureRegionDrawable(new TextureRegion(Textures.VERTICAL_HEALTH_BAR));

        // Animations
        idleRedSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_IDLE_RED.findRegions("idle"),
                PlayMode.LOOP);
        walkingRedSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_WALKING_RED.findRegions("walk"),
                PlayMode.LOOP);
        attackingRedSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_ATTACKING_RED.findRegions("attack"),
                PlayMode.LOOP);
        dyingRedSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_DYING_RED.findRegions("die"),
                PlayMode.LOOP);

        idleRedSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_IDLE_RED.findRegions("idle"),
                PlayMode.LOOP);
        walkingRedSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_WALKING_RED.findRegions("walk"),
                PlayMode.LOOP);
        attackingRedSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_ATTACKING_RED.findRegions("attack"),
                PlayMode.LOOP);
        dyingRedSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_DYING_RED.findRegions("die"),
                PlayMode.LOOP);

        idleBlueSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_IDLE_BLUE.findRegions("idle"),
                PlayMode.LOOP);
        walkingBlueSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_WALKING_BLUE.findRegions("walk"),
                PlayMode.LOOP);
        attackingBlueSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_ATTACKING_BLUE.findRegions("attack"),
                PlayMode.LOOP);
        dyingBlueSoldier1Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER1_DYING_BLUE.findRegions("die"),
                PlayMode.LOOP);

        idleBlueSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_IDLE_BLUE.findRegions("idle"),
                PlayMode.LOOP);
        walkingBlueSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_WALKING_BLUE.findRegions("walk"),
                PlayMode.LOOP);
        attackingBlueSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_ATTACKING_BLUE.findRegions("attack"),
                PlayMode.LOOP);
        dyingBlueSoldier3Animation = new Animation<TextureRegion>(0.08f,
                Textures.SOLDIER3_DYING_BLUE.findRegions("die"),
                PlayMode.LOOP);

        // Move in path
        turnToBlueMove = 0;
        turnToRedMove = 0;
        // temp unit ArrayList
        TempUnits = new ArrayList<>();

        // Unit -- Soldier1
        sold1region = new TextureRegion(Textures.SOLDIER1);
        sold1regiondraw = new TextureRegionDrawable(sold1region);
        soldier1 = new ImageButton(sold1regiondraw);

        soldier1.setSize(110, 90);
        soldier1.setPosition(567, 38);

        soldier1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (redPlayer.getTurn() && redPlayer.hasEnoughGold(Constants.TRAIN_NORMAL_SOLDIER)) {

                    // Create red unit of type 1
                    unitsPosition = new Point2D.Float(820, 217);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(idleRedSoldier1Animation);
                    newUnit.setColor("red");

                    redPlayer.trainUnit(newUnit);

                    towerSettings.setRedPlayerGoldCounter(redPlayer.getGold());
                    TempUnits.add(newUnit);
                    unitCountSoldier1++;
                } else if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.TRAIN_NORMAL_SOLDIER)) {

                    // Create blue unit of type 1
                    unitsPosition = new Point2D.Float(85, 702);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(idleBlueSoldier1Animation);
                    newUnit.setColor("blue");

                    bluePlayer.trainUnit(newUnit);
                    towerSettings.setBluePlayerGoldCounter(bluePlayer.getGold());
                    TempUnits.add(newUnit);
                    unitCountSoldier1++;
                }
            }
        });

        // Unit -- Soldier3
        sold3region = new TextureRegion(Textures.SOLDIER3);
        sold3regiondraw = new TextureRegionDrawable(sold3region);
        soldier3 = new ImageButton(sold3regiondraw);

        soldier3.setSize(110, 90);
        soldier3.setPosition(663, 38);

        soldier3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (redPlayer.getTurn() && redPlayer.hasEnoughGold(Constants.TRAIN_CRAZY_SOLDIER)) {

                    // Create red unit of type 3
                    unitsPosition = new Point2D.Float(820, 158);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(idleRedSoldier3Animation);
                    newUnit.setColor("red");
                    redPlayer.trainUnit(newUnit);

                    towerSettings.setRedPlayerGoldCounter(redPlayer.getGold());
                    TempUnits.add(newUnit);
                    unitCountSoldier3++;
                } else if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.TRAIN_CRAZY_SOLDIER)) {

                    // Create blue unit of type 3
                    unitsPosition = new Point2D.Float(85, 640);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(idleBlueSoldier3Animation);
                    newUnit.setColor("blue");

                    bluePlayer.trainUnit(newUnit);
                    towerSettings.setBluePlayerGoldCounter(bluePlayer.getGold());
                    TempUnits.add(newUnit);
                    unitCountSoldier3++;
                }
            }
        });

    }

    public void setSoldier1(ImageButton soldier1) {
        this.soldier1 = soldier1;
    }

    public void setUnitCountSoldier1(int unitCountSoldier1) {
        this.unitCountSoldier1 = unitCountSoldier1;
    }

    public void setUnitCountSoldier3(int unitCountSoldier3) {
        this.unitCountSoldier3 = unitCountSoldier3;
    }

    public void setTempUnits(ArrayList<Unit> tempUnits) {
        TempUnits = tempUnits;
    }

    public ImageButton getSoldier1() {
        return soldier1;
    }

    public int getUnitCountSoldier1() {
        return unitCountSoldier1;
    }

    public int getUnitCountSoldier3() {
        return unitCountSoldier3;
    }

    public ArrayList<Unit> getTempUnits() {
        return TempUnits;
    }

    /**
     * Render blue/red castle healthbars in the gamescreen
     *
     * @param batch
     */
    public void renderCastleHealthBar(Player bluePlayer, Player redPlayer, SpriteBatch batch) {
        CastleHealthBarFrameB.draw(batch, 6, 21 * Constants.PLACEHOLDER_SIZE + 1, 22, 80);
        CastleHealthBarFrameR.draw(batch, 29 * Constants.PLACEHOLDER_SIZE + 8, 5 * Constants.PLACEHOLDER_SIZE + 4, 22,
                80);

        // Blue
        healthBarBStyle.background.setMinWidth(15);
        ProgressBar healthBarB = new ProgressBar(0f, 1000, 1f, true, healthBarBStyle);
        healthBarB.setBounds(14.5f, 21 * Constants.PLACEHOLDER_SIZE + 17.5f, 1f,
                66f * (float) bluePlayer.getHealth() / Constants.INIT_HEALTH);
        healthBarB.draw(batch, 1);

        // Red
        healthBarBStyle.background.setMinWidth(15);
        ProgressBar healthBarR = new ProgressBar(0f, 1000, 1f, true, healthBarBStyle);
        healthBarR.setBounds(29 * Constants.PLACEHOLDER_SIZE + 16.5f, 5 * Constants.PLACEHOLDER_SIZE + 20.5f, 1f,
                66f * (float) redPlayer.getHealth() / Constants.INIT_HEALTH);
        healthBarR.draw(batch, 1);
    }

    /**
     * Add the units buttons/cards to the game screen
     *
     * @param gameScreenButtons
     */
    public void setUnitsAsActors(Stage gameScreenButtons) {
        gameScreenButtons.addActor(soldier1);
        gameScreenButtons.addActor(soldier3);
    }

    /**
     * Render unit counter in the gamescreen
     *
     * @param batch
     */
    public void renderUnitCounter(SpriteBatch batch) {
        unitCounter.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        unitCounter.draw(batch, String.valueOf(unitCountSoldier1), 638, 129);// -- Soldier 1
        unitCounter.draw(batch, String.valueOf(unitCountSoldier3), 734, 129);// -- Soldier 3
    }

    /**
     * Set the Idle red units to walk in the paths
     *
     * @param redPlayer
     * @param pathSettings
     */
    public void endTurnRedPlayerSetUp(Player redPlayer, PathSettings pathSettings) {
        pathSettings.chooseRandomPath(TempUnits);

        redPlayer.units.addAll(TempUnits);
        clearTempUnits();
        for (Unit unit : redPlayer.units) {
            if (unit.getCurrentState() == "IDLE") {
                if (unit instanceof NormalSoldier) {
                    unit.setAnimation(walkingRedSoldier1Animation);
                } else if (unit instanceof CrazySoldier) {
                    unit.setAnimation(walkingRedSoldier3Animation);
                    unit.setPath(PathNum.CRAZY);
                }

                unit.setState("WALK");
                if(!unit.getFromBarrack()) {
                    unit.setNextPathLevel(pathSettings.getPaths().get(unit.getPath()).size() - 2);
                    unit.setPosition(
                            pathSettings.getPaths().get(unit.getPath())
                                    .get(pathSettings.getPaths().get(unit.getPath()).size() - 1));
                }
                setUnitIsXaxis("RED", unit);
            }
        }

    }

    /**
     * Set the Idle red units to walk in the paths
     *
     * @param bluePlayer
     * @param pathSettings
     */
    public void endTurnBluePlayerSetUp(Player bluePlayer, PathSettings pathSettings) {
        // adding the temp units to Blue player units

        pathSettings.chooseRandomPath(TempUnits);

        bluePlayer.units.addAll(getTempUnits());
        clearTempUnits();
        for (Unit unit : bluePlayer.units) {
            if (unit.getCurrentState() == "IDLE") {

                if (unit instanceof NormalSoldier) {
                    unit.setAnimation(walkingBlueSoldier1Animation);

                } else if (unit instanceof CrazySoldier) {

                    unit.setAnimation(walkingBlueSoldier3Animation);
                    unit.setPath(PathNum.CRAZY);
                }

                unit.setState("WALK");
                if(!unit.getFromBarrack()) {
                    unit.setNextPathLevel(1);
                    unit.setPosition(pathSettings.getPaths().get(unit.getPath()).get(0));
                }
                setUnitIsXaxis("BLUE", unit);
            }
        }
    }

    public void clearTempUnits() {
        this.TempUnits.clear();
    }

    /**
     * Initialize the variable `isXaxis` which will indicate if a unit is moving on
     * the X or Y axis
     *
     * @param unit Soldier
     */
    private void setUnitIsXaxis(String Player, Unit unit) {
        if (Player == "BLUE") {

            if (unit.getPath() == PathNum.FIRST) {
                unit.setIsXaxis(true);
            } else if (unit.getPath() == PathNum.SECOND || unit.getPath() == PathNum.FORTH
                    || unit.getPath() == PathNum.THIRD) {
                unit.setIsXaxis(false);

            }
        } else {
            if (unit.getPath() == PathNum.FIRST || unit.getPath() == PathNum.SECOND) {
                unit.setIsXaxis(false);
            } else if (unit.getPath() == PathNum.THIRD || unit.getPath() == PathNum.FORTH) {
                unit.setIsXaxis(true);

            }

        }

    }

    /**
     * Render each player units to the gamescreen
     *
     * @param bluePlayer
     * @param redPlayer
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     */
    public void renderPlayersUnits(Player bluePlayer, Player redPlayer, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {



        for (Unit unit : redPlayer.units) {
            if (!unit.isAlive())
                continue;

            // one by one in the path (bigger array means bigger gaps) -> more stranght for
            // the units
            unit.moveInPath(turnToRedMove, 120);
            if (turnToRedMove >= 130) {
                turnToRedMove = 0;
            } else {
                turnToRedMove++;
            }
            if (!unit.getMovedInPath())
                continue;

            // current state
            drawHealthBar(unit, batch);
            switch (unit.getCurrentState()) {
                case "WALK":

                    if (unit instanceof NormalSoldier) {
                        // path chosen
                        switch (unit.getPath()) {
                            case FIRST:
                                if (pathSettings.getPaths().get(Constants.PathNum.FIRST).get(0)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(bluePlayer, 10);
                                    unit.setAnimation(attackingRedSoldier1Animation);//////// HERE should if-else for to
                                                                                     //////// assign animation for diff
                                                                                     //////// solidiers
                                } else {
                                    if (isACornerOfPath1ForRed(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() - 1);

                                    } else {
                                        isACornerOfPath1XY("RED", unit, pathSettings);
                                        if (unit.getIsXaxis()) {

                                            controlRedUnitsAnimationX(unit, true, pathSettings, batch, elapsedTime);

                                        } else {

                                            controlRedUnitsAnimationY(unit, false, pathSettings, batch,
                                                    elapsedTime);
                                        }
                                    }
                                }
                                break;
                            case SECOND:

                                if (pathSettings.getPaths().get(Constants.PathNum.SECOND).get(0)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(bluePlayer, 10);
                                    unit.setAnimation(attackingRedSoldier1Animation);//////// HERE should if-else for to
                                                                                     //////// assign animation for diff
                                                                                     //////// solidiers

                                } else {
                                    if (isACornerOfPath2ForRed(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() - 1);

                                    } else {

                                        isACornerOfPath2XY("RED", unit, pathSettings);
                                        if (unit.getIsXaxis()) {
                                            controlRedUnitsAnimationX(unit, true, pathSettings, batch, elapsedTime);

                                        } else {

                                            if (unit.getPosition().x == pathSettings.getPaths()
                                                    .get(Constants.PathNum.SECOND).get(5).x) {

                                                controlBlueUnitsAnimationY(unit, true, pathSettings, batch,
                                                        elapsedTime);
                                            } else {
                                                controlBlueUnitsAnimationY(unit, false, pathSettings, batch,
                                                        elapsedTime);
                                            }
                                        }
                                    }
                                }
                                break;
                            case THIRD:

                                if (pathSettings.getPaths().get(PathNum.THIRD).get(1).x == unit.getPosition().x
                                        && pathSettings.getPaths().get(PathNum.THIRD).get(1).y
                                                + 25 == unit.getPosition().y) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(bluePlayer, 10);
                                    unit.setAnimation(attackingRedSoldier1Animation);//////// HERE should if-else for to
                                                                                     //////// assign animation for diff
                                                                                     //////// solidiers

                                } else {
                                    if (isACornerOfPath3ForRed(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() - 1);

                                    } else {
                                        isACornerOfPath3XY("RED", unit, pathSettings);
                                        if (unit.getIsXaxis()) {

                                            controlRedUnitsAnimationX(unit, true, pathSettings, batch, elapsedTime);

                                        } else {

                                            controlRedUnitsAnimationY(unit, false, pathSettings, batch,
                                                    elapsedTime);
                                        }
                                    }
                                }
                                break;

                            case FORTH:
                                if (pathSettings.getPaths().get(PathNum.FORTH).get(0).x == unit.getPosition().x
                                        && pathSettings.getPaths().get(PathNum.FORTH).get(0).y == unit
                                                .getPosition().y) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(bluePlayer, 10);
                                    unit.setAnimation(attackingRedSoldier1Animation);//////// HERE should if-else for to
                                                                                     //////// assign animation for diff
                                                                                     //////// solidiers

                                } else {
                                    if (isACornerOfPath4ForRed(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() - 1);

                                    } else {
                                        isACornerOfPath4XY("RED", unit, pathSettings);
                                        if (unit.getIsXaxis()) {

                                            controlRedUnitsAnimationX(unit, true, pathSettings, batch, elapsedTime);

                                        } else {

                                            controlRedUnitsAnimationY(unit, false, pathSettings, batch,
                                                    elapsedTime);
                                        }
                                    }
                                }
                                break;

                            default:
                                System.err.println("### NO PATH CHOSEN ###");
                                break;
                        }
                    } else {
                        if (pathSettings.getPaths().get(PathNum.CRAZY)
                                .get(0).x >= unit
                                        .getPosition().x) {
                            unit.setState("ATTACK");
                            unit.attackCastle(bluePlayer, 50);
                            unit.setAnimation(attackingRedSoldier3Animation);

                        } else {

                            if (isACornerOfCrazyPath(unit, "RED", pathSettings, elapsedTime, batch)) {

                                unit.setNextPathLevel(unit.getNextPathLevel() - 1);

                            } else {
                                controlCrazyRedUnitAnimationXY(unit, pathSettings, batch, elapsedTime);
                            }
                        }
                    }
                    break;
                case "ATTACK":
                    batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                            unit.getPosition().x,
                            unit.getPosition().y,
                            50, 1,
                            60,
                            45, 1, 1, 0);

                    unit.setHealth(unit.getHealth() - 5);
                    break;
                default:
                    batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                            unit.getPosition().x,
                            unit.getPosition().y,
                            50, 1,
                            60,
                            45, 1, 1, 0);
                    break;
            }

        }

        for (Unit unit : bluePlayer.units) {
            if (!unit.isAlive())
                continue;
            // one by one in the path (bigger array means bigger gaps) -> more stranght for
            // the units
            unit.moveInPath(turnToBlueMove, 120);
            if (turnToBlueMove >= 130) {
                turnToBlueMove = 0;
            } else {
                turnToBlueMove++;
            }
            if (!unit.getMovedInPath())
                continue;
            // current state
            drawHealthBar(unit, batch);
            switch (unit.getCurrentState()) {
                case "WALK":

                    if (unit instanceof NormalSoldier) {
                        // path chosen
                        switch (unit.getPath()) {
                            case FIRST:

                                if (pathSettings.getPaths().get(Constants.PathNum.FIRST)
                                        .get(pathSettings.getPaths().get(Constants.PathNum.FIRST).size() - 1)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(redPlayer, 10);
                                    unit.setAnimation(attackingBlueSoldier1Animation);//////// HERE should if-else for
                                                                                      //////// to
                                                                                      //////// assign animation for diff
                                                                                      //////// solidiers

                                } else {
                                    if (isACornerOfPath1ForBlue(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() + 1);

                                    } else {
                                        isACornerOfPath1XY("BLUE", unit, pathSettings);
                                        if (unit.getIsXaxis()) {
                                            controlBlueUnitsAnimationX(unit, false, pathSettings, batch, elapsedTime);

                                        } else {
                                            controlBlueUnitsAnimationY(unit, true, pathSettings, batch, elapsedTime);
                                        }
                                    }
                                }
                                break;
                            case SECOND:

                                if (pathSettings.getPaths().get(Constants.PathNum.SECOND)
                                        .get(pathSettings.getPaths().get(Constants.PathNum.SECOND).size() - 1)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(redPlayer, 10);
                                    unit.setAnimation(attackingBlueSoldier1Animation);//////// HERE should if-else for
                                                                                      //////// to
                                                                                      //////// assign animation for diff
                                                                                      //////// solidiers

                                } else {
                                    if (isACornerOfPath2ForBlue(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() + 1);
                                    } else {

                                        isACornerOfPath2XY("BLUE", unit, pathSettings);
                                        if (unit.getIsXaxis()) {
                                            controlBlueUnitsAnimationX(unit, false, pathSettings, batch, elapsedTime);

                                        } else {
                                            // checking if the y is increasing
                                            if (unit.getPosition().x == pathSettings.getPaths()
                                                    .get(Constants.PathNum.SECOND).get(4).x) {

                                                controlBlueUnitsAnimationY(unit, false, pathSettings, batch,
                                                        elapsedTime);
                                            } else {
                                                controlBlueUnitsAnimationY(unit, true, pathSettings, batch,
                                                        elapsedTime);

                                            }
                                        }
                                    }
                                }
                                break;
                            case THIRD:
                                if (pathSettings.getPaths().get(PathNum.THIRD)
                                        .get(pathSettings.getPaths().get(PathNum.THIRD).size() - 1)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(redPlayer, 10);
                                    unit.setAnimation(attackingBlueSoldier1Animation);//////// HERE should if-else for
                                                                                      //////// to
                                                                                      //////// assign animation for diff
                                                                                      //////// solidiers

                                } else {

                                    if (isACornerOfPath3ForBlue(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() + 1);

                                    } else {

                                        isACornerOfPath3XY("BLUE", unit, pathSettings);
                                        if (unit.getIsXaxis()) {
                                            controlBlueUnitsAnimationX(unit, false, pathSettings, batch, elapsedTime);

                                        } else {

                                            controlBlueUnitsAnimationY(unit, true, pathSettings, batch,
                                                    elapsedTime);

                                        }
                                    }
                                }
                                break;

                            case FORTH:
                                if (pathSettings.getPaths().get(PathNum.FORTH)
                                        .get(pathSettings.getPaths().get(PathNum.FORTH).size() - 1)
                                        .equals(unit.getPosition())) {

                                    unit.setState("ATTACK");
                                    unit.attackCastle(redPlayer, 10);
                                    unit.setAnimation(attackingBlueSoldier1Animation);//////// HERE should if-else for
                                                                                      //////// to
                                                                                      //////// assign animation for diff
                                                                                      //////// solidiers

                                } else {

                                    if (isACornerOfPath4ForBlue(unit, pathSettings, elapsedTime, batch)) {
                                        unit.setNextPathLevel(unit.getNextPathLevel() + 1);

                                    } else {

                                        isACornerOfPath4XY("BLUE", unit, pathSettings);
                                        if (unit.getIsXaxis()) {
                                            controlBlueUnitsAnimationX(unit, false, pathSettings, batch, elapsedTime);

                                        } else {

                                            controlBlueUnitsAnimationY(unit, true, pathSettings, batch,
                                                    elapsedTime);

                                        }
                                    }
                                }
                                break;

                            default:
                                System.err.println("### NO PATH CHOSEN ###");
                                break;
                        }
                    } else { // Crazy Soldier
                        if (pathSettings.getPaths().get(PathNum.CRAZY)
                                .get(pathSettings.getPaths().get(PathNum.CRAZY).size() - 1).x <= unit
                                        .getPosition().x) {
                            unit.setState("ATTACK");
                            unit.attackCastle(redPlayer, 50);
                            unit.setAnimation(attackingBlueSoldier3Animation);

                        } else {

                            if (isACornerOfCrazyPath(unit, "BLUE", pathSettings, elapsedTime, batch)) {
                                unit.setNextPathLevel(unit.getNextPathLevel() + 1);

                            } else {
                                controlCrazyBlueUnitAnimationXY(unit, pathSettings, batch, elapsedTime);

                            }
                        }
                    }
                    break;
                case "ATTACK":
                    batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                            unit.getPosition().x,
                            unit.getPosition().y,
                            50, 1,
                            60,
                            45, 1, 1, 0);

                    unit.setHealth(unit.getHealth() - 2);
                    break;

                default:
                    batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                            unit.getPosition().x,
                            unit.getPosition().y,
                            50, 1,
                            60,
                            45, 1, 1, 0);
                    break;
            }

        }

        // Clearing dead units
         clearDeadUnits(redPlayer);
         clearDeadUnits(bluePlayer);

    }

    /**
     * Clearing the units from the players `Unit` array lists
     */
   public void clearDeadUnits(Player player) {
        player.units.removeIf(element -> !element.isAlive());
    }

    /**
     * Check if the unit is at one of the corners of Path1 of the blue player -> to
     * change the direction
     *
     * @param unit
     * @param Player
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfCrazyPath(Unit unit, String Player, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {

        // Blue player corners switching
        if (Player.equals("BLUE") && unit.getNextPathLevel() <= 5) {
            if (unit.getPosition().x > (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x
                    || unit.getPosition().y < (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                            .get(unit.getNextPathLevel())).y && unit.getNextPathLevel() == 1) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y));
                return true;
            } else if (unit.getPosition().x > (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 2) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 0.5f));
                return true;
            } else if (unit.getPosition().x > (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 3) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.2f, unit.getPosition().y - 0.2f));
                return true;
            } else if (unit.getPosition().x > (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 4) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y));
                return true;
            } else if (unit.getPosition().x > (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 5) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y - 0.5f));
                return true;
            }

        }
        // Red player corners switching
        else if (Player.equals("RED") && unit.getNextPathLevel() >= 0) {
            if (unit.getPosition().x <= (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 4) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y));
                return true;
            } else if (unit.getPosition().x <= (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 3) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.2f, unit.getPosition().y + 0.2f));
                return true;
            } else if (unit.getPosition().x <= (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 2) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y));
                return true;
            } else if (unit.getPosition().x <= (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 1) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 0.5f));
                return true;
            } else if (unit.getPosition().x <= (pathSettings.getPaths().get(Constants.PathNum.CRAZY)
                    .get(unit.getNextPathLevel())).x && unit.getNextPathLevel() == 0) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y));
                return true;
            }
        }
        return false;
    }

    /**
     * Move crazy blue units left/right and up/down
     * 
     * @param unit
     * @param pathSettings
     * @param batch
     * @param elapsedTime
     */
    private void controlCrazyBlueUnitAnimationXY(Unit unit, PathSettings pathSettings,
            SpriteBatch batch, Float elapsedTime) {
        if (unit.getNextPathLevel() <= 5) {
            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 1) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.52f, unit.getPosition().y - 0.5f));
            }
            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 2) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.55f, unit.getPosition().y));
            }
            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 3) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y - 0.5f));
            }
            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 4) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y));
            }
            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 5) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 0.5f, unit.getPosition().y - 0.25f));
            }
        }
    }

    /**
     * Move crazy red units left/right and up/down
     * 
     * @param unit
     * @param pathSettings
     * @param batch
     * @param elapsedTime
     */
    private void controlCrazyRedUnitAnimationXY(Unit unit, PathSettings pathSettings,
            SpriteBatch batch, Float elapsedTime) {
        if (unit.getNextPathLevel() >= 0) {
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 4) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y + 0.2f));
            }
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 3) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y));
            }
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 2) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y + 0.5f));
            }
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 1) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.5f, unit.getPosition().y));
            }
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x && unit.getNextPathLevel() == 0) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 0.52f, unit.getPosition().y + 0.5f));
            }
        }
    }

    /**
     * Check if the unit is at one of the corners of Path1 of the blue player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath1ForBlue(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(1))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(2))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(3))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        }

        return false;

    }

    /**
     * Check if the unit is at one of the corners of Path1 for the red player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath1ForRed(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(3))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(2))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));// Increasing the Y axis

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.FIRST).get(1))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            return true;
        }
        return false;
    }

    /**
     * Check if the unit will walk in the X or Y axis by checking the corners
     *
     * @param unit
     * @param pathSettings
     */
    private void isACornerOfPath1XY(String Player, Unit unit, PathSettings pathSettings) {
        if (Player == "BLUE") {
            if (pathSettings.getPaths().get(Constants.PathNum.FIRST).get(3)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.FIRST).get(1)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))) {

                unit.setIsXaxis(false);

            }
            if (pathSettings.getPaths().get(Constants.PathNum.FIRST).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))) {
                unit.setIsXaxis(true);

            }
        } else {

            if ((pathSettings.getPaths().get(Constants.PathNum.FIRST).get(3)
                    .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.FIRST).get(1)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y)))) {

                unit.setIsXaxis(true);

            }
            if (pathSettings.getPaths().get(Constants.PathNum.FIRST).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))) {

                unit.setIsXaxis(false);
            }

        }

    }

    /**
     * Check if the unit will walk in the X or Y axis by checking the corners
     *
     * @param unit
     * @param pathSettings
     */
    private void isACornerOfPath2XY(String Player, Unit unit, PathSettings pathSettings) {
        if (Player.equals("BLUE")) {
            if (pathSettings.getPaths().get(Constants.PathNum.SECOND).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(6)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(8)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))) {

                unit.setIsXaxis(false);

            }
            if (pathSettings.getPaths().get(Constants.PathNum.SECOND).get(1)
                    .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(3)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(7)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))) {
                unit.setIsXaxis(true);

            }
        } else {
            if (pathSettings.getPaths().get(Constants.PathNum.SECOND).get(1)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(3)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(7)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))) {

                unit.setIsXaxis(false);

            }
            if (pathSettings.getPaths().get(Constants.PathNum.SECOND).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(6)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.SECOND).get(8)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))) {
                unit.setIsXaxis(true);

            }

        }

    }

    /**
     * Check if the unit is at one of the corners of Path3 of the blue player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath3ForBlue(Unit unit, PathSettings pathSettings, Float elapsedTime,

            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(1))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(2))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(3))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(4))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(5))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(6))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;
        }

        return false;

    }

    /**
     * Check if the unit will walk in the X or Y axis by checking the corners
     *
     * @param unit
     * @param pathSettings
     */
    private void isACornerOfPath3XY(String Player, Unit unit, PathSettings pathSettings) {
        if (Player.equals("BLUE")) {
            if (pathSettings.getPaths().get(PathNum.THIRD).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(6)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))) {

                unit.setIsXaxis(true);
            } else if (pathSettings.getPaths().get(Constants.PathNum.THIRD).get(3)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))) {
                unit.setIsXaxis(false);

            }
        } else {

            if (pathSettings.getPaths().get(Constants.PathNum.THIRD).get(3)
                    .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))) {
                unit.setIsXaxis(true);

            } else if (pathSettings.getPaths().get(Constants.PathNum.THIRD).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.THIRD).get(6)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))) {

                unit.setIsXaxis(false);

            }

        }

    }

    /**
     * Check if the unit is at one of the corners of Path3 of the red player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath3ForRed(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(6))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(5))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));// Increasing the Y axis

            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(4))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(3))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(2))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.THIRD).get(1))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;
        }
        return false;
    }

    /**
     * Check if the unit is at one of the corners of Path4 of the blue player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath4ForBlue(Unit unit, PathSettings pathSettings, Float elapsedTime,

            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(1))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(2))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(3))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(4))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(5))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(6))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            return true;
        }

        return false;

    }

    /**
     * Check if the unit will walk in the X or Y axis by checking the corners
     *
     * @param unit
     * @param pathSettings
     */
    private void isACornerOfPath4XY(String Player, Unit unit, PathSettings pathSettings) {
        if (Player.equals("BLUE")) {
            if (pathSettings.getPaths().get(PathNum.FORTH).get(1)
                    .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(3)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y))) {

                unit.setIsXaxis(true);
            } else if (pathSettings.getPaths().get(Constants.PathNum.FORTH).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1))) {
                unit.setIsXaxis(false);

            }
        } else {

            if (pathSettings.getPaths().get(Constants.PathNum.FORTH).get(3)
                    .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1)) ||
                    pathSettings.getPaths().get(Constants.PathNum.FORTH).get(1)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(5)
                            .equals(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1))) {
                unit.setIsXaxis(false);

            } else if (pathSettings.getPaths().get(Constants.PathNum.FORTH).get(2)
                    .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(4)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))
                    || pathSettings.getPaths().get(Constants.PathNum.FORTH).get(6)
                            .equals(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y))) {

                unit.setIsXaxis(true);

            }

        }

    }

    /**
     * Check if the unit is at one of the corners of Path4 of the red player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath4ForRed(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(5))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(4))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));// Increasing the Y axis

            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(3))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;

        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(2))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(PathNum.FORTH).get(1))) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));
            return true;
        }

        return false;
    }

    /**
     * Move red units left and right
     *
     * @param unit
     * @param negative left movement
     */
    private void controlRedUnitsAnimationX(Unit unit, boolean negative, PathSettings pathSettings, SpriteBatch batch,
            Float elapsedTime) {
        if (!negative) {

            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath()).get(unit.getNextPathLevel()).x) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            }

        } else {
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        pathSettings.getPaths().get(unit.getPath()).get(unit.getNextPathLevel()).y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            }

        }
    }

    /**
     * Move blue units left and right
     *
     * @param unit
     * @param negative left movement
     */
    private void controlBlueUnitsAnimationX(Unit unit, boolean negative, PathSettings pathSettings, SpriteBatch batch,
            Float elapsedTime) {
        if (!negative) {

            if (unit.getPosition().x < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));
            }

        } else {
            if (unit.getPosition().x > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).x) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        pathSettings.getPaths().get(unit.getPath()).get(unit.getNextPathLevel()).y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            }

        }
    }

    /**
     * move the red animated unit up and down
     *
     * @param unit
     * @param negative down movement
     */
    private void controlRedUnitsAnimationY(Unit unit, boolean negative, PathSettings pathSettings, SpriteBatch batch,
            Float elapsedTime) {
        if (!negative) {

            if (unit.getPosition().y < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).y) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        pathSettings.getPaths().get(unit.getPath()).get(unit.getNextPathLevel()).x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));

            }
        } else {
            if (unit.getPosition().y > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).y) {

                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            }
        }
    }

    /**
     * Check if the unit is at one of the corners of Path2 of the blue player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath2ForBlue(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {

        if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(2))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(6))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(8))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(1))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(3))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(5))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(7))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x + 1, unit.getPosition().y));

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(4))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));

            return true;
        }

        return false;

    }

    /**
     * Check if the unit is at one of the corners of Path2 for the red player -> to
     * change the direction
     *
     * @param unit
     * @param pathSettings
     * @param elapsedTime
     * @param batch
     * @return true if the unit is at one of the corners, otherwise false
     */
    private boolean isACornerOfPath2ForRed(Unit unit, PathSettings pathSettings, Float elapsedTime,
            SpriteBatch batch) {
        if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(2))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(4))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(6))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(8))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);
            unit.setPosition(new Point2D.Float(unit.getPosition().x - 1, unit.getPosition().y));
            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(1))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(3))
                || unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(7))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));

            return true;
        } else if (unit.getPosition().equals(pathSettings.getPaths().get(Constants.PathNum.SECOND).get(5))) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                    unit.getPosition().x, unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

            unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));

            return true;
        }

        return false;
    }

    /**
     * move the blue animated unit up and down
     *
     * @param unit
     * @param negative down movement
     */
    private void controlBlueUnitsAnimationY(Unit unit, boolean negative, PathSettings pathSettings, SpriteBatch batch,
            Float elapsedTime) {
        if (!negative) {

            if (unit.getPosition().y < pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).y) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        pathSettings.getPaths().get(unit.getPath()).get(unit.getNextPathLevel()).x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y + 1));

            }
        } else {
            if (unit.getPosition().y > pathSettings.getPaths().get(unit.getPath())
                    .get(unit.getNextPathLevel()).y) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true),
                        unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
                unit.setPosition(new Point2D.Float(unit.getPosition().x, unit.getPosition().y - 1));
            }
        }
    }

    /**
     * Render temp units(used when choosing random path) on the gamescreen
     *
     * @param batch
     */
    public void renderTempUnits(Player bluePlayer, Float elapsedTime, SpriteBatch batch) {
        for (Unit unit : TempUnits) {
            if (!unit.isAlive())
                continue;

            drawHealthBar(unit, batch);

            if (bluePlayer.getTurn()) {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);
            } else {
                batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                        unit.getPosition().y,
                        50, 1,
                        60,
                        45, 1, 1, 0);
            }
        }
    }

    /**
     * Draw healthbar in the gamescreen (used in render method)
     *
     * @param unit
     * @param batch
     */
    public void drawHealthBar(Unit unit, SpriteBatch batch) {
        TextureRegionDrawable healthBarFrame = new TextureRegionDrawable(Textures.HEALTH_BAR_FRAME);
        healthBarFrame.draw(batch, unit.getPosition().x + 8, unit.getPosition().y + 40, 45, 10);
        ProgressBarStyle healthBarStyle = new ProgressBar.ProgressBarStyle();
        healthBarStyle.background = new TextureRegionDrawable(new TextureRegion(Textures.HEALTH_BAR));
        healthBarStyle.background.setMinHeight(8);
        ProgressBar healthBar = new ProgressBar(0f, 1000, 1f, false, healthBarStyle);
        healthBar.setBounds(unit.getPosition().x + 17f, unit.getPosition().y + 44.2f,
                36 * (float) unit.getHealth() / Constants.FULL_UNIT_HEALTH_POINTS, 1);
        healthBar.draw(batch, 1);

    }






}
