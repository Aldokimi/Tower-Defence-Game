package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;
import java.util.ArrayList;

public class GameScreen implements Screen {

    private TiledMap map;
    private Viewport viewport;
    private OrthoCachedTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ArrayList<Drawer> drawers;
    private ArrayList<Point> placeHolders;
    private Texture placeHolder;

    @Override
    public void show() {
        map = new TmxMapLoader().load("map/map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        viewport = new FillViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        batch = new SpriteBatch();
        drawers = new ArrayList<>();
        placeHolders = new ArrayList<>();
        placeHolder = new Texture("GroundTiles/BuildGround.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        fillPlaceHolders();
        batch.begin();
        for (Drawer drawer: drawers) {
            Sprite sprite = drawer.sprite;
            sprite.setPosition(drawer.x, drawer.y);
            sprite.setSize(Constants.UNIT_SIZE, Constants.UNIT_SIZE);
            sprite.draw(batch);
        }
        Sprite sprite = new Sprite(placeHolder);

        for (Point placeHolder : placeHolders) {
                sprite.setPosition( placeHolder.x * Constants.UNIT_SIZE, placeHolder.y * Constants.UNIT_SIZE);
                sprite.setSize(Constants.UNIT_SIZE, Constants.UNIT_SIZE);
                sprite.draw(batch);
        }
        batch.end();
    }

    public void draw(Texture texture, int x, int y){
        drawers.add(new Drawer(new Sprite(texture), x * Constants.UNIT_SIZE, y * Constants.UNIT_SIZE ));
    }

    public void fillPlaceHolders(){

        for (int x = 0 ; x < 30; x++){
            for (int y = 0; y < 28; y++) {
                if(// near castle 1
                    (x == 4 && (y == 21 || y == 23)) || (y == 19 && x == 4) || ( (y == 19 || y == 18) && (x == 2 || x == 0) || (y == 23 && x == 15)) ||
                    ((x == 7 || x == 9) && (y == 21 || y == 23)) || (y == 19 && x == 6) || (y == 16 && (x == 2 || x == 0)) || (y == 15 && x == 4) ||
                    (x == 10 && y == 21) || (y == 19 && x == 8) || (y == 14 && (x == 2 || x == 0) || (x == 21 && y == 23)) ||
                    ((x == 11 ) && (y == 19 || y == 23)) || (y == 21 && x == 12 )|| (y == 12 && (x == 2 || x == 0)) || (y == 10 && (x == 2 || x == 4 || x == 6)) ||
                    ( x == 17 && (y == 21 || y == 23) || (x == 19  && y == 21)|| (y == 23 && x == 24)) || (y == 21 && x == 15) ||
                    (y == 17 && (x == 11 || x == 13)) || (y == 10 && (x == 8 || x == 10) ) || (y == 8 && x == 1) || (y == 5 && x == 6) ||
                    // near castle 2
                    ((y == 4 || y == 8) && (x == 25 || x == 23)) || (y == 8 && (x == 22 || x == 29)) || (y == 6 && x == 25) ||
                    ((y == 4 || y == 6) && x == 22) || ((x == 25 || x == 27) && y == 8) || (y == 10 && (x == 23 ||x == 25 )) || (y == 11 && (x == 29 || x == 27)) ||
                    (y == 6 && x == 20) || (y == 12 && ( x == 13 || x == 10 || x == 8 ||x == 23)) || (y == 13 &&(x == 27 || x == 29)) ||
                    ((y == 4 || y == 6) && x == 18) || (y == 8 && x == 17) || (y == 15 && (x == 29 || x == 23)) ||
                    (y == 10 && x == 20 ) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                    (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14)) || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                    (y == 17 && ( x == 18|| x == 21 || x == 23) ) || ((y == 21 || y == 17) &&  x == 25) || ((x == 7 || x== 14 || x == 16 ) && y == 7)
                ){
                    placeHolders.add(new Point(x, y));
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth  = width;
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
    }

    private class Drawer{
        private int x;
        private int y;
        private Sprite sprite;
        public Drawer(Sprite sprite, int x, int y){
            this.x = x;
            this.y = y;
            this.sprite = sprite;
        }
    }
}
