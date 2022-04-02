package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.CrazySoldier;
import com.alphatech.game.utils.NormalSoldier;
import com.alphatech.game.utils.Player;
import com.alphatech.game.utils.Unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brashmonkey.spriter.Point;

import java.util.ArrayList;
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
    private ArrayList<Point> placeHolders;

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

    // Turn Control
    private TextureRegion endTurnRegion;
    private TextureRegionDrawable endTurnRegionDraw;
    private ImageButton endTurn;

    @Override
    public void show() {
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

        // Place holders points for buildings
        placeHolders = new ArrayList<>();

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

        // Turn Control
        endTurnRegion = new TextureRegion(Textures.ENDTURN_TEXT);
        endTurnRegionDraw = new TextureRegionDrawable(endTurnRegion);
        endTurn = new ImageButton(endTurnRegionDraw);

        endTurn.setSize(80, 70);
        endTurn.setPosition(825, 44);

        endTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
                    redPlayer.units.add(newUnit);

                } else {

                    // Animations for the initial solider (before end-turn)

                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER1_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 1
                    unitsPosition = new Point(85, 702);
                    NormalSoldier newUnit = new NormalSoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);

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

                } else {
                    // Animations for the initial solider (before end-turn)
                    animation = new Animation<TextureRegion>(0.08f, Textures.SOLDIER3_IDLE_BLUE.findRegions("idle"),
                            PlayMode.LOOP);

                    // Create blue unit of type 3
                    unitsPosition = new Point(85, 640);
                    CrazySoldier newUnit = new CrazySoldier(unitsPosition);
                    newUnit.setAnimation(animation);
                    bluePlayer.units.add(newUnit);

                }
            }
        });

        gameScreenButtons = new Stage(new ScreenViewport());
        gameScreenButtons.addActor(endTurn);
        gameScreenButtons.addActor(soldier1);
        gameScreenButtons.addActor(soldier3);

        // Stage should controll input.
        Gdx.input.setInputProcessor(gameScreenButtons);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        fillPlaceHolders();

        batch.begin();

        // Rendering Placeholders
        Sprite placeHolderSprite = new Sprite(Textures.PLACE_HOLDER);

        for (Point placeHolder : placeHolders) {
            placeHolderSprite.setPosition(placeHolder.x * Constants.PLACEHOLDER_SIZE,
                    placeHolder.y * Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.draw(batch);
        }

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

        elapsedTime += Gdx.graphics.getDeltaTime();
        gameScreenButtons.act(Gdx.graphics.getDeltaTime()); // Perform ui logic
        gameScreenButtons.draw(); // Draw the ui

        batch.end();
    }

    public void fillPlaceHolders() {

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 28; y++) {
                if (// near castle 1
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
                        // near castle 2
                        ((y == 4 || y == 8) && (x == 25 || x == 23)) || (y == 8 && (x == 22 || x == 29))
                        || (y == 6 && x == 25) ||
                        ((y == 4 || y == 6) && x == 22) || ((x == 25 || x == 27) && y == 8)
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
        Textures.disposeConstants();
    }

}
