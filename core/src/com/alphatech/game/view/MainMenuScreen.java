package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {

    private TiledMap mainMenu;
    private SpriteBatch batch;
    protected Stage mainmenuScreenButtons;
    private Viewport viewport;
    private OrthographicCamera camera;
    private OrthoCachedTiledMapRenderer renderer;
    private GameScreen gameScreen;

    // Menu Buttons
    // Start button
    private TextureRegion startButtonRegion;
    private TextureRegionDrawable startButtonDraw;
    private ImageButton startButton;
    // Load button
    private TextureRegion loadButtonRegion;
    private TextureRegionDrawable loadButtonDraw;
    private ImageButton loadButton;
    // Settings button
    private TextureRegion settingsButtonRegion;
    private TextureRegionDrawable settingsButtonDraw;
    private ImageButton settingsButton;
    // Exit Button
    private TextureRegion exitButtonRegion;
    private TextureRegionDrawable exitButtonDraw;
    private ImageButton exitButton;

    @Override
    public void show() {
        mainMenu = new TmxMapLoader().load("MainMenu/MainMenu.tmx");
        renderer = new OrthoCachedTiledMapRenderer(mainMenu);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FillViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        gameScreen = new GameScreen();
        mainmenuScreenButtons = new Stage(new ScreenViewport());

        // Buttons
        startButtonRegion = new TextureRegion(Textures.MAIN_MENU_START_TEXT);
        startButtonDraw = new TextureRegionDrawable(startButtonRegion);
        startButton = new ImageButton(startButtonDraw);
        startButton.setSize(210, 114);
        startButton.setPosition(370, 522);

        loadButtonRegion = new TextureRegion(Textures.MAIN_MENU_LOAD_TEXT);
        loadButtonDraw = new TextureRegionDrawable(loadButtonRegion);
        loadButton = new ImageButton(loadButtonDraw);
        loadButton.setSize(210, 114);
        loadButton.setPosition(370, 430);

        settingsButtonRegion = new TextureRegion(Textures.MAIN_MENU_SETTINGS_TEXT);
        settingsButtonDraw = new TextureRegionDrawable(settingsButtonRegion);
        settingsButton = new ImageButton(settingsButtonDraw);
        settingsButton.setSize(160, 90);
        settingsButton.setPosition(392, 348);

        exitButtonRegion = new TextureRegion(Textures.MAIN_MENU_EXIT_TEXT);
        exitButtonDraw = new TextureRegionDrawable(exitButtonRegion);
        exitButton = new ImageButton(exitButtonDraw);
        exitButton.setSize(200, 110);
        exitButton.setPosition(374, 248);

        // Add listeners to buttons
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(gameScreen);
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        loadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences prefs = Gdx.app.getPreferences("tower-defense");
                ((Game) Gdx.app.getApplicationListener()).setScreen(gameScreen);

                // setting the stored data
                // gold
                gameScreen.redPlayer.setGold(prefs.getInteger("Rgold", Constants.INIT_GOLD_COUNT));
                gameScreen.bluePlayer.setGold(prefs.getInteger("Bgold", Constants.INIT_GOLD_COUNT));

                // turn
                gameScreen.redPlayer.setTurn(prefs.getBoolean("Rturn", false));
                gameScreen.bluePlayer.setTurn(prefs.getBoolean("Bturn", false));

                // health
                gameScreen.redPlayer.setHealth(prefs.getInteger("Rhealth", Constants.INIT_HEALTH));
                gameScreen.bluePlayer.setHealth(prefs.getInteger("Bhealth", Constants.INIT_HEALTH));

                // timer
                gameScreen.redPlayer.setTimer(prefs.getFloat("Rtimer", 0));
                gameScreen.bluePlayer.setTimer(prefs.getFloat("Btimer", 0));

            }
        });

        mainmenuScreenButtons.addActor(startButton);
        mainmenuScreenButtons.addActor(loadButton);
        mainmenuScreenButtons.addActor(settingsButton);
        mainmenuScreenButtons.addActor(exitButton);
        // Stage should controll input:
        Gdx.input.setInputProcessor(mainmenuScreenButtons);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        batch.begin();

        mainmenuScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        mainmenuScreenButtons.draw(); // Draw the ui
        batch.end();
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
        mainMenu.dispose();
        renderer.dispose();
        Textures.disposeConstants();
    }
}