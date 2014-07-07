package com.wasabihater.faster.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle extends Scrollable {
	
	private Rectangle obstacle;
	private Random r;
	
	public boolean isScored = false;
	
	public Obstacle(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		obstacle = new Rectangle(x, y, width, height);
	}
	
    @Override
    public void update(float delta) {
    	super.update(delta);
    	obstacle.set(position.x, position.y, width, height);
    }

	@Override
	public void reset(float newX){
		super.reset(newX);
	    isScored = false;
		//height = r.nextInt(90) + 15;
	}
	
	public boolean collides(Jumper jumper) {
		return Intersector.overlaps(jumper.getBoundingRectangle(), obstacle);
	}
	
	public Rectangle getObstacle(){
		return obstacle;
	}
	
	public boolean isScored() {
	    return isScored;
	}

	public void setScored(boolean b) {
	    isScored = b;
	}

	public void onRestart(float x, int scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);		
	}
}
