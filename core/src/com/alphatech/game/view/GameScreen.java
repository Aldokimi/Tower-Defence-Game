package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.model.Player;
import com.alphatech.game.model.paths.PathSettings;
import com.alphatech.game.model.persistance.SaveGame;
import com.alphatech.game.model.towers.TowerSettings;
import com.alphatech.game.model.units.UnitSettings;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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

    // Players
    private Player redPlayer;
    private Player bluePlayer;
    // Towers
    private TowerSettings towerSettings;
    // Paths
    private PathSettings pathSettings;
    // Map & Camera
    private TiledMap map;
    private Viewport viewport;
    private OrthoCachedTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private MainMenuScreen menuScreen;
    private Stage gameScreenButtons;
    // Timer bar
    private ProgressBar timerBar;
    private ProgressBarStyle timerBarStyle;
    private float elapsedTime = 0;// Time span between the current frame and the last frame in seconds.
    private float width = 174f;
    // Units
    private UnitSettings unitSettings;
    // Turn Control
    private TextureRegion endTurnRegion;
    private TextureRegionDrawable endTurnRegionDraw;
    private ImageButton endTurn;
    // Options and Save
    private TextureRegion optionsRegion;
    private TextureRegionDrawable optionsRegionDraw;
    private ImageButton optionsButton;
    private TextureRegion saveRegion;
    private TextureRegionDrawable saveRegionDraw;
    private ImageButton saveButton;

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
        menuScreen = new MainMenuScreen();
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
        } else {
            bluePlayer.setTurn(true);
        }

        // Towers Init
        towerSettings = new TowerSettings(bluePlayer, redPlayer, gameScreenButtons);

        // Units init
        unitSettings = new UnitSettings(bluePlayer, redPlayer, towerSettings, pathSettings);

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

        // Options and Save
        optionsRegion = new TextureRegion(Textures.OPTIONS);
        optionsRegionDraw = new TextureRegionDrawable(optionsRegion);
        optionsButton = new ImageButton(optionsRegionDraw);

        optionsButton.setSize(45, 45);
        optionsButton.setPosition(55, 52);

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });

        saveRegion = new TextureRegion(Textures.SAVE);
        saveRegionDraw = new TextureRegionDrawable(saveRegion);
        saveButton = new ImageButton(saveRegionDraw);

        saveButton.setSize(49, 49);
        saveButton.setPosition(95, 52);

        saveButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                new SaveGame(GameScreen.this);
            }
        });

        gameScreenButtons.addActor(optionsButton);
        gameScreenButtons.addActor(saveButton);

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

        // Set enemies for towers
        towerSettings.setEnemies(this.redPlayer, this.bluePlayer);

        // Rendering Towers
        towerSettings.renderTowers(batch, this.redPlayer, this.bluePlayer);
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

        // pleacing a treasure chest
        pathSettings.placeTreasureChests(batch, Gdx.graphics.getDeltaTime(), redPlayer, bluePlayer);

        towerSettings.hideFireBallesInCorner(batch);

        // Rendering units
        unitSettings.renderPlayersUnits(bluePlayer, redPlayer, pathSettings, elapsedTime, batch);

        // Rendering the temporary created units before choosing their path and add
        // them to Player units
        unitSettings.renderTempUnits(bluePlayer, elapsedTime, batch);

        batch.end();

        if (bluePlayer.hasLost()) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen("redPlayer"));
        }
        if (redPlayer.hasLost()) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen("bluePlayer"));
        }
    }

    public Player getBluePlayer() {
        return bluePlayer;
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public UnitSettings getUnitSettings() {
        return unitSettings;
    }

    public TowerSettings getTowerSettings() {
        return towerSettings;
    }

    public float getWidth() {
        return width;
    }

    public PathSettings getPathSettings() {
        return pathSettings;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Resseting the timer and soldiers count, and
     * switching the turn among the two players.
     */
    public void switchTurn() {
        // Resetting the timer
        width = Constants.TIMER_CAPACITY;

        // Resetting the unit counter
        unitSettings.setUnitCountSoldier1(0);
        unitSettings.setUnitCountSoldier3(0);

        // Resetting highlights
        towerSettings.removeHighlight(gameScreenButtons);

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
