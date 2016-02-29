package com.StarDust.system;

import java.util.ArrayList;
import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.components.CircleCollider;
import com.StarDust.entity.components.Collider;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.helper.CollisionPair;

public class CollisionDetection
{
	public List<CollisionPair> process(List<Entity> entities)
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
					if (isColliding(e1, e2))
					{
						collidingEntities.add(new CollisionPair(e1, e2));
					}
				}
			}
		}
		return collidingEntities;
	}
	
	public boolean isColliding(Entity e1, Entity e2)
	{
		Position position1 = e1.getComponent(ComponentType.POSITION);
		Position position2 = e2.getComponent(ComponentType.POSITION);
		Collider collider1 = e1.getComponent(ComponentType.COLLIDER);
		Collider collider2 = e2.getComponent(ComponentType.COLLIDER);
		
		if (collider1.getShape() == Collider.Shape.CIRCLE && collider2.getShape() == Collider.Shape.CIRCLE)
		{
			return isCircleCollision(position1, (CircleCollider)collider1, position2, (CircleCollider)collider2);
		}
		
		return false;
	}
	
	public boolean isCircleCollision(Position p1, CircleCollider c1, Position p2, CircleCollider c2)
	{
		float totalRadius = c1.radius + c2.radius;
		if ((p2.x - p1.x) + (p1.y - p2.y) <= (totalRadius * totalRadius))
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