package com.StarDust.system;

import java.util.List;

import com.StarDust.entity.Entity;
import com.StarDust.entity.components.CircleCollider;
import com.StarDust.entity.components.Collided;
import com.StarDust.entity.components.Collider;
import com.StarDust.entity.components.CollisionReaction;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.components.RegionCollider;
import com.StarDust.entity.components.Velocity;
import com.StarDust.entity.components.collisions.Event;
import com.badlogic.gdx.math.Vector2;

public class CollisionDetection extends System
{
	enum ShapeComparison {
		CircleToCircle, CircleToRegion, NONE;
	};
	
	public void process(List<Entity> entities, double deltaTime)
	{
		for (int i = 0; i < entities.size(); i++)
		{
			for (int j = i+1; j < entities.size(); j++)
			{
				Entity e1 = entities.get(i);
				Entity e2 = entities.get(j);
				if (e1.hasComponents(getRequiredComponents()) &&
						e2.hasComponents(getRequiredComponents()))
				{
					isColliding(e1, e2, deltaTime);
				}
			}
		}
	}
	
	public void isColliding(Entity e1, Entity e2, double deltaTime)
	{
		Position position1 = e1.getComponent(ComponentType.POSITION);
		Position position2 = e2.getComponent(ComponentType.POSITION);
		Collider collider1 = e1.getComponent(ComponentType.COLLIDER);
		Collider collider2 = e2.getComponent(ComponentType.COLLIDER);
		
		Velocity velocity1 = e1.getComponent(ComponentType.VELOCITY);
		if (velocity1 == null)
			velocity1 = new Velocity(0, 0, 0);
		Velocity velocity2 = e2.getComponent(ComponentType.VELOCITY);
		if (velocity2 == null)
			velocity2 = new Velocity(0, 0, 0);
		
		switch(determineCollisionShapes(collider1, collider2))
		{
			case CircleToRegion:
				performCircleToRegionCollisionCheck(e1, collider1, position1, e2, collider2, position2, deltaTime);
				break;
			case CircleToCircle:
				performCircleToCircleCollisionCheck(e1, new Vector2(position1.x, position1.y), new Vector2(velocity1.dx, velocity1.dy), ((CircleCollider)collider1).radius,
						  e2, new Vector2(position2.x, position2.y), new Vector2(velocity2.dx, velocity2.dy), ((CircleCollider)collider2).radius, deltaTime);
				break;
			case NONE:
				break;
		}
	}
	
	private ShapeComparison determineCollisionShapes(Collider c1, Collider c2)
	{
		if ((c1.getShape() == Collider.Shape.REGION && c2.getShape() == Collider.Shape.CIRCLE) ||
			(c1.getShape() == Collider.Shape.CIRCLE && c2.getShape() == Collider.Shape.REGION))
		{
			return ShapeComparison.CircleToRegion;
		}
		else if (c1.getShape() == Collider.Shape.CIRCLE && c2.getShape() == Collider.Shape.CIRCLE)
		{
			return ShapeComparison.CircleToCircle;
		}
		return ShapeComparison.NONE;
	}
	
	public void performCircleToRegionCollisionCheck(Entity e1, Collider collider1, Position position1, Entity e2, Collider collider2, Position position2, double deltaTime)
	{
		RegionCollider regionCollider = null;
		CollisionReaction reaction1 = null;
		CollisionReaction reaction2 = null;
		CircleCollider circleCollider = null;
		Position circlePosition = null;
		
		if (collider1.getShape() == Collider.Shape.REGION)
		{
			regionCollider = (RegionCollider)collider1;
			reaction1 = e1.getComponent(ComponentType.COLLISION_REACTION);
			reaction2 = e2.getComponent(ComponentType.COLLISION_REACTION);
			circleCollider = (CircleCollider)collider2;
			circlePosition = position2;
		}
		else
		{
			regionCollider = (RegionCollider)collider2;
			reaction1 = e1.getComponent(ComponentType.COLLISION_REACTION);
			reaction2 = e2.getComponent(ComponentType.COLLISION_REACTION);
			circleCollider = (CircleCollider)collider1;
			circlePosition = position1;
		}
		
		if (isCircleWithinRegion(regionCollider, new Vector2(circlePosition.x, circlePosition.y), circleCollider.radius))
		{
			Event event1 = new Event(0.00, deltaTime);
			event1.setCollisionReaction(reaction2);
			Event event2 = new Event(0.00, deltaTime);
			event2.setCollisionReaction(reaction1);
			event1.setOtherEvent(event2);
			event2.setOtherEvent(event1);
			
			addEventsToEntities(e1, event1, e2, event2);
		}
	}
	
