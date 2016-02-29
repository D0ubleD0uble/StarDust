package com.StarDust.system;

import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.components.Rotation;
import com.StarDust.entity.components.Velocity;

public class Movement
{
	public void process(List<Entity> entities)
	{
		for (Entity e : entities)
		{
			if (e.hasComponents(getRequiredComponents()))
			{
				process(e);
			}
		}
	}
	
	public void process(Entity... entities)
	{
		for (Entity e: entities)
		{
			Position positionComponent = e.getComponent(ComponentType.POSITION);
			Velocity velocityComponent = e.getComponent(ComponentType.VELOCITY);
			Rotation rotationComponent = e.getComponent(ComponentType.ROTATION);
			
			positionComponent.x += velocityComponent.dx;
			positionComponent.y += velocityComponent.dy;
			if (rotationComponent != null)
			{
				rotationComponent.rotation += velocityComponent.dr;
			}
		}
	}
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.POSITION, ComponentType.VELOCITY};
	}
}