package com.StarDust;

import java.util.ArrayList;
import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityFactory;
import com.StarDust.entity.helper.CollisionPair;
import com.StarDust.system.CollisionDetection;
import com.StarDust.system.Input;
import com.StarDust.system.Movement;
import com.StarDust.system.Render;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame implements ApplicationListener
{
	private static Skin uiSkin;
	//StageManager stageManager;
	private static Input inputSystem;
	private static Movement movementSystem;
	private static Render renderSystem;
	private static CollisionDetection collisionDetection;
	
	public static List<Entity> allEntities;

	public void create()
	{
		uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		allEntities = new ArrayList<Entity>();
		renderSystem = new Render();
		movementSystem = new Movement();
		collisionDetection = new CollisionDetection();
		inputSystem = new Input();
		GestureDetector gd = new GestureDetector(inputSystem);
		Gdx.input.setInputProcessor(gd);
		
		//testing functionality
		Entity player = EntityFactory.createHarvester();
		Entity asteroid = EntityFactory.createAsteroid(100, 100, 256);
	}

	public void render()
	{
		//StageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
		//StageManager.getCurrentStage().draw();
		
		//TODO: apply input
		//TODO: detect collisions
		List<CollisionPair> collisions = collisionDetection.process(allEntities);
		for(CollisionPair collisionPair : collisions)
		{
			System.out.println(collisionPair.entity1 + " with " + collisionPair.entity2);
		}
		//TODO: resolve collisions
		movementSystem.process(allEntities);
		renderSystem.render(allEntities);
	}

	public void dispose()
	{
	}

	public void resize(int width, int height)
	{
		/*StageManager.getCurrentStage().getViewport().update(width, height);
		Camera camera = StageManager.getCurrentStage().getCamera();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);*/
	}

	public void pause()
	{
	}

	public void resume()
	{
	}
	
	public static Skin getUISkin()
	{
		return uiSkin;
	}
}
