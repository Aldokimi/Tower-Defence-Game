package com.alphatech.game.desktop;

import com.alphatech.game.helpers.Constants;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.alphatech.game.AlphaTech;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Tower Defense");
		config.setWindowedMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		config.setResizable(false);
		config.setWindowIcon("map/logo.png");
		new Lwjgl3Application(new AlphaTech(), config);
	}
}
