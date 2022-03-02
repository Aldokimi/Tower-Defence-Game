package com.alphatech.game;

import com.alphatech.game.view.GameScreen;
import com.badlogic.gdx.Game;

public class AlphaTech extends Game {

	private GameScreen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose(){
		super.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}
