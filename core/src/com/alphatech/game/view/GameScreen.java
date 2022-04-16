package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.paths.PathSettings;
import com.alphatech.game.utils.towers.*;
import com.alphatech.game.utils.units.UnitSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    // Timer bar
    private ProgressBar timerBar;
    private ProgressBarStyle timerBarStyle;
    private float width = 174f;
    private float elapsedTime = 0;// Time span between the current frame and the last frame in seconds.

    // Units
    private UnitSettings unitSettings;

    // Turn Control
    private TextureRegion endTurnRegion;
    private TextureRegionDrawable endTurnRegionDraw;
    private ImageButton endTurn;

    // Towers
    private TowerSettings towerSettings;

    // Paths
    private PathSettings pathSettings;

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

        // Paths init
        pathSettings = new PathSettings();

        // Player
        redPlayer = new Player();
        bluePlayer = new Player();

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

        // Towers Init
        towerSettings = new TowerSettings(bluePlayer, redPlayer, gameScreenButtons);

        // Units init
        unitSettings = new UnitSettings(bluePlayer, redPlayer, towerSettings);

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

        unitSettings.setUnitsAsActors(gameScreenButtons);

        pathSettings.addArrowsToGameStage(gameScreenButtons);

        towerSettings.setTowersAsActors(gameScreenButtons);
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
        towerSettings.renderPlaceHolders(batch);

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

        // rendering castles healthbars
        unitSettings.renderCastleHealthBar(bluePlayer, redPlayer, batch);

        // Rendering unit counter
        unitSettings.renderUnitCounter(batch);

        elapsedTime += Gdx.graphics.getDeltaTime();// Time span between the current frame and the last frame in seconds.
        gameScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameScreenButtons.draw(); // Draw the ui

        // rendering Paths arrows by turn
        pathSettings.setArrowsVisibility(bluePlayer.getTurn());

        // Rendering Towers
        towerSettings.renderTowers(batch);

        // rendering random barracks
        towerSettings.renderBarracks(batch);

        // Rendering Gold Mines
        towerSettings.renderGoldMines(batch);

        // Goldmines making gold
        redPlayer.makeGold(Gdx.graphics.getDeltaTime());
        towerSettings.setRedPlayerGoldCounter(redPlayer.getGold());
        bluePlayer.makeGold(Gdx.graphics.getDeltaTime());
        towerSettings.setBluePlayerGoldCounter(bluePlayer.getGold());

        // Rendering player's gold and towers prices
        towerSettings.renderPlayersGoldAndGoldMines(batch);

        // Rendering units
        unitSettings.renderPlayersUnits(bluePlayer, redPlayer, pathSettings, elapsedTime, batch);

        // Rendering the temporary created units before choosing their path and add
        // them to Player units
        unitSettings.renderTempUnits(bluePlayer, elapsedTime, batch);
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
        unitSettings.setUnitCountSoldier1(0);
        unitSettings.setUnitCountSoldier3(0);

        // Resetting highlights
        towerSettings.removeHighlight(gameScreenButtons);

        // Choosing a random path for each Unit if the player did not select a path
        pathSettings.chooseRandomPath(unitSettings.getTempUnits());

        if (redPlayer.getTurn()) {
            // Switch turn to the blue player
            redPlayer.endTurn();
            bluePlayer.startTurn();

            // Preparing to walk in the paths
            unitSettings.endTurnRedPlayerSetUp(redPlayer, pathSettings);
        } else {
            // Switch turn to the red player
            bluePlayer.endTurn();
            redPlayer.startTurn();

            // Preparing to walk in the paths
            unitSettings.endTurnBluePlayerSetUp(bluePlayer, pathSettings);
        }
        // Clearing the temporary unit array to Add new ones Next Turn
        unitSettings.clearTempUnits();
        pathSettings.setIsPathChosen(false);
        pathSettings.setChosenPath(null);
        pathSettings.resetColorsOfPaths();
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
