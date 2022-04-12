package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.CrazySoldier;
import com.alphatech.game.utils.NormalSoldier;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.Unit;
import com.alphatech.game.utils.towers.*;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
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

    // Placeholders
    Sprite placeHolderSprite;
    private static ArrayList<Placeholder> placeHolders;
    private ArrayList<Placeholder> placeHoldersNearBlueCastle;
    private ArrayList<Placeholder> placeHoldersNearRedCastle;

    // barracks
    private ArrayList<Placeholder> barrackPlaceholders;

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

    // Towers
    private boolean isHighlighted;

    // Normal
    private ImageButton normalTowerButton;
    private TextureRegion normalTowerRegion;
    private TextureRegionDrawable normalTowerRegionDrawable;
    private Group normalTowerHighlights;

    // Multi attack
    private ImageButton multiAttackTowerButton;
    private TextureRegion multiAttackTowerRegion;
    private TextureRegionDrawable multiAttackTowerRegionDrawable;
    private Group MultiAttackTowerHighlights;

    // Crazy
    private ImageButton crazyTowerButton;
    private TextureRegion crazyTowerRegion;
    private TextureRegionDrawable crazyTowerRegionDrawable;
    private Group crazyTowerHighlights;

    private ArrayList<Tower> towers;

    // Gold mines
    private ImageButton goldMineButton;
    private TextureRegion goldMineRegion;
    private TextureRegionDrawable goldMineRegionDrawable;
    private Group goldMineHighlights;
    private ArrayList<GoldMine> goldMines;

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
        gameScreenButtons = new Stage(new ScreenViewport());

        // Stage should control input.
        Gdx.input.setInputProcessor(gameScreenButtons);

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

        // temp unit ArrayList
        TempUnits = new ArrayList<>();

        // Place-holders points for buildings
        placeHolders = new ArrayList<>();
        placeHoldersNearBlueCastle = new ArrayList<>();
        placeHoldersNearRedCastle = new ArrayList<>();
        fillPlaceHolders(); // Filling the placeholders once
        placeHolderSprite = new Sprite(Textures.PLACE_HOLDER);

        // barracks
        barrackPlaceholders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i >= 2) {
                barrackPlaceholders.add(placeHoldersNearRedCastle.get(new Random().nextInt(placeHoldersNearRedCastle.size())));
            } else {
                barrackPlaceholders.add(placeHoldersNearBlueCastle.get(new Random().nextInt(placeHoldersNearBlueCastle.size())));
            }
        }

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

        gameScreenButtons.addActor(timerBar);

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

        gameScreenButtons.addActor(endTurn);

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

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_RED.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create red unit of type 1
                    unitsPosition = new Point(820, 222);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    redPlayer.units.add(newUnit);

                    redPlayer.trainUnit(newUnit);
                    System.out.println("red: " + redPlayer.getGold());
                    TempUnits.add(newUnit);
                } else if ( bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.TRAIN_NORMAL_SOLDIER)) {

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 1
                    unitsPosition = new Point(85, 702);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);

                    bluePlayer.trainUnit(newUnit);
                    System.out.println("blue: " + bluePlayer.getGold());
                    TempUnits.add(newUnit);
                }
                unitCountSoldier1 += 1;
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
                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_RED.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create red unit of type 3
                    unitsPosition = new Point(820, 158);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    redPlayer.units.add(newUnit);

                    redPlayer.trainUnit(newUnit);
                    System.out.print("red: " + redPlayer.getGold());
                    TempUnits.add(newUnit);
                } else if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.TRAIN_CRAZY_SOLDIER)) {
                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 3
                    unitsPosition = new Point(85, 640);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);

                    bluePlayer.trainUnit(newUnit);
                    System.out.println("blue: " + bluePlayer.getGold());
                    TempUnits.add(newUnit);
                }
                unitCountSoldier3 += 1;
            }
        });

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

        // Towers -- Normal
        normalTowerRegion = new TextureRegion(Textures.NORMAL_TOWER);
        normalTowerRegionDrawable = new TextureRegionDrawable(normalTowerRegion);
        normalTowerButton = new ImageButton(normalTowerRegionDrawable);
        normalTowerButton.setSize(Constants.UNIT_SIZE.x * 2, (float) (Constants.UNIT_SIZE.y * 2.3));
        normalTowerButton.setPosition(Constants.UNIT_SIZE.x * 3, (Constants.UNIT_SIZE.y - 11));

        bluePlayer.normalTower = new NormalTower(Textures.BLUE_NORMAL_TOWER, placeHolders);
        redPlayer.normalTower = new NormalTower(Textures.RED_NORMAL_TOWER, placeHolders);

        // Towers -- Multi-Attack
        multiAttackTowerRegion = new TextureRegion(Textures.MULTI_ATTACK_TOWER);
        multiAttackTowerRegionDrawable = new TextureRegionDrawable(multiAttackTowerRegion);
        multiAttackTowerButton = new ImageButton(multiAttackTowerRegionDrawable);
        multiAttackTowerButton.setSize((float) (Constants.UNIT_SIZE.x * 1.4), (float) (Constants.UNIT_SIZE.y * 2.3));
        multiAttackTowerButton.setPosition((float) (Constants.UNIT_SIZE.x * 4.9), (Constants.UNIT_SIZE.y - 14));

        bluePlayer.multiAttackTower = new MultiAttackTower(Textures.BLUE_MULTI_ATTACK_TOWER, placeHolders);
        redPlayer.multiAttackTower = new MultiAttackTower(Textures.RED_MULTI_ATTACK_TOWER, placeHolders);

        // Towers -- Crazy
        crazyTowerRegion = new TextureRegion(Textures.CRAZY_TOWER);
        crazyTowerRegionDrawable = new TextureRegionDrawable(normalTowerRegion);
        crazyTowerButton = new ImageButton(normalTowerRegionDrawable);
        crazyTowerButton.setSize(Constants.UNIT_SIZE.x * 2, (float) (Constants.UNIT_SIZE.y * 2.3));
        crazyTowerButton.setPosition((float) (Constants.UNIT_SIZE.x * 6.2), (Constants.UNIT_SIZE.y - 16));

        bluePlayer.crazyTower = new CrazyTower(Textures.BLUE_CRAZY_TOWER, placeHolders);
        redPlayer.crazyTower = new CrazyTower(Textures.RED_CRAZY_TOWER, placeHolders);

        // Prepare all towers for rendering
        towers = new ArrayList<>( Arrays.asList(bluePlayer.normalTower, redPlayer.normalTower, bluePlayer.multiAttackTower,
                        redPlayer.multiAttackTower, bluePlayer.crazyTower, redPlayer.crazyTower));

        // Initializing the center which we will measure from.
        bluePlayer.normalTower.initializeCenterofMeasurement(new Placeholder(2, 21));
        bluePlayer.multiAttackTower.initializeCenterofMeasurement(new Placeholder(2, 21));

        redPlayer.normalTower.initializeCenterofMeasurement(new Placeholder(27, 6));
        redPlayer.multiAttackTower.initializeCenterofMeasurement(new Placeholder(27, 6));

        // Tower's buttons listeners
        normalTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                normalTowerHighlights = new Group();
                buildTowers(bluePlayer.normalTower, redPlayer.normalTower, normalTowerHighlights);
            }
        });

        multiAttackTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MultiAttackTowerHighlights = new Group();
                buildTowers(bluePlayer.multiAttackTower, redPlayer.multiAttackTower, MultiAttackTowerHighlights);
            }
        });

        crazyTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                crazyTowerHighlights = new Group();
                buildTowers(bluePlayer.crazyTower, redPlayer.crazyTower, crazyTowerHighlights);
            }
        });

        gameScreenButtons.addActor(normalTowerButton);
        gameScreenButtons.addActor(multiAttackTowerButton);
        gameScreenButtons.addActor(crazyTowerButton);

        // Gold Mines
        goldMineRegion = new TextureRegion(Textures.GOLD_MINE);
        goldMineRegionDrawable = new TextureRegionDrawable(goldMineRegion);
        goldMineButton = new ImageButton(goldMineRegionDrawable);
        goldMineButton.setSize(110, 90);
        goldMineButton.setPosition(473, 38);

        bluePlayer.goldMine = new GoldMine(Textures.BLUE_GOLD_MINE, placeHolders);
        redPlayer.goldMine = new GoldMine(Textures.RED_GOLD_MINE, placeHolders);
        goldMines = new ArrayList<>(Arrays.asList(bluePlayer.goldMine, redPlayer.goldMine));
        // Gold Mine's button listener
        goldMineButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                goldMineHighlights = new Group();
                goldMineHighlights.setName("highlight");
                if (!isHighlighted) {
                    if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                        bluePlayer.goldMine.build();

                        for (Placeholder p : bluePlayer.goldMine.getAvailablePlaces()) {
                            if (p.isFreePlace() && !gePlaceholdersNearCastle().contains(p))
                                goldMineHighlights.addActor(highlightPlaceforGoldMine(p, bluePlayer.goldMine));
                        }
                    } else if (redPlayer.getTurn() && redPlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)){
                        // Measuring from all directions
                        redPlayer.goldMine.build();

                        for (Placeholder p : redPlayer.goldMine.getAvailablePlaces()) {
                            if (p.isFreePlace() && !gePlaceholdersNearCastle().contains(p))
                                goldMineHighlights.addActor(highlightPlaceforGoldMine(p, redPlayer.goldMine));
                        }
                    }
                    isHighlighted = true;
                    gameScreenButtons.addActor(goldMineHighlights);
                } else {
                    removeHighlight();
                }
            }
        });
        gameScreenButtons.addActor(goldMineButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();

        // Rendering Place-holders
        for (Placeholder placeHolder : placeHolders) {
            placeHolderSprite.setPosition(placeHolder.getX() *
                    Constants.PLACEHOLDER_SIZE,
                    placeHolder.getY() * Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.setSize(Constants.PLACEHOLDER_SIZE,
                    Constants.PLACEHOLDER_SIZE);
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

        elapsedTime += Gdx.graphics.getDeltaTime();// Time span between the current frame and the last frame in seconds.
        gameScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameScreenButtons.draw(); // Draw the ui

        // rendering Paths arrows by turn
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

        // Rendering Towers
        for (Tower tower : towers) {
            Sprite towerSprite = new Sprite(tower.getTowerTexture());
            for (int i = 1; i < tower.getTakenPlaces().size(); i++) {
                towerSprite.setPosition(
                        (float) (tower.getTakenPlaces().get(i).getX() * Constants.PLACEHOLDER_SIZE
                                - Constants.UNIT_SIZE.x * 0.30),
                        tower.getTakenPlaces().get(i).getY() * Constants.PLACEHOLDER_SIZE);
                towerSprite.setSize(Constants.UNIT_SIZE.x + Constants.UNIT_SIZE.x * 1 / 7,
                        Constants.UNIT_SIZE.y + Constants.UNIT_SIZE.y * 1 / 2);
                towerSprite.draw(batch);
            }

        }

        // rendering random barracks
        Sprite sprite = new Sprite(Textures.BLUE_BARRACK);
        for (int i = 0; i < barrackPlaceholders.size(); ++i) {
            if (i == barrackPlaceholders.size() / 2)
                sprite = new Sprite(Textures.RED_BARRACK);
            final int x = Constants.PLACEHOLDER_SIZE + Constants.PLACEHOLDER_SIZE / 3;
            final int y = Constants.PLACEHOLDER_SIZE + Constants.PLACEHOLDER_SIZE - Constants.PLACEHOLDER_SIZE / 3;
            sprite.setPosition(barrackPlaceholders.get(i).getX() * Constants.PLACEHOLDER_SIZE - 5,
                    barrackPlaceholders.get(i).getY() * Constants.PLACEHOLDER_SIZE - 10);
            sprite.setSize(x, y);
            sprite.draw(batch);
        }

        // Rendering Gold Mines
        for (GoldMine goldMine : goldMines) {
            Sprite goldMineSprite = new Sprite(goldMine.getGoldMineTexture());
            for (int i = 0; i < goldMine.getTakenPlaces().size(); i++) {
                goldMineSprite.setPosition(
                        (float) (goldMine.getTakenPlaces().get(i).getX() * Constants.PLACEHOLDER_SIZE
                                - Constants.UNIT_SIZE.x * 0.30),
                        goldMine.getTakenPlaces().get(i).getY() * Constants.PLACEHOLDER_SIZE);
                goldMineSprite.setSize(Constants.UNIT_SIZE.x + Constants.UNIT_SIZE.x * 1 / 10,
                        Constants.UNIT_SIZE.y + Constants.UNIT_SIZE.y * 1 / 2);
                goldMineSprite.draw(batch);
            }
        }

        // Goldmines making gold
        redPlayer.makeGold(Gdx.graphics.getDeltaTime());
        bluePlayer.makeGold(Gdx.graphics.getDeltaTime());

        batch.end();
    }

    /**
     * Building towers according the player turn.
     *
     * @param blueTower
     * @param redTower
     * @param highlights
     */
    private void buildTowers(Tower blueTower, Tower redTower, Group highlights) {
        highlights.setName("highlight");
        if (!isHighlighted) {// Checking if the button has been clicked (double click gives the same state)

            if (bluePlayer.getTurn()) {
                buildBlueTowers(blueTower, highlights);
            } else {
                buildRedTowers(redTower, highlights);
            }
            isHighlighted = true;
            gameScreenButtons.addActor(highlights);
        } else {
            removeHighlight();
        }
    }

    /**
     * Builds the blue towers when it is the red player turn.
     * We go over each tower available places then we highlight the placeholder
     * according to the given tower.
     * Then we highlight the given places and the player can build on the
     * highlighted places.
     *
     * @param tower
     * @param highlights
     */
    private void buildBlueTowers(Tower tower, Group highlights) {
        bluePlayer.multiAttackTower.build();
        bluePlayer.normalTower.build();
        bluePlayer.crazyTower.build();

        for (Placeholder p : bluePlayer.crazyTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }
        for (Placeholder p : bluePlayer.multiAttackTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }
        for (Placeholder p : bluePlayer.normalTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }

    }

    /**
     * Builds the red towers when it is the red player turn.
     * We go over each tower available places then we highlight the placeholder
     * according to the given tower.
     * Then we highlight the given places and the player can build on the
     * highlighted places.
     *
     * @param tower
     * @param highlights
     */
    private void buildRedTowers(Tower tower, Group highlights) {
        redPlayer.multiAttackTower.build();
        redPlayer.normalTower.build();
        redPlayer.crazyTower.build();

        for (Placeholder p : redPlayer.crazyTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }
        for (Placeholder p : redPlayer.multiAttackTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }
        for (Placeholder p : redPlayer.normalTower.getAvailablePlaces()) {
            if (p.isFreePlace() && !barrackPlaceholders.contains(p))
                highlights.addActor(highlightPlace(p, tower));
        }
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

        // Resetting highlights
        removeHighlight();

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
     * Filling the place holders on the map.
     * place holders are represented as the Cartesian (Euclidean) Plane R2
     */
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
                        || (y == 10 && (x == 2 || x == 4 || x == 6)) ||
                        (x == 17 && (y == 21) || (x == 19 && y == 21) || (y == 23 && x == 24))
                        || (y == 21 && x == 15) ||
                        ((y == 15 || y == 19 || y == 17) && (x == 11 || x == 13))
                        || (y == 10 && (x == 8 || x == 10)) || (y == 8 && x == 1)
                        || (y == 5 && x == 6)) {
                    placeHolders.add(new Placeholder(x, y));
                    placeHoldersNearBlueCastle.add(new Placeholder(x, y));
                }

                if ( // near red castle
                (y == 8 && (x == 21 || x == 23)) || (y == 4 && x == 21)
                        || (y == 6 && x == 22) || (x == 23 && y == 6)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 13 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && (x == 17 || x == 25 || x == 27 || x == 29))
                        || (y == 15 && (x == 29 || x == 23 || x == 17)) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 23)) || ((y == 21 || y == 17) && x == 25)
                        || ((x == 7 || x == 13 || x == 16) && y == 7)) {
                    placeHolders.add(new Placeholder(x, y));
                    placeHoldersNearRedCastle.add(new Placeholder(x, y));
                }
            }
        }
    }

    /**
     * Used to store the 4 paths inside paths hash map.
     * NOTE: Path starts from BLUE castle and ends in the RED castle
     */

    public void fillPaths() {
        // First Path
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

    /**
     * highlight the avaliable (to build on) place holder.
     * 
     * @param placeholder Place holder
     * @param type        tower class to add the new build tower to it if the
     *                    highlight is clicked.
     * @return ImageButton, the highlighted placeholder
     */
    private ImageButton highlightPlace(final Placeholder placeholder, final Tower type) {
        TextureRegion rgn = new TextureRegion(Textures.HIGHLIGHTED_PLACE_HOLDER);
        if (gePlaceholdersNearCastle().contains(placeholder))
            rgn = new TextureRegion(Textures.RED_HIGHLIGHTED_PLACE_HOLDER);
        TextureRegionDrawable rgndrbl = new TextureRegionDrawable(rgn);
        ImageButton btn = new ImageButton(rgndrbl);
        btn.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
        btn.setPosition(Constants.PLACEHOLDER_SIZE * (placeholder.getX()),
                Constants.PLACEHOLDER_SIZE * (placeholder.getY()));

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int price;
                if (type instanceof NormalTower) {
                    price = Constants.BUILD_NORMAL_TOWER;
                } else {
                    price = Constants.BUILD_MULTIATTACK_TOWER;
                }
                if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(price)) {
                    if (!gePlaceholdersNearCastle().contains(placeholder)) {
                        type.addTower(placeholder);
                        bluePlayer.buildTower(type);

                        type.addTower(placeholder);

                        placeholder.takePlace();

                        type.releaseAvailablePlaces();
                    }

                    System.out.println("blue: " + bluePlayer.getGold());
                } else if (redPlayer.hasEnoughGold(price)) {
                    if (!gePlaceholdersNearCastle().contains(placeholder)) {
                        type.addTower(placeholder);
                        redPlayer.buildTower(type);

                        type.addTower(placeholder);

                        placeholder.takePlace();

                        type.releaseAvailablePlaces();
                    }

                    System.out.println("red: " + redPlayer.getGold());
                }
                removeHighlight();
            }
        });
        return btn;
    }

    /**
     * Highlight a placeholder of the map so we can build a gold  mine on it.
     *
     * @param placeholder Place holder
     * @param goldMine    if the highlighted placeholder is clicked we build into it.
     * @return ImageButton, the highlighted placeholder
     */
    private ImageButton highlightPlaceforGoldMine(final Placeholder placeholder, final GoldMine goldMine) {
        TextureRegion rgn = new TextureRegion(Textures.HIGHLIGHTED_PLACE_HOLDER);
        TextureRegionDrawable rgndrbl = new TextureRegionDrawable(rgn);
        ImageButton btn = new ImageButton(rgndrbl);
        btn.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
        btn.setPosition(Constants.PLACEHOLDER_SIZE * (placeholder.getX()),
                Constants.PLACEHOLDER_SIZE * (placeholder.getY()));

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                goldMine.addPlaceholder(placeholder);
                placeholder.takePlace();
                goldMine.releaseAvailablePlaces();

                removeHighlight();

                if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                    bluePlayer.buildGoldMine();
                    System.out.println("blue player: " + bluePlayer.goldMineCounter);
                    System.out.println("blue: " + bluePlayer.getGold());
                } else if (redPlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                    redPlayer.buildGoldMine();
                    System.out.println("red player: " + redPlayer.goldMineCounter);
                    System.out.println("red: " + redPlayer.getGold());
                }
            }
        });
        return btn;
    }

    /**
     * Removes the highlight from the place holders
     */
    private void removeHighlight() {
        for (Actor actor : gameScreenButtons.getActors()) {
            if (actor.getName() == "highlight") {
                actor.remove();
            }
        }
        isHighlighted = false;
    }

    /**
     * Get the placeholders array.
     *
     * @return array contains the placeholder of the game.
     */
    public static ArrayList<Placeholder> getPlaceHolders() {
        return placeHolders;
    }

    /**
     * Get the placeholder near the opposite castle of each player to prevent
     * building on them.
     *
     * @return array of place holders.
     */
    private ArrayList<Placeholder> gePlaceholdersNearCastle() {
        if (this.redPlayer.getTurn())
            return new ArrayList<>(Arrays.asList(
                    new Placeholder(0, 18),
                    new Placeholder(2, 18),
                    new Placeholder(4, 18),
                    new Placeholder(6, 19),
                    new Placeholder(6, 21),
                    new Placeholder(5, 23)));
        return new ArrayList<>(Arrays.asList(
                new Placeholder(23, 6),
                new Placeholder(25, 8),
                new Placeholder(27, 8),
                new Placeholder(29, 8)));

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
