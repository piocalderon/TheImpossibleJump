package com.wasabihater.faster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.wasabihater.faster.gameworld.GameRenderer;
import com.wasabihater.faster.gameworld.GameWorld;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;

	public GameScreen() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
	
		float gameHeight = 136;
		float gameWidth = gameHeight * (screenWidth / screenHeight);

		int midPointY = (int) (gameHeight / 2);
		
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int) gameWidth, midPointY);
		
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
