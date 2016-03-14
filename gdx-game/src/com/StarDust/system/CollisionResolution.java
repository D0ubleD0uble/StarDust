package com.StarDust.system;

import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.components.Collided;
import com.StarDust.entity.components.CollisionReaction;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.components.Velocity;
import com.StarDust.entity.components.collisions.Event;

public class CollisionResolution extends System
{
	public void process(List<Entity> entities, double deltaTime)
	{
		for (Entity e : entities)
		{
			if (e.hasComponents(getRequiredComponents()))
			{
				Collided collided = e.getComponent(ComponentType.COLLIDED);
				for (Event event : collided.getEvents())
				{
				    CollisionReaction reaction = event.getCollisionReaction();
				    if (reaction != null)
				    {
				    	Velocity velocity = e.getComponent(ComponentType.VELOCITY);
					    if (reaction.reactionValue == CollisionReaction.Value.StaticXRebound)
					        velocity.dx *= -1;
					    if (reaction.reactionValue == CollisionReaction.Value.StaticYRebound)
					        velocity.dy *= -1;
				    }
				    else
				    {
				        Velocity velocity = e.getComponent(ComponentType.VELOCITY);
				    //velocity.dx *= -1;
				    //velocity.dy *= -1;
				        Position position = e.getComponent(ComponentType.POSITION);
				    //position.x = collided.getCollisionPosition().x;
				    //position.y = collided.getCollisionPosition().y;
			 	        e.removeComponent(ComponentType.COLLIDED);
				    }
				}
				e.removeComponent(ComponentType.COLLIDED);
			}
		}
	}
	
	public ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.COLLIDED, ComponentType.POSITION, ComponentType.VELOCITY };
	}
}
