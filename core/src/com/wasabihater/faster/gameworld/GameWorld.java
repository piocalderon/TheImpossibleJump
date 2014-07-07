package com.wasabihater.faster.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.wasabihater.faster.gameobjects.Jumper;
import com.wasabihater.faster.gameobjects.ScrollHandler;
import com.wasabihater.faster.helpers.AssetLoader;

public class GameWorld {
	
	private Jumper jumper; // main character
	private ScrollHandler scroller; // will handle obstacles
	private Rectangle floor;
	private int midPointY;
	
	private int score = 0;
	
	public enum GameState {
		READY, RUNNING, GAMEOVER, HIGHSCORE
	};
	
	private GameState gameState;
	
	public GameWorld(int midPointY) {
		gameState = GameState.READY;
		scroller = new ScrollHandler(this);
		floor = new Rectangle(0, midPointY + 34, Gdx.graphics.getWidth(), 10);
		jumper = new Jumper(40, midPointY + 34 - 30, 22, 30, midPointY, this, scroller);
		this.midPointY = midPointY;
	}
	
	public void updateReady(float delta) {
		if (Gdx.input.isTouched()) {
			this.start();
		}
	}
	
	public void updateRunning(float delta) {
		
		jumper.update(delta);
		scroller.update(delta);

		jumper.onClick();

				if (scroller.collides(jumper) && jumper.isAlive()) {
						
					scroller.stop();
					jumper.die();
					// AssetLoader.dead.play();
					gameState = GameState.GAMEOVER;
					
		            if (score > AssetLoader.getHighScore()) {
		                AssetLoader.setHighScore(score);
		                gameState = GameState.HIGHSCORE;
		            }

					
        }
		
	}
	
	public void update(float delta) {
		switch (gameState) {
			
			case READY:
				updateReady(delta);
				break;
				
			case RUNNING:
				updateRunning(delta);
				break;
			
			case GAMEOVER: case HIGHSCORE:
	            if (Gdx.input.isTouched()) {
	            	this.restart();
	            }
			
			default:
				break;

		}
	}
	
	public Jumper getJumper() {
		return jumper;
	}
	
	public ScrollHandler getScrollHandler() {
		return scroller;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int increment) {
		score += increment;
	}
	
	public void addScore(int increment) {
	    score += increment;
	}
	
	public Rectangle getFloor(){
		return floor;
	}
	
	public boolean isReady(){
		return (gameState == GameState.READY);
	}
	
	public void start(){
		gameState = GameState.RUNNING;
	}
	
	public void restart(){
		gameState = GameState.READY;
		score = 0;
		jumper.onRestart(midPointY);
		scroller.onRestart();
		gameState = GameState.RUNNING;
	}
	
	public boolean isGameOver(){
		return (gameState == GameState.GAMEOVER);
	}
	
	public boolean isHighScore() {
	    return gameState == GameState.HIGHSCORE;
	}

}