	public void performCircleToCircleCollisionCheck(Entity e1, Vector2 p1, Vector2 v1, float r1, Entity e2, Vector2 p2, Vector2 v2, float r2, double delta)
	{
		float maxDistanceStep = 1f;
		
		Vector2 totalDistanceVector1 = v1.scl((float)delta);
		Vector2 totalDistanceVector2 = v2.scl((float)delta);
		float totalDistance1 = totalDistanceVector1.len();
		float totalDistance2 = totalDistanceVector2.len();
		
		float numberOfSteps1 = totalDistance1 / maxDistanceStep;
		float numberOfSteps2 = totalDistance2 / maxDistanceStep;
		float numberOfSteps = Math.max(numberOfSteps1, numberOfSteps2);
		
		Vector2 entity1VelocityPerStep = new Vector2(totalDistanceVector1.x/numberOfSteps, totalDistanceVector1.y/numberOfSteps);
		Vector2 entity2VelocityPerStep = new Vector2(totalDistanceVector2.x/numberOfSteps, totalDistanceVector2.y/numberOfSteps);
		double timeStep = delta/numberOfSteps;
		
		for (int i = 0; i < numberOfSteps; i++)
		{
			Vector2 entity1Position = new Vector2(p1.x+(entity1VelocityPerStep.x*i), p1.y+(entity1VelocityPerStep.y*i));
			Vector2 entity2Position = new Vector2(p2.x+(entity2VelocityPerStep.x*i), p2.y+(entity2VelocityPerStep.y*i));
			
			if (isCirclesIntersected(entity1Position, r1, entity2Position, r2))
			{
				double timeOfCollision = (i-1)*timeStep;
				
				Event event1 = new Event(timeOfCollision, delta);
				event1.setCollisionPosition(entity1Position.x,entity1Position.y);
				event1.setCollisionVelocity(v1.x, v1.y);
				Event event2 = new Event(timeOfCollision, delta);
				event2.setCollisionPosition(entity2Position.x,entity2Position.y);
				event2.setCollisionVelocity(v2.x, v2.y);
				
				event1.setOtherEvent(event2);
				event2.setOtherEvent(event1);
				
				addEventsToEntities(e1, event1, e2, event2);
			}
		}
	}
	
	public boolean isCirclesIntersected(Vector2 p1, float r1, Vector2 p2, float r2)
	{
		//positions are given as bottom-left of the object. Adds radius to go from center of circle
		float xDifference = (p2.x+r2) - (p1.x+r1);
		float yDifference = (p1.y+r1) - (p2.y+r2);
		float totalRadius = r1+r2;
		if ((xDifference*xDifference) + (yDifference * yDifference) <= totalRadius*totalRadius)
		{
			return true;
		}
		return false;
	}
	
	public boolean isCircleWithinRegion(RegionCollider region, Vector2 circlePosition, float circleRadius)
	{
		if (region.minX != null &&
		    circlePosition.x > region.minX)
		{
			return false;
		}
		
		if (region.minY != null && 
		    circlePosition.y > region.minY)
		{
			return false;
		}
		
		if (region.maxX != null &&
		    circlePosition.x+(circleRadius*2) < region.maxX)
		{
			return false;
		}

		if (region.maxY != null && 
		    circlePosition.y+(circleRadius*2) < region.maxY)
		{
			return false;
		}
		
		return true;
	}
	
	private void addEventsToEntities(Entity entity1, Event event1, Entity entity2, Event event2)
	{
		Collided c1;
		if(entity1.hasComponents(ComponentType.COLLIDED))
		{
			c1 = entity1.getComponent(ComponentType.COLLIDED);
		}
		else
		{
			c1 = new Collided();
			entity1.addComponent(c1);
		}
		Collided c2;
		if(entity2.hasComponents(ComponentType.COLLIDED))
		{
			c2 = entity2.getComponent(ComponentType.COLLIDED);
		}
		else
		{
			c2 = new Collided();
			entity2.addComponent(c2);
		}
		
		c1.addEvent(event1);
		c2.addEvent(event2);
	}
	
	public ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.POSITION, ComponentType.COLLIDER };
	}
}
