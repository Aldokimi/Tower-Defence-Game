package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverScreen implements Screen {


    protected Stage gameOverScreenButtons;
    private TiledMap gameOver;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera camera;
    private OrthoCachedTiledMapRenderer renderer;

    // Main Menu button
    private TextureRegion mainMenuButtonRegion;
    private TextureRegionDrawable mainMenuButtonDraw;
    private ImageButton mainMenuButton;

    // Restart Game button
    private TextureRegion playAgainButtonRegion;
    private TextureRegionDrawable playAgainButtonDraw;
    private ImageButton playAgainButton;

    // Exit button
    private TextureRegion exitButtonRegion;
    private TextureRegionDrawable exitButtonDraw;
    private ImageButton exitButton;

    // Loser
    private String loser;

    public GameOverScreen(String loser) {
        this.loser = loser;
    }

    @Override
    public void show() {
        if (this.loser.equals("redPlayer")) {
            gameOver = new TmxMapLoader().load("GameOver/GameOverRed.tmx");
        } else {
            gameOver = new TmxMapLoader().load("GameOver/GameOverBlue.tmx");
        }
        renderer = new OrthoCachedTiledMapRenderer(gameOver);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FillViewport(Constants.SCREEN_WIDTH + 70, Constants.SCREEN_HEIGHT + 20, camera);
        viewport.apply();

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();

        gameOverScreenButtons = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(gameOverScreenButtons);

        // Buttons
        // Play Again 
        playAgainButtonRegion = new TextureRegion(Textures.GAMEOVER_PLAY_AGAIN_BUTTON);
        playAgainButtonDraw = new TextureRegionDrawable(playAgainButtonRegion);
        playAgainButton = new ImageButton(playAgainButtonDraw);
        playAgainButton.setSize(40, 40);
        playAgainButton.setPosition(470, 340);
        // Go back to main menu
        mainMenuButtonRegion = new TextureRegion(Textures.INSTRUCTIONS_MAIN_MENU_BUTTON);
        mainMenuButtonDraw = new TextureRegionDrawable(mainMenuButtonRegion);
        mainMenuButton = new ImageButton(mainMenuButtonDraw);
        mainMenuButton.setSize(40, 40);
        mainMenuButton.setPosition(370, 340);
        // Exit game
        exitButtonRegion = new TextureRegion(Textures.INSTRUCTIONS_EXIT_BUTTON);
        exitButtonDraw = new TextureRegionDrawable(exitButtonRegion);
        exitButton = new ImageButton(exitButtonDraw);
        exitButton.setSize(40, 40);
        exitButton.setPosition(570, 340);

        // Add listeners
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        gameOverScreenButtons.addActor(playAgainButton);
        gameOverScreenButtons.addActor(mainMenuButton);
        gameOverScreenButtons.addActor(exitButton);

        Gdx.input.setInputProcessor(gameOverScreenButtons);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        batch.begin();

        gameOverScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameOverScreenButtons.draw(); // Draw the ui
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
        gameOverScreenButtons.dispose();
        renderer.dispose();
        Textures.disposeConstants();
    }
}
