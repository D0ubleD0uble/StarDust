package com.StarDust.system;

import com.StarDust.entity.*;
import com.StarDust.entity.components.*;
import com.StarDust.entity.helper.*;
import com.badlogic.gdx.math.Vector2;

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
						Collided collided1 = new Collided(0, deltaTime);
						Collided collided2 = new Collided(0, deltaTime);
						
						//collided1.setCollisionPosition(0, 0);
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
		performCollisionCheck();
		
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
	
	public void performCollisionCheck()
	{
		float timeChange = 10;//seconds
		Vector2 startPosition1 = new Vector2(0, 0);
		Vector2 startVelocity1 = new Vector2(1, 1);
		
		Vector2 startPosition2 = new Vector2(10, 10);
		Vector2 startVelocity2 = new Vector2(-1, -1);
		
		float maxDistanceStep = 1f;
		
		Vector2 totalDistanceVector1 = startVelocity1.scl(timeChange);
		Vector2 totalDistanceVector2 = startVelocity2.scl(timeChange);
		float totalDistance1 = totalDistanceVector1.len();
		System.out.println(totalDistance1 +", expected: 10");
		float totalDistance2 = totalDistanceVector2.len();
		
		float numberOfSteps1 = totalDistance1 / maxDistanceStep;
		System.out.println(numberOfSteps1 +", expected: 10");
		float numberOfSteps2 = totalDistance2 / maxDistanceStep;
		
		float numberOfSteps = Math.max(numberOfSteps1, numberOfSteps2);
		Vector2 entity1VelocityPerStep = new Vector2(totalDistanceVector1.x/numberOfSteps, totalDistanceVector1.y/numberOfSteps);
		Vector2 entity2VelocityPerStep = new Vector2(totalDistanceVector2.x/numberOfSteps, totalDistanceVector2.y/numberOfSteps);
		double timeStep = timeChange/numberOfSteps;
		
		for (int i = 0; i < numberOfSteps; i++)
		{
			if (isCirclesIntersected(new Vector2(startPosition1.x*(entity1VelocityPerStep.x*i), startPosition1.y*(entity1VelocityPerStep.y*i)), 0, 
									 new Vector2(startPosition2.x*(entity2VelocityPerStep.x*i), startPosition2.y*(entity2VelocityPerStep.y*i)), 0))
			{
				double timeOfCollision = numberOfSteps*timeStep;
				double timeAfterCollision = timeChange-timeOfCollision;
				System.out.println(timeOfCollision + ", expected: 5");
			}
		}
		
		
		//expected point of collision = 5,5
		
		/*length = sqrt(v.x * v.x + v.y * v.y);

		// normalize vector
		v.x /= length;
		v.y /= length;

		// increase vector size
		v.x *= 10
		v.y *= 10*/
	}
	
	
	public static ComponentType[] getRequiredComponents()
	{
		return new ComponentType[] { ComponentType.POSITION, ComponentType.COLLIDER };
	}
}
