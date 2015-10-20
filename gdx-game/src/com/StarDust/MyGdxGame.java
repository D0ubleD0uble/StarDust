package com.StarDust;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

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
		stageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
		stageManager.getCurrentStage().draw();
	}

	public void dispose()
	{
	}

	public void resize(int width, int height)
	{
		stageManager.getCurrentStage().getViewport().update(width, height);
		Viewport viewport = stageManager.getCurrentStage().getViewport();
		viewport.setScreenPosition(viewport.getScreenWidth()/2, viewport.getScreenWidth()/2);
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
