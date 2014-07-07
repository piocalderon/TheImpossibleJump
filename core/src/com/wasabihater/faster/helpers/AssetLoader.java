package com.wasabihater.faster.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
    public static Texture texture;
    public static TextureRegion obstacle1t, obstacle2t, obstacle3t, grass, dirt, bg;
    public static TextureRegion jumperLeft, jumperMid, jumperRight, jumperDead, jumperJump;

    public static Animation jumperAnimation;
    
	public static BitmapFont font, shadow;
	public static Preferences prefs;
	

	public static void load() {
        texture = new Texture(Gdx.files.internal("data/sheet.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		font = new BitmapFont(Gdx.files.internal("data/bitmap.fnt"));
		font.setScale(.25f, -.25f);
						
		grass = new TextureRegion(texture, 0, 0, 204, 10);
        grass.flip(false, true);

		dirt = new TextureRegion(texture, 0, 10, 204, 40);
        dirt.flip(false, true);
        
		obstacle1t = new TextureRegion(texture, 0, 50, 40, 10);
        obstacle1t.flip(false, true);

		obstacle2t = new TextureRegion(texture, 0, 50, 30, 10);
        obstacle2t.flip(false, true);
        
		obstacle3t = new TextureRegion(texture, 0, 50, 20, 10);
        obstacle3t.flip(false, true);
        
        jumperLeft = new TextureRegion(texture, 0, 60, 22, 30);
        jumperLeft.flip(false, true);
        
        jumperMid = new TextureRegion(texture, 22, 60, 22, 30);
        jumperMid.flip(false, true);

        jumperRight = new TextureRegion(texture, 44, 60, 22, 30);
        jumperRight.flip(false, true);
        
        jumperDead = new TextureRegion(texture, 66, 60, 22, 30);
        jumperDead.flip(false, true);
        
        jumperJump = new TextureRegion(texture, 88, 60, 22, 30);
        jumperJump.flip(false, true);

        TextureRegion[] birds = { jumperLeft, jumperMid, jumperRight };
        jumperAnimation = new Animation(0.15f, birds);
        jumperAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        bg = new TextureRegion(texture, 0, 90, 204, 102);
        bg.flip(false, true);

        
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("Faster");

		// Provide default high score of 0
		if (!prefs.contains("highScore")) {
		    prefs.putInteger("highScore", 0);
		}
	
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(int val) {
	    prefs.putInteger("highScore", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static int getHighScore() {
	    return prefs.getInteger("highScore");
	}

	public static void dispose() {
        font.dispose();
        texture.dispose();
	}
	
}
