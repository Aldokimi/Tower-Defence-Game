package com.alphatech.game;

import com.alphatech.game.view.MainMenuScreen;
import com.badlogic.gdx.Game;

public class AlphaTech extends Game {
	private MainMenuScreen MainMenuScreen;

	public AlphaTech() {

		MainMenuScreen = new MainMenuScreen();
	}

	@Override
	public void create() {
		setScreen(MainMenuScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
