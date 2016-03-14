package com.StarDust.system;
import com.StarDust.*;
import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import java.util.*;

import java.util.List;

public abstract class System
{
	private int ID;
	private static int numberOfSystems = 0;
	
	Entity debugMessageEntity;
	String debugMessage;
	
	long currentTime;
	
	public System()
	{
		numberOfSystems++;
		ID = numberOfSystems;
		
		showDebugMessages();
	}
	
	private void showDebugMessages()
	{
		debugMessageEntity = new Entity();
		debugMessage = "";
		Label messageLabel = new Label("", MyGdxGame.getUISkin()){
			public void act(float delta)
			{
				setText(debugMessage);
			}
		};
		UIComponent messageComponent = new UIComponent(messageLabel);
		debugMessageEntity.addComponent(messageComponent);
		debugMessageEntity.addComponent(new Position(0f, 16f*(ID+1)));
	}
	
	public void beforeProcess()
	{
		currentTime = java.lang.System.currentTimeMillis();
	}
	
	abstract public void process(List<Entity> entities, double deltaTime);
	
	public void afterProcess()
	{
		debugMessage = this.getClass().getSimpleName() + ": " + (java.lang.System.currentTimeMillis() - currentTime);
	}
	
	abstract public ComponentType[] getRequiredComponents();
}
