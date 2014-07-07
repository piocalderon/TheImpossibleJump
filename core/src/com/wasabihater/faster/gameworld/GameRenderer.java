package com.wasabihater.faster.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.wasabihater.faster.gameobjects.Jumper;
import com.wasabihater.faster.gameobjects.Obstacle;
import com.wasabihater.faster.gameobjects.ScrollHandler;
import com.wasabihater.faster.helpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	private int gameWidth;
	private int midPointY;
	
	private Jumper jumper;
	private Obstacle obstacle1, obstacle2, obstacle3;
	private Rectangle floor;
	private ScrollHandler scroller;
	
	private TextureRegion obstacle1t, obstacle2t, obstacle3t, grass, dirt, jumperJump, jumperDead, bg;
	private Animation jumperAnimation;
	private Animation obstacleAnimation;
	
	public GameRenderer(GameWorld world, int gameWidth, int midPointY) {
		myWorld = world;
		this.gameWidth = gameWidth;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, 136);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		
		initGameObjects();
		initAssets();
	}
		
	private void initGameObjects() {
		floor = myWorld.getFloor();
		jumper = myWorld.getJumper();
		scroller = myWorld.getScrollHandler();
		obstacle1 = scroller.getObstacle1();
		obstacle2 = scroller.getObstacle2();
		obstacle3 = scroller.getObstacle3();		
	
	}

	private void initAssets() {
		grass = AssetLoader.grass;
		dirt = AssetLoader.dirt;
		obstacle1t = AssetLoader.obstacle1t;
		obstacle2t = AssetLoader.obstacle2t;
		obstacle3t = AssetLoader.obstacle3t;
		bg = AssetLoader.bg;
		jumperJump = AssetLoader.jumperJump;
		jumperDead = AssetLoader.jumperDead;
		jumperAnimation = AssetLoader.jumperAnimation;
	}
	
	public void render(float runTime) {
        
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  
        batcher.begin();
        batcher.enableBlending();

        drawBG();
        drawJumper(runTime);
        drawObstacles();
        drawFloor();
        drawDirt();
        
        if (myWorld.isReady()) {
            // Draw text
            AssetLoader.font
                    .draw(batcher, "Start!", (204 / 2) - (42 - 1), 59);
            
            // Insert here tutorial on what to press.
            
        } else {

            if (myWorld.isGameOver() || myWorld.isHighScore()) {

                if (myWorld.isGameOver()) {
                    AssetLoader.font.draw(batcher, "Game Over", 66, 55);
                    
                } else {
                    AssetLoader.font.draw(batcher, "High Score!", 66, 55);
                }

                AssetLoader.font.draw(batcher, "Try again?", 66, 75);

            }

            // Convert integer into String
            String score = myWorld.getScore() + "";

            AssetLoader.font.draw(batcher, "Current:", 100, 4);

            
            // Draw text
            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), 182, 4);
            
            
            AssetLoader.font.draw(batcher, "Best:", 12, 4);

            String highScore = AssetLoader.getHighScore() + "";

            // Draw text
            AssetLoader.font.draw(batcher, highScore, (136 / 2)
                    - (3 * highScore.length() - 1), 4);
        }
	batcher.end();
	}
	
    private void drawObstacles() {
    	batcher.draw(obstacle1t, obstacle1.getX(), obstacle1.getY(), obstacle1.getWidth(), obstacle1.getHeight());
    	batcher.draw(obstacle2t, obstacle2.getX(), obstacle2.getY(), obstacle2.getWidth(), obstacle2.getHeight());
    	batcher.draw(obstacle3t, obstacle3.getX(), obstacle3.getY(), obstacle3.getWidth(), obstacle3.getHeight());
    }
	
	private void drawFloor() {
		batcher.draw(grass, floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
	}
	
	private void drawJumper(float runTime) {
		if (jumper.onGround() && jumper.isAlive()) {
			batcher.draw(jumperAnimation.getKeyFrame(runTime),
					jumper.getX(), jumper.getY(), jumper.getWidth(), jumper.getHeight());
		} else if (!jumper.isAlive()) {
			batcher.draw(jumperDead, jumper.getX(), jumper.getY(), jumper.getWidth(), jumper.getHeight());		
		} else {
			batcher.draw(jumperJump, jumper.getX(), jumper.getY(), jumper.getWidth(), jumper.getHeight());
		}
	}
	
	private void drawDirt() {
        batcher.draw(dirt,0, floor.getY() + floor.getHeight(), Gdx.graphics.getWidth(), 40);

	}
	
	private void drawBG() {
		batcher.draw(bg, 0, 0,gameWidth, 102);// Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - (floor.getY() + floor.getHeight() + 40));
	}

}

