package com.StarDust;

import java.util.ArrayList;
import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityFactory;
import com.StarDust.entity.components.CameraFollow;
import com.StarDust.entity.helper.CollisionPair;
import com.StarDust.system.CollisionDetection;
import com.StarDust.system.Input;
import com.StarDust.system.Movement;
import com.StarDust.system.Render;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.StarDust.system.*;

public class MyGdxGame implements ApplicationListener
{
	private static boolean running = true;
	private static Skin uiSkin;
	//StageManager stageManager;
	private static Input inputSystem;
	private static Movement movementSystem;
	private static Render renderSystem;
	private static CollisionDetection collisionDetection;
	private static CollisionResolution collisionResolution;
	
	public static List<Entity> allEntities;

	public void create()
	{
		uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		allEntities = new ArrayList<Entity>();
		collisionDetection = new CollisionDetection();
		collisionResolution = new CollisionResolution();
		movementSystem = new Movement();
		renderSystem = new Render();
		inputSystem = new Input();
		GestureDetector gd = new GestureDetector(inputSystem);
		Gdx.input.setInputProcessor(gd);
		
		//display fps
		EntityFactory.displayFPS();
		
		//testing functionality
		Entity player = EntityFactory.createHarvester();
		player.addComponent(new CameraFollow());
		//Entity asteroid = EntityFactory.createAsteroid(400, 400, 128);
		//asteroid.addComponent(new CameraFollow());
		EntityFactory.createScreenBorders();
		
	}

	public void render()
	{
		if (running) {
			//long lastTime = System.currentTimeMillis();
			long currentTime;
		    //StageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
		    //StageManager.getCurrentStage().draw();
		    double deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.00/60.00);
		    //TODO: apply input
			collisionDetection.beforeProcess();
		    collisionDetection.process(allEntities, deltaTime);
			collisionDetection.afterProcess();
			
			collisionResolution.beforeProcess();
		    collisionResolution.process(allEntities, deltaTime);
			collisionResolution.afterProcess();
			
			movementSystem.beforeProcess();
		    movementSystem.process(allEntities, deltaTime);
			movementSystem.afterProcess();
			
			renderSystem.beforeProcess();
		    renderSystem.process(allEntities, deltaTime);
			renderSystem.afterProcess();
		}
	}

	public void dispose()
	{
	}

	public void resize(int width, int height)
	{
		renderSystem.resize(width, height);
	}

	public void pause()
	{
		running = false;
	}

	public void resume()
	{
		running = true;
	}
	
	public static Skin getUISkin()
	{
		return uiSkin;
	}
}
