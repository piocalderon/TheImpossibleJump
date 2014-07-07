package com.wasabihater.faster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wasabihater.faster.Faster;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Faster!";
		config.height = 272;
		config.width = 408;
		config.useGL30 = false;
		new LwjglApplication(new Faster(), config);
	}
}
