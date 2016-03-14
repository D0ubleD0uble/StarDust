package com.StarDust.entity.components.collisions;

import com.StarDust.entity.components.*;
import com.badlogic.gdx.math.*;

public class Event
{
	Vector2 collisionPosition;
	Vector2 collisionVelocity;
	double totalDeltaTime;
	double deltaTimeOfCollision;

	Event otherEvent;
	CollisionReaction collisionReaction;
	
	public Event(double deltaTimeOfCollision, double totalDeltaTime)
	{
		this.deltaTimeOfCollision = deltaTimeOfCollision;
		this.totalDeltaTime = totalDeltaTime;
	}
	
	public void setCollisionReaction(CollisionReaction collisionReaction)
	{
		this.collisionReaction = collisionReaction;
	}

	public CollisionReaction getCollisionReaction()
	{
		return collisionReaction;
	}

	public void setCollisionPosition(float x, float y)
	{
		this.collisionPosition = new Vector2(x, y);
	}

	public Vector2 getCollisionPosition()
	{
		return collisionPosition;
	}

	public void setCollisionVelocity(float x, float y)
	{
		this.collisionVelocity = new Vector2(x, y);
	}

	public void setOtherEvent(Event otherEvent)
	{
		this.otherEvent = otherEvent;
	}
}
