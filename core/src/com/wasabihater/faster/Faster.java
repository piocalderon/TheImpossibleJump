package com.wasabihater.faster;

import com.badlogic.gdx.Game;
import com.wasabihater.faster.helpers.AssetLoader;
import com.wasabihater.faster.screens.GameScreen;

public class Faster extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}

}
