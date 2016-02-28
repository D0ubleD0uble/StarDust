package com.StarDust;

import java.util.ArrayList;
import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityFactory;
import com.StarDust.system.Render;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame implements ApplicationListener
{
	private static Skin uiSkin;
	//StageManager stageManager;
	Render renderSystem = new Render();
	public static List<Entity> allEntities;

	public void create()
	{
		uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		allEntities = new ArrayList<Entity>();
		Entity testPlayer = EntityFactory.createHarvester();
		//stageManager = new StageManager();
	}

	public void render()
	{
		StageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
		//StageManager.getCurrentStage().draw();
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
