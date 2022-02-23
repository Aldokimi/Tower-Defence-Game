package com.alphatech.game.view;

import com.alphatech.game.helpers.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private TiledMap map;
    private Viewport viewport;
    private OrthoCachedTiledMapRenderer renderer;
    private OrthographicCamera camera;

    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        camera = new OrthographicCamera();
        viewport = new FillViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

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
}
