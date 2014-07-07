package com.wasabihater.faster.gameobjects;

import com.wasabihater.faster.gameworld.GameWorld;

public class ScrollHandler {
	private GameWorld gameWorld;
	private Obstacle obstacle1, obstacle2, obstacle3;
	
	public static int SCROLL_SPEED = -70;
	public static final int GAP = 60;

	public ScrollHandler(GameWorld gameWorld){
		this.gameWorld = gameWorld;
		
        obstacle1 = new Obstacle(200, 70, 40, 10, SCROLL_SPEED);
        obstacle2 = new Obstacle(obstacle1.getTailX() + GAP, 70, 30, 10, SCROLL_SPEED);
        obstacle3 = new Obstacle(obstacle2.getTailX() + GAP, 70, 20, 10, SCROLL_SPEED);
    }
	
	public void update(float delta) {    
        // Update our objects
		obstacle1.update(delta);
        obstacle2.update(delta);
        obstacle3.update(delta);

        // Check if any of the obstacles are scrolled left,
        // and reset accordingly
        if (obstacle1.isScrolledLeft()) {
            obstacle1.reset(obstacle3.getTailX() + GAP);
        } else if (obstacle2.isScrolledLeft()) {
            obstacle2.reset(obstacle1.getTailX() + GAP);

        } else if (obstacle3.isScrolledLeft()) {
            obstacle3.reset(obstacle2.getTailX() + GAP);
        }
        
    }

    public Obstacle getObstacle1() {
        return obstacle1;
    }

    public Obstacle getObstacle2() {
        return obstacle2;
    }

    public Obstacle getObstacle3() {
        return obstacle3;
    }

    public void stop() {
        obstacle1.stop();
        obstacle2.stop();
        obstacle3.stop();
    }

    public boolean collides(Jumper jumper) {
      
    	if (!obstacle1.isScored()
                && obstacle1.getX() + (obstacle1.getWidth() / 2) < jumper.getX()
                        + jumper.getWidth()) {
            addScore(1);
            obstacle1.setScored(true);
            //AssetLoader.coin.play();
        } else if (!obstacle2.isScored()
                && obstacle2.getX() + (obstacle2.getWidth() / 2) < jumper.getX()
                        + jumper.getWidth()) {
            addScore(1);
            obstacle2.setScored(true);
            //AssetLoader.coin.play();

        } else if (!obstacle3.isScored()
                && obstacle3.getX() + (obstacle3.getWidth() / 2) < jumper.getX()
                        + jumper.getWidth()) {
            addScore(1);
            obstacle3.setScored(true);
            //AssetLoader.coin.play();

        }
        return (obstacle1.collides(jumper) || obstacle2.collides(jumper) || obstacle3
                .collides(jumper));
    }
    
    public void changeSpeed(int speed) {
    	obstacle1.changeSpeed(speed);
    	obstacle2.changeSpeed(speed);
    	obstacle3.changeSpeed(speed);
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

	public void onRestart() {

		obstacle1.onRestart(200, SCROLL_SPEED);
        obstacle2.onRestart(obstacle1.getTailX() + GAP, SCROLL_SPEED);
        obstacle3.onRestart(obstacle2.getTailX() + GAP, SCROLL_SPEED);
		
	}


}