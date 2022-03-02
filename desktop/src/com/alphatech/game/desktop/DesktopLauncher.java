package com.alphatech.game.desktop;

import com.alphatech.game.helpers.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alphatech.game.AlphaTech;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tower Defense";
		config.height = Constants.SCREEN_HEIGHT;
		config.width = Constants.SCREEN_WIDTH;
		config.resizable = false;
		new LwjglApplication( new AlphaTech(), config);
	}
}
