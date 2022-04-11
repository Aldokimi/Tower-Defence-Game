package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.CrazySoldier;
import com.alphatech.game.utils.NormalSoldier;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.Unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brashmonkey.spriter.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GameScreen implements Screen {

    // Map & Camera
    private TiledMap map;
    private Viewport viewport;
    private OrthoCachedTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage gameScreenButtons;

    // Players
    private Player redPlayer;
    private Player bluePlayer;

    //////////// Paths

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

    // Place holders
    private ArrayList<Point> placeHolders;

    // Units
    private ArrayList<Unit> TempUnits;// to Store units before adding them to the user units
    private TextureRegion sold1region;
    private TextureRegionDrawable sold1regiondraw;
    private ImageButton soldier1;

    private Point unitsPosition;

    private TextureRegion sold3region;
    private TextureRegionDrawable sold3regiondraw;
    private ImageButton soldier3;

    // Units animation
    private Animation<TextureRegion> animation;
    private float elapsedTime = 0;

    // Units counter
    private int unitCountSoldier1;
    private int unitCountSoldier3;
    private BitmapFont unitCounter;

    // Timer bar
    ProgressBar timerBar;
    ProgressBarStyle timerBarStyle;
    float width = 174f;
    float stateTime = 0; // Time span between the current frame and the last frame in seconds.

    // Turn Control
    private TextureRegion endTurnRegion;
    private TextureRegionDrawable endTurnRegionDraw;
    private ImageButton endTurn;

    @Override
    public void show() {
        // Map & Camera
        map = new TmxMapLoader().load("map/map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        viewport = new FillViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        batch = new SpriteBatch();

        // Paths
        isPathChosen = false;
        chosenPath = null;
        paths = new HashMap<>();
        fillPaths();
        // Near Blue Castle
        PathArrowBlue1Region = new TextureRegion(Textures.PathArrowB);
        PathArrowBlue1RegionDraw = new TextureRegionDrawable(PathArrowBlue1Region);
        PathArrowBlue1 = new ImageButton(PathArrowBlue1RegionDraw);
        PathArrowBlue1.setPosition(paths.get(Constants.PathNum.FIRST).get(0).x * Constants.PLACEHOLDER_SIZE + 9,
                paths.get(Constants.PathNum.FIRST).get(0).y * Constants.PLACEHOLDER_SIZE + 9);
        PathArrowBlue1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(isPathChosen && chosenPath == Constants.PathNum.FIRST )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.SECOND )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.THIRD )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.FORTH )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
        PathArrowRed1.setPosition(
                paths.get(Constants.PathNum.FIRST).get(paths.get(Constants.PathNum.FIRST).size() - 1).x
                        * Constants.PLACEHOLDER_SIZE + 25,
                paths.get(Constants.PathNum.FIRST).get(paths.get(Constants.PathNum.FIRST).size() - 1).y
                        * Constants.PLACEHOLDER_SIZE + 10);
        PathArrowRed1.setTransform(true);
        PathArrowRed1.setRotation(90);
        PathArrowRed1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(isPathChosen && chosenPath == Constants.PathNum.FIRST )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.SECOND )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.THIRD )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
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
                if(isPathChosen && chosenPath == Constants.PathNum.FORTH )
                {
                    resetColorsOfPaths();
                    isPathChosen = false;
                    chosenPath = null;
                }
                else {
                    chosenPath = Constants.PathNum.FORTH;
                    isPathChosen = true;
                    colorPath4();
                }
            }
        });
        resetColorsOfPaths();

        // temp unit ArrayList
        TempUnits = new ArrayList<>();

        // Place-holders points for buildings
        placeHolders = new ArrayList<>();
        fillPlaceHolders(); // Filling the placeholders once

        // Player
        redPlayer = new Player();
        bluePlayer = new Player();

        // Unit counter
        unitCounter = new BitmapFont();

        // Starting the turn randomly every new game
        Random rand = new Random(); // instance of random class
        int randomInt = rand.nextInt(2);
        if (randomInt == 0) {
            redPlayer.setTurn(true);
            System.out.println("Red turn");
        } else {
            bluePlayer.setTurn(true);
            System.out.println("Blue turn");
        }

        // Timer
        timerBarStyle = new ProgressBar.ProgressBarStyle();
        timerBarStyle.background = new TextureRegionDrawable(new TextureRegion(Textures.TIMER_BAR));
        timerBarStyle.background.setMinHeight(13);

        timerBar = new ProgressBar(0f, 50, 1f, false, timerBarStyle);
        timerBar.setBounds(382, 793, 174, 97);

        // Turn Control
        endTurnRegion = new TextureRegion(Textures.ENDTURN_TEXT);
        endTurnRegionDraw = new TextureRegionDrawable(endTurnRegion);
        endTurn = new ImageButton(endTurnRegionDraw);

        endTurn.setSize(80, 70);
        endTurn.setPosition(825, 44);

        endTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchTurn();
            }
        });

        // Unit -- Soldier1
        sold1region = new TextureRegion(Textures.SOLDIER1);
        sold1regiondraw = new TextureRegionDrawable(sold1region);
        soldier1 = new ImageButton(sold1regiondraw);

        soldier1.setSize(110, 90);
        soldier1.setPosition(567, 38);

        soldier1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (redPlayer.getTurn()) {

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_RED.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create red unit of type 1
                    unitsPosition = new Point(820, 222);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    TempUnits.add(newUnit);/////////////////////////////////
                    unitCountSoldier1 += 1;
                } else {

                    // Animations for the initial solider (before end-turn)

                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 1
                    unitsPosition = new Point(85, 702);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    TempUnits.add(newUnit);/////////////////////////////////
                    unitCountSoldier1 += 1;

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

                if (redPlayer.getTurn()) {

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_RED.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create red unit of type 3
                    unitsPosition = new Point(820, 158);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    TempUnits.add(newUnit);/////////////////////////////////
                    unitCountSoldier3 += 1;
                } else {
                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 3
                    unitsPosition = new Point(85, 640);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    TempUnits.add(newUnit);/////////////////////////////////
                    unitCountSoldier3 += 1;
                }
            }
        });

        gameScreenButtons = new Stage(new ScreenViewport());
        gameScreenButtons.addActor(timerBar);
        gameScreenButtons.addActor(endTurn);
        gameScreenButtons.addActor(soldier1);
        gameScreenButtons.addActor(soldier3);

        gameScreenButtons.addActor(PathArrowBlue1);
        gameScreenButtons.addActor(PathArrowBlue2);
        gameScreenButtons.addActor(PathArrowBlue3);
        gameScreenButtons.addActor(PathArrowBlue4);

        gameScreenButtons.addActor(PathArrowRed1);
        gameScreenButtons.addActor(PathArrowRed2);
        gameScreenButtons.addActor(PathArrowRed3);
        gameScreenButtons.addActor(PathArrowRed4);

        // Stage should control input.
        Gdx.input.setInputProcessor(gameScreenButtons);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();

        // Rendering Placeholders
        Sprite placeHolderSprite = new Sprite(Textures.PLACE_HOLDER);

        for (Point placeHolder : placeHolders) {
            placeHolderSprite.setPosition(placeHolder.x * Constants.PLACEHOLDER_SIZE,
                    placeHolder.y * Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.draw(batch);
        }

        // Rendering Timer
        if (elapsedTime > 1 && width > 0) {
            // Decresing the timer
            width -= 0.122222222;
            timerBar.setWidth(width);
        }
        if (width < 1) {
            // Resetting the timer and its dependencies
            switchTurn();
        }

        // Rendering unit counter
        unitCounter.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        unitCounter.draw(batch, String.valueOf(unitCountSoldier1), 638, 129);// -- Soldier 1
        unitCounter.draw(batch, String.valueOf(unitCountSoldier3), 734, 129);// -- Soldier 3

        // Rendering units
        for (Unit unit : redPlayer.units) {
            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x, unit.getPosition().y,
                    50, 1,
                    60,
                    45, 1, 1, 0);

        }

        for (Unit unit : bluePlayer.units) {

            batch.draw(unit.getAnimation().getKeyFrame(elapsedTime, true), unit.getPosition().x,
                    unit.getPosition().y, 50, 1, 60, 45, 1, 1, 0);

        }
        ///// Rendering the temporary created units before choosing their path and add
        ///// them to Player units
        for (Unit unit : TempUnits) {
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
        ////

        elapsedTime += Gdx.graphics.getDeltaTime();// Time span between the current frame and the last frame in seconds.
        gameScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameScreenButtons.draw(); // Draw the ui

        /// rendering Paths arrows by turn

        if (bluePlayer.getTurn()) {
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

        batch.end();
    }

    /**
     * Resseting the timer and soldiers count, and
     * switching the turn among the two players.
     * 
     */
    public void switchTurn() {
        // Resetting the timer
        width = Constants.TIMER_CAPACITY;

        // Resetting the unit counter
        unitCountSoldier1 = 0;
        unitCountSoldier3 = 0;

        // Choosing a random path for each Unit if the player did not select a path
        if (!isPathChosen) {
            for (Unit unit : TempUnits) {
                Random rand = new Random();
                int num = rand.nextInt(4);
                unit.setPath(Constants.PathNum.values()[num]);
            }
        } else {
            for (Unit u : TempUnits) {
                u.setPath(chosenPath);
            }
        }

        if (redPlayer.getTurn()) {
            // adding the temp units to red player units
            redPlayer.units.addAll(TempUnits);
            // Switch turn to the blue player
            redPlayer.endTurn();
            bluePlayer.startTurn();
        } else {
            // adding the temp units to Blue player units
            bluePlayer.units.addAll(TempUnits);
            // Switch turn to the red player
            bluePlayer.endTurn();
            redPlayer.startTurn();
        }
        // Clearing the temporary unit array to Add new ones Next Turn
        TempUnits.clear();
        isPathChosen = false;
        chosenPath = null;
        resetColorsOfPaths();
    }

    /**
     * Showing the place holders on the map.
     */
    public void fillPlaceHolders() {

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 28; y++) {
                if (// near castle 1 (blue)
                (x == 4 && (y == 21 || y == 23)) || (y == 19 && x == 4)
                        || ((y == 19 || y == 18) && (x == 2 || x == 0) || (y == 23 && x == 15)) ||
                        ((x == 7 || x == 9) && (y == 21 || y == 23)) || (y == 19 && x == 6)
                        || (y == 16 && (x == 2 || x == 0)) || (y == 15 && x == 4) ||
                        (x == 10 && y == 21) || (y == 19 && x == 8)
                        || (y == 14 && (x == 2 || x == 0) || (x == 21 && y == 23)) ||
                        ((x == 11) && (y == 19 || y == 23)) || (y == 21 && x == 12) || (y == 12 && (x == 2 || x == 0))
                        || (y == 10 && (x == 2 || x == 4 || x == 6)) ||
                        (x == 17 && (y == 21 || y == 23) || (x == 19 && y == 21) || (y == 23 && x == 24))
                        || (y == 21 && x == 15) ||
                        (y == 17 && (x == 11 || x == 13)) || (y == 10 && (x == 8 || x == 10)) || (y == 8 && x == 1)
                        || (y == 5 && x == 6) ||
                        // near castle 2 (Red)
                        ((y == 4 || y == 8) && (x == 25 || x == 21)) || (y == 8 && (x == 22 || x == 29))
                        || (y == 6 && x == 25) ||
                        ((y == 4 || y == 6) && x == 24) || ((x == 25 || x == 27) && y == 8)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 13 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && x == 17) || (y == 15 && (x == 29 || x == 23)) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 23)) || ((y == 21 || y == 17) && x == 25)
                        || ((x == 7 || x == 14 || x == 16) && y == 7)) {
                    placeHolders.add(new Point(x, y));
                }
            }
        }
    }

    /**
     * Used to store the 4 paths inside paths hash map.
     * NOTE: Path starts from BLUE castle and ends in the RED castle
     */

    public void fillPaths() {
        /// First Path
        paths.put(Constants.PathNum.FIRST, new ArrayList<>(Arrays.asList(
                new Point(4, 22),
                new Point(24, 22),
                new Point(24, 16),
                new Point(28, 16),
                new Point(28, 8)
        // new Point(28, 16)
        )));
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

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        gameScreenButtons.dispose();
        Textures.disposeConstants();
    }

}
