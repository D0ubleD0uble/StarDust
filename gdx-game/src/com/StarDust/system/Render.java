package com.StarDust.system;

import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import java.util.*;

import com.StarDust.entity.components.Image;
import java.util.List;
import com.badlogic.gdx.math.*;

public class Render
{
	FollowCamera camera;
	SpriteBatch spriteBatch;
	
	public Render()
	{
		spriteBatch = new SpriteBatch();
		camera = new FollowCamera();
	}
	
	public void render(List<Entity> entities)
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(entities);
	    
		spriteBatch.setProjectionMatrix(camera.combined);
	    spriteBatch.begin();
		List<Entity> interfaceEntities = new ArrayList<Entity>();
		for (Entity e : entities)
		{
			if (e.hasComponents(getRequiredComponentsForWorld()))
			{
				renderWorld(e);
			}
			if (e.hasComponents(getRequiredComponentsForUserInterface()))
			{
				interfaceEntities.add(e);
			}
		}
		renderInterface(interfaceEntities);
		spriteBatch.end();
	}
	
	private void renderWorld(Entity... entities)
	{
	    for (Entity e : entities)
	    {
	    	Image image = e.getComponent(ComponentType.IMAGE);
	    	Position position = e.getComponent(ComponentType.POSITION);
	    	Rotation rotationComponent = e.getComponent(ComponentType.ROTATION);
	    	
	    	float rotation = 0f;
	    	if (rotationComponent != null)
	    	{
	    		rotation = rotationComponent.rotation;
	    	}
	    	
	    	//These values are currently defaulted from information that is already known, but may need to allow them to be set in the future.
	    	    //If they do need to be set, would probably have to add the information to {@link Image} Component.
	    	float scaleX = 1;
	    	float scaleY = 1;
	    	int originX = image.width/2;
	    	int originY = image.height/2;
	    	int sourceX = 0;
	    	int sourceY = 0;
	    	int sourceWidth = image.width;
	    	int sourceHeight = image.height;
	    	boolean flipX = false;
	    	boolean flipY = false;
	    	
	    	spriteBatch.draw(image.getImage(),
					position.x, position.y,
					originX, originY,
					image.width, image.height,
					scaleX, scaleY,
					rotation,
					sourceX, sourceY,
					sourceWidth, sourceHeight,
					flipX, flipY);
	    }
	}
	
	private void renderInterface(List<Entity> interfaceEntities)
	{
		for (Entity e : interfaceEntities)
		{
			UIComponent uiComponent = e.getComponent(ComponentType.UICOMPONENT);
			Position position = e.getComponent(ComponentType.POSITION);
			Widget widget = uiComponent.getWidget();
			widget.act(1/60);
			Vector3 screenPosition = camera.unproject(new Vector3(position.x, position.y, 0));
			widget.setPosition(screenPosition.x, screenPosition.y);
			widget.draw(spriteBatch, 1);
		}
	}
	
	public static ComponentType[] getRequiredComponentsForWorld()
	{
		return new ComponentType[] { ComponentType.IMAGE, ComponentType.POSITION};
	}
	
	public static ComponentType[] getRequiredComponentsForUserInterface()
	{
		return new ComponentType[] { ComponentType.UICOMPONENT, ComponentType.POSITION};
	}
}
