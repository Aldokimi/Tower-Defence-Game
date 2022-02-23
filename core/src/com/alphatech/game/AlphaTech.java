package com.alphatech.game;

import com.alphatech.game.view.GameScreen;
import com.badlogic.gdx.Game;

public class AlphaTech extends Game {


	@Override
	public void create() {
		setScreen(new GameScreen());
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
