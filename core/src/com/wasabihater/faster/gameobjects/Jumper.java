package com.wasabihater.faster.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.wasabihater.faster.gameworld.GameWorld;

public class Jumper {
	
	private GameWorld myWorld;
	private ScrollHandler scroller;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	private int midPointY;
	private int temp;
	
	private Rectangle boundingRectangle;
	
	private boolean isAlive;

	public Jumper(float x, float y, int width, int height, int midPointY, GameWorld world, ScrollHandler scroller) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 400);
		boundingRectangle = new Rectangle();
		isAlive = true;
		myWorld = world;
		this.scroller = scroller;
		this.midPointY = midPointY;
	}

	public void update(float delta){
		
		temp = onClick();
		if (onGround()){
			acceleration.y = 0;
			velocity.y = 0;
			velocity.y = temp;
		} else {
		
			acceleration.y = 600;
			velocity.add(acceleration.cpy().scl(delta));
		
			if (velocity.y > 100){
				velocity.y = 100;
			}
		}
		
		position.add(velocity.cpy().scl(delta));
		
		if (position.y > midPointY + 34 - 30) {
			position.y = midPointY + 34 - 30;
		}
		
		boundingRectangle.set(position.x, position.y, width, height);
		

	}
	
	public boolean isFalling(){
		return velocity.y > 110;
	}
		
	public int onClick() {
		
		boolean fast = false;
		boolean jump = false;
		final int fastAreaMax = Gdx.graphics.getWidth()/2;
		final int jumpAreaMin = fastAreaMax + 1;
				
		for (int i = 0; i < 2; i++) { // 20 is max number of touch points
		   if (Gdx.input.isTouched(i)) {
		      final int iX = Gdx.input.getX(i);
		      fast = (iX < fastAreaMax); // Touch coordinates are in screen space
		      jump = (iX > jumpAreaMin);
		   }
		}
		

		if (this.isAlive == true) {
			if (jump && onGround()) { // and collision with floor
				return -250;
			}
			
			if (fast && !onGround()) {
				scroller.changeSpeed(-150);
				return 0;
			} else {
			scroller.changeSpeed(-70);
			return 0;
			}
		} else {
			return 0;
		}
	}
		
		public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
		
	public Rectangle getBoundingRectangle(){
		return boundingRectangle;
	}
	
	public boolean isAlive() {
		   return isAlive;
		}
	
	public void die() {
	    isAlive = false;
	    velocity.y = 0;
	}

	public void stop() {
		acceleration.y = 0;
	    velocity.y = 0;
	}
	
	public void decelerate() {
	    acceleration.y = 0;
	}
	
	public void onRestart(int y) {
        position.y = midPointY + 34 - 30;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 400;
        isAlive = true;
	}
	
	public boolean onGround() {
		return myWorld.getFloor().y - (this.getY() + this.getHeight()) < 0.5;
	}
}
