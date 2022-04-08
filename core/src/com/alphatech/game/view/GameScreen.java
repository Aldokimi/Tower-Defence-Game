package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.CrazySoldier;
import com.alphatech.game.utils.NormalSoldier;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.Unit;
import com.alphatech.game.utils.towers.MultiAttackTower;
import com.alphatech.game.utils.towers.NormalTower;
import com.alphatech.game.utils.towers.Placeholder;
import com.alphatech.game.utils.towers.Tower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

    // Place holders
    Sprite placeHolderSprite;
    private ArrayList<Placeholder> placeHolders;

    // Units
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
    private ImageButton normalTowerButton;
    private boolean isHighlighted;
    private TextureRegion normalTowerRegion;
    private TextureRegionDrawable normalTowerRegionDrawable;
    private ImageButton multiAttackTowerButton;
    private TextureRegion multiAttackTowerRegion;
    private TextureRegionDrawable multiAttackTowerRegionDrawable;
    private Group normalTowerHighlights;
    private Group MultiAttackTowerHighlights;
    private Tower blueNormalTower;
    private Tower redNormalTower;
    private Tower blueMultiAttackTower;
    private Tower redMultiAttackTower;
    private ArrayList<Tower> towers;

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

        // Stage should controll input.
        Gdx.input.setInputProcessor(gameScreenButtons);

        // Place holders points for buildings
        placeHolders = new ArrayList<>();
        fillPlaceHolders(); // Filling the placeholders once
        placeHolderSprite = new Sprite(Textures.PLACE_HOLDER);

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
                if (redPlayer.getTurn()) {

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_RED.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create red unit of type 1
                    unitsPosition = new Point(820, 222);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    redPlayer.units.add(newUnit);
                    unitCountSoldier1 += 1;
                } else {

                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 1
                    unitsPosition = new Point(85, 702);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);
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
                    redPlayer.units.add(newUnit);
                    unitCountSoldier3 += 1;
                } else {
                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 3
                    unitsPosition = new Point(85, 640);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);
                    unitCountSoldier3 += 1;
                }
            }
        });

        gameScreenButtons.addActor(soldier1);
        gameScreenButtons.addActor(soldier3);

        // Towers -- Normal
        normalTowerRegion = new TextureRegion(Textures.NORMAL_TOWER);
        normalTowerRegionDrawable = new TextureRegionDrawable(normalTowerRegion);
        normalTowerButton = new ImageButton(normalTowerRegionDrawable);
        normalTowerButton.setSize(Constants.UNIT_SIZE.x * 2, (float) (Constants.UNIT_SIZE.y * 2.3));
        normalTowerButton.setPosition(Constants.UNIT_SIZE.x * 3, (Constants.UNIT_SIZE.y - 11));

        blueNormalTower = new NormalTower(Textures.BLUE_NORMAL_TOWER, placeHolders);
        redNormalTower = new NormalTower(Textures.RED_NORMAL_TOWER, placeHolders);

        // Towers -- Multi-Attack
        multiAttackTowerRegion = new TextureRegion(Textures.MULTI_ATTACK_TOWER);
        multiAttackTowerRegionDrawable = new TextureRegionDrawable(multiAttackTowerRegion);
        multiAttackTowerButton = new ImageButton(multiAttackTowerRegionDrawable);
        multiAttackTowerButton.setSize((float) (Constants.UNIT_SIZE.x * 1.4),
                (float) (Constants.UNIT_SIZE.y * 2.3));
        multiAttackTowerButton.setPosition((float) (Constants.UNIT_SIZE.x * 4.9), (Constants.UNIT_SIZE.y - 14));

        blueMultiAttackTower = new MultiAttackTower(Textures.BLUE_MULTI_ATTACK_TOWER, placeHolders);
        redMultiAttackTower = new MultiAttackTower(Textures.RED_MULTI_ATTACK_TOWER, placeHolders);

        // Prepare all towers for rendering
        towers = new ArrayList<>(
                Arrays.asList(blueNormalTower, redNormalTower, blueMultiAttackTower, redMultiAttackTower));

        // Initializing the center which we will measure from.
        blueNormalTower.initializeCenterofMeasurement(new Placeholder(2, 21));
        blueMultiAttackTower.initializeCenterofMeasurement(new Placeholder(2, 21));

        redNormalTower.initializeCenterofMeasurement(new Placeholder(27, 6));
        redMultiAttackTower.initializeCenterofMeasurement(new Placeholder(27, 6));

        // Tower's buttons listeners
        normalTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                normalTowerHighlights = new Group();
                normalTowerHighlights.setName("highlight");
                if (!isHighlighted) {// Checking if the button has been clicked (double click gives the same state)

                    if (bluePlayer.getTurn()) {

                        // Measuring from all directions
                        blueNormalTower.build();
                        blueMultiAttackTower.build();

                        for (Placeholder p : blueNormalTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                normalTowerHighlights.addActor(highlightPlace(p, blueNormalTower));
                        }
                        for (Placeholder p : blueMultiAttackTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                normalTowerHighlights.addActor(highlightPlace(p, blueNormalTower));
                        }
                    } else {
                        // Measuring from all directions
                        redNormalTower.build();
                        redMultiAttackTower.build();

                        for (Placeholder p : redNormalTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                normalTowerHighlights.addActor(highlightPlace(p, redNormalTower));

                        }
                        for (Placeholder p : redMultiAttackTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                normalTowerHighlights.addActor(highlightPlace(p, redNormalTower));
                        }
                    }
                    isHighlighted = true;
                    gameScreenButtons.addActor(normalTowerHighlights);
                } else {
                    removeHighlight();
                }
            }
        });

        multiAttackTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MultiAttackTowerHighlights = new Group();
                MultiAttackTowerHighlights.setName("highlight");
                if (!isHighlighted) {// Checking if the button has been clicked (double click gives the same state)

                    if (bluePlayer.getTurn()) {
                        // Measuring from all directions
                        blueMultiAttackTower.build();
                        blueNormalTower.build();

                        for (Placeholder p : blueMultiAttackTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                MultiAttackTowerHighlights.addActor(highlightPlace(p, blueMultiAttackTower));
                        }
                        for (Placeholder p : blueNormalTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                MultiAttackTowerHighlights.addActor(highlightPlace(p, blueMultiAttackTower));
                        }

                    } else {
                        // Measuring from all directions
                        redMultiAttackTower.build();
                        redNormalTower.build();

                        for (Placeholder p : redMultiAttackTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                MultiAttackTowerHighlights.addActor(highlightPlace(p, redMultiAttackTower));
                        }
                        for (Placeholder p : redNormalTower.getAvailablePlaces()) {
                            if (p.isFreePlace())
                                MultiAttackTowerHighlights.addActor(highlightPlace(p, redMultiAttackTower));
                        }
                    }
                    isHighlighted = true;
                    gameScreenButtons.addActor(MultiAttackTowerHighlights);
                } else {
                    removeHighlight();
                }
            }
        });

        gameScreenButtons.addActor(normalTowerButton);
        gameScreenButtons.addActor(multiAttackTowerButton);

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
            // Reseting the timer and it's dependencies
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

        elapsedTime += Gdx.graphics.getDeltaTime();// Time span between the current frame and the last frame in seconds.
        gameScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameScreenButtons.draw(); // Draw the ui

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

        // Removing highlights (if there's any)
        removeHighlight();

        if (redPlayer.getTurn()) {
            // Switch turn to the blue player
            redPlayer.endTurn();
            bluePlayer.startTurn();
        } else {
            // Switch turn to the red player
            bluePlayer.endTurn();
            redPlayer.startTurn();
        }
    }

    /**
     * Filling the place holders on the map.
     * place holders are represented as the Cartesian (Euclidean) Plane R2
     */
    public void fillPlaceHolders() {

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 28; y++) {
                if (// near blue castle
                ((x == 4 || x == 6) && (y == 21 || y == 23)) || (y == 19 && x == 4)
                        || ((y == 19) && (x == 2 || x == 0) || (y == 23 && x == 15)) ||
                        ((x == 7 || x == 7 || x == 9) && (y == 21 || y == 23)) || (y == 19 && x == 6)
                        || (y == 16 && (x == 2 || x == 0)) || (y == 15 && x == 4) ||
                        (x == 10 && y == 21) || (y == 19 && x == 8)
                        || (y == 14 && (x == 2 || x == 0) || (x == 21 && y == 23)) ||
                        ((x == 11) && (y == 19 || y == 23)) || (y == 21 && x == 12) || (y == 12 && (x == 2 || x == 0))
                        || (y == 10 && (x == 2 || x == 4 || x == 6)) ||
                        (x == 17 && (y == 21 || y == 23) || (x == 19 && y == 21) || (y == 23 && x == 24))
                        || (y == 21 && x == 15) ||
                        (y == 17 && (x == 11 || x == 13)) || (y == 10 && (x == 8 || x == 10)) || (y == 8 && x == 1)
                        || (y == 5 && x == 6) ||
                        // near red castle
                        ((y == 4 || y == 8) && (x == 25 || x == 21)) || (y == 8 && (x == 22 || x == 29))
                        || (y == 6 && x == 25) ||
                        ((y == 4 || y == 6) && x == 24) || ((x == 25 || x == 27) && y == 8)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 13 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && x == 17)
                        || (y == 15 && (x == 29 || x == 23 || x == 17)) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 23)) || ((y == 21 || y == 17) && x == 25)
                        || ((x == 7 || x == 13 || x == 16) && y == 7)) {
                    placeHolders.add(new Placeholder(x, y));
                }
            }
        }
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
        TextureRegionDrawable rgndrbl = new TextureRegionDrawable(rgn);
        ImageButton btn = new ImageButton(rgndrbl);
        btn.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
        btn.setPosition(Constants.PLACEHOLDER_SIZE * (placeholder.getX()),
                Constants.PLACEHOLDER_SIZE * (placeholder.getY()));

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                type.addTower(placeholder);

                placeholder.takePlace();

                type.releaseAvailablePlaces();

                removeHighlight();
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
