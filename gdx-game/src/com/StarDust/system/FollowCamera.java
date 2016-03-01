package com.StarDust.system;
import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;

public class FollowCamera extends OrthographicCamera
{
	int screenWidth;
	int screenHeight;
	
	public FollowCamera()
	{
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}
	
	public void update(List<Entity> entities)
	{
		float totalXPosition = 0f;
		float totalYPosition = 0f;
		int numberOfFollowed = 0;
		for (Entity e : entities)
		{
			if (e.hasComponents(getRequiredComponents()))
			{
				Position positionComponent = e.getComponent(ComponentType.POSITION);
				Image imageComponent = e.getComponent(ComponentType.IMAGE);
				totalXPosition += positionComponent.x;
				totalXPosition += imageComponent.getImage().getWidth()/2;
				totalYPosition += positionComponent.y;
				totalYPosition += imageComponent.getImage().getHeight()/2;
				numberOfFollowed++;
			}
		}
		if (numberOfFollowed > 0)
		{
			this.position.set(totalXPosition/numberOfFollowed, totalYPosition/numberOfFollowed, 0);
		}
		super.update();
	}
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.CAMERAFOLLOW, ComponentType.IMAGE, ComponentType.POSITION };
	}
}
