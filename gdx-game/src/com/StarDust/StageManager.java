package com.StarDust;

import java.util.HashMap;

import com.StarDust.stage.BaseStage;
import com.StarDust.stage.StageType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class StageManager
{
	/*
	Primary Controller for switching between different screens or menus
	*/
	private static BaseStage currentStage;
	private static HashMap<StageType, BaseStage> stageMap;
	private static HashMap<Object, Boolean> lockMap;
	
	public StageManager()
	{
		initialize();
	}
	
	private void initialize()
	{
		stageMap = new HashMap<StageType, BaseStage>();
		lockMap = new HashMap<Object, Boolean>();
		for (StageType stageType : StageType.values())
		{
			try{
			    stageMap.put(stageType, (BaseStage)stageType.getStageClass().newInstance());
			} catch(InstantiationException e)
			{
				e.printStackTrace();
			} catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
		setCurrentStage(StageType.MAINMENU);
	}
	
	public static void setCurrentStage(StageType stageType)
	{
		setCurrentStage(stageMap.get(stageType));
	}
	
	public static void setCurrentStage(BaseStage stage)
	{
		currentStage = stage;
		Gdx.input.setInputProcessor(stage);
		stage.updateOnNavigation();
		stage.getViewport().apply();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Camera camera = stage.getCamera();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0);
		//stage.setDebugAll(true);
	}
	
	public BaseStage getCurrentStage()
	{
		return currentStage;
	}
	
	public static BaseStage getStage(StageType stageType)
	{
		return stageMap.get(stageType);
	}
	
	public static void addLock(Object object)
	{
		lockMap.put(object, true);
	}
	
	public static void removeLock(Object object)
	{
		lockMap.put(object, false);
	}
	
	public static boolean isLocked(Object object)
	{
		if (lockMap.get(object) == null)
			return true;
			
		return lockMap.get(object);
	}
}
