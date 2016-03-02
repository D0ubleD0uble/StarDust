package com.StarDust.system;

import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.StarDust.entity.helper.*;
import java.util.*;

public class CollisionDetection
{
	public List<CollisionPair> process(List<Entity> entities, double deltaTime)
	{
		List<CollisionPair> collidingEntities = new ArrayList<CollisionPair>();
		for (int i = 0; i < entities.size(); i++)
		{
			for (int j = i+1; j < entities.size(); j++)
			{
				Entity e1 = entities.get(i);
				Entity e2 = entities.get(j);
				if (e1.hasComponents(getRequiredComponents()) &&
						e2.hasComponents(getRequiredComponents()))
				{
					if (isColliding(e1, e2, deltaTime))
					{
						collidingEntities.add(new CollisionPair(e1, e2));
					}
				}
			}
		}
		return collidingEntities;
	}
	
	public boolean isColliding(Entity e1, Entity e2, double deltaTime)
	{
		Position position1 = e1.getComponent(ComponentType.POSITION);
		Position position2 = e2.getComponent(ComponentType.POSITION);
		Collider collider1 = e1.getComponent(ComponentType.COLLIDER);
		Collider collider2 = e2.getComponent(ComponentType.COLLIDER);
		
		Velocity velocity1 = e1.getComponent(ComponentType.VELOCITY);
		Velocity velocity2 = e2.getComponent(ComponentType.VELOCITY);
		
		if (velocity1 != null || velocity2 != null)
		{
		    if (collider1.getShape() == Collider.Shape.CIRCLE && collider2.getShape() == Collider.Shape.CIRCLE)
		    {
			    return isCircleCollision(position1, velocity1, (CircleCollider)collider1, position2, velocity2, (CircleCollider)collider2, deltaTime);
		    }
		}
		
		return false;
	}
	
	public boolean isCircleCollision(Position p1, Velocity v1, CircleCollider c1, Position p2, Velocity v2, CircleCollider c2, double deltaTime)
	{
		float xDifference = (p2.x+c2.radius) - (p1.x+c1.radius);
		float yDifference = (p1.y+c1.radius) - (p2.y+c2.radius);
		float totalRadius = c1.radius + c2.radius;
		if ((xDifference*xDifference) + (yDifference*yDifference) <= (totalRadius*totalRadius))
		{
			return true;
		}
		return false;
	}
	
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.POSITION, ComponentType.COLLIDER };
	}
}
