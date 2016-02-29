package com.StarDust;

import java.util.ArrayList;
import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityFactory;
import com.StarDust.system.Render;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame implements ApplicationListener, GestureListener
{
	private static Skin uiSkin;
	//StageManager stageManager;
	Render renderSystem;
	public static List<Entity> allEntities;

	public void create()
	{
		uiSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		allEntities = new ArrayList<Entity>();
		renderSystem = new Render();
		
		//testing functionality
		Entity player = EntityFactory.createHarvester();
	}

	public void render()
	{
		//StageManager.getCurrentStage().act(Gdx.graphics.getDeltaTime());
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

	@Override
	public boolean fling(float arg0, float arg1, int arg2) {
		System.out.println("fling");
		return false;
	}

	@Override
	public boolean longPress(float arg0, float arg1) {
		System.out.println("longpress");
		return false;
	}

	@Override
	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		System.out.println("pan");
		return false;
	}

	@Override
	public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("panStop");
		return false;
	}

	@Override
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {
		System.out.println("pinch");
		return false;
	}

	@Override
	public boolean tap(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("tap");
		return false;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("touchdown");
		return false;
	}

	@Override
	public boolean zoom(float arg0, float arg1) {
		System.out.println("zoom");
		return false;
	}
}
