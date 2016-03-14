package com.StarDust.system;

import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.StarDust.entity.helper.*;
import com.badlogic.gdx.math.Vector2;

import java.util.*;
import com.StarDust.entity.components.collisions.*;

public class CollisionDetection extends System
{
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
		
		if ((collider1.getShape() == Collider.Shape.REGION && collider2.getShape() == Collider.Shape.CIRCLE) ||
		    (collider1.getShape() == Collider.Shape.CIRCLE && collider2.getShape() == Collider.Shape.REGION))
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
				
				Collided c1;
				if(e1.hasComponents(ComponentType.COLLIDED))
				{
					c1 = e1.getComponent(ComponentType.COLLIDED);
				}
				else
				{
					c1 = new Collided();
					e1.addComponent(c1);
				}
				Collided c2;
				if(e2.hasComponents(ComponentType.COLLIDED))
				{
					c2 = e2.getComponent(ComponentType.COLLIDED);
				}
				else
				{
					c2 = new Collided();
					e2.addComponent(c2);
				}
				
				c1.addEvent(event1);
				c2.addEvent(event2);
			}
		}
		else if (collider1.getShape() == Collider.Shape.CIRCLE && collider2.getShape() == Collider.Shape.CIRCLE)
		{
				performCollisionCheck(e1,
				                      new Vector2(position1.x, position1.y),
									  new Vector2(velocity1.dx, velocity1.dy),
									  ((CircleCollider)collider1).radius,
									  e2,
									  new Vector2(position2.x, position2.y),
									  new Vector2(velocity2.dx, velocity2.dy),
									  ((CircleCollider)collider2).radius,
									  deltaTime);
		}
		else if ((collider1.getShape() == Collider.Shape.LINE && collider2.getShape() == Collider.Shape.CIRCLE) ||
		         (collider2.getShape() == Collider.Shape.LINE && collider1.getShape() == Collider.Shape.CIRCLE))
		{
			LineCollider lineCollider = null;
			CircleCollider circleCollider = null;
			Position circlePosition = null;
			
			if (collider1.getShape() == Collider.Shape.LINE)
			{
				lineCollider = (LineCollider)collider1;
				circleCollider = (CircleCollider)collider2;
				circlePosition = position2;
			}
			else
			{
				lineCollider = (LineCollider)collider2;
				circleCollider = (CircleCollider)collider1;
				circlePosition = position1;
			}
			Vector2 position = new Vector2(circlePosition.x+circleCollider.radius, circlePosition.y+circleCollider.radius);
			
			if (isLineIntersectedWithCircle(lineCollider.lineStart, lineCollider.lineEnd, position, circleCollider.radius))
			{
				/*Collided c1 = new Collided(0.00, deltaTime);
				Collided c2 = new Collided(0.00, deltaTime);
				
				c1.setOtherCollided(c2);
				c2.setOtherCollided(c1);
				
				e1.addComponent(c1);
				e2.addComponent(c2);*/
			}
		}
	}
	
	/**
	 * Determines whether two circles with given radius are intersected at given positions
	 * @param p1 Position of first Entity
	 * @param r1 Radius of first Entity
	 * @param p2 Position of second Entity
	 * @param r2 Radius of second Entity
	 * @return
	 */
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
	
	public boolean isLineIntersectedWithCircle(Vector2 lineStart, Vector2 lineEnd, Vector2 circlePosition, float circleRadius)
	{
		Vector2 lineVector = lineEnd.sub(lineStart);
		Vector2 lineStartToCircle = lineEnd.sub(circlePosition);
		
		float a = lineVector.dot(lineVector);
		float b = 2*lineStartToCircle.dot(lineVector);
		float c = lineStartToCircle.dot(lineStartToCircle) - circleRadius*circleRadius;
		
		float discriminant = b*b-4*a*c;
		if (discriminant < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void performCollisionCheck(Entity e1, Vector2 p1, Vector2 v1, float r1, Entity e2, Vector2 p2, Vector2 v2, float r2, double delta)
	{
		/*float maxDistanceStep = 1f;
		
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
				*/
				/*Collided c1 = new Collided(timeOfCollision, delta);
				c1.setCollisionPosition(entity1Position.x,entity1Position.y);
				c1.setCollisionVelocity(v1.x, v1.y);
				Collided c2 = new Collided(timeOfCollision, delta);
				c2.setCollisionPosition(entity2Position.x,entity2Position.y);
				c2.setCollisionVelocity(v2.x, v2.y);
				
				c1.setOtherCollided(c2);
				c2.setOtherCollided(c1);
				
				e1.addComponent(c1);
				e2.addComponent(c2);*/
				//System.out.println("1:"+entity1VelocityPerStep + ", 2: "+entity2VelocityPerStep);
				//System.out.println(numberOfSteps);
				/*break;
			}
		}*/
	}
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.POSITION, ComponentType.COLLIDER };
	}
}
