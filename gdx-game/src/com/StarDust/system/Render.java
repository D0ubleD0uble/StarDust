package com.StarDust.system;

import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Image;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.components.Rotation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render
{
	SpriteBatch spriteBatch;
	
	public Render()
	{
		spriteBatch = new SpriteBatch();
	}
	
	public void render(List<Entity> entities)
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    spriteBatch.begin();
		for (Entity e : entities)
		{
			if (e.hasComponents(getRequiredComponents()))
			{
				render(e);
			}
		}
		spriteBatch.end();
	}
	
	private void render(Entity... entities)
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
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.IMAGE, ComponentType.POSITION};
	}
}
