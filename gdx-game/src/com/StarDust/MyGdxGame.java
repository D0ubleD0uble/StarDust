package com.StarDust;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame implements ApplicationListener
{
	private static Skin uiSkin;
	StageManager stageManager;

	public void create()
	{
		uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stageManager = new StageManager();
	}

	public void render()
	{
	    Gdx.gl.glClearColor(0, 0, 0, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		StageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
		StageManager.getCurrentStage().draw();
	}

	public void dispose()
	{
	}

	public void resize(int width, int height)
	{
		StageManager.getCurrentStage().getViewport().update(width, height);
		Camera camera = StageManager.getCurrentStage().getCamera();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);
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
