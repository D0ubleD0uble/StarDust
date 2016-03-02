package com.StarDust.entity.components;

import com.badlogic.gdx.math.Vector2;

public class Collided extends Component
{
	Vector2 collisionPosition;
	Vector2 collisionVelocity;
	double totalDeltaTime;
	double deltaTimeOfCollision;
	
	Collided otherCollided;
	
	public Collided(double deltaTimeOfCollision, double totalDeltaTime) {
		super(ComponentType.COLLIDED);
		this.deltaTimeOfCollision = deltaTimeOfCollision;
		this.totalDeltaTime = totalDeltaTime;
	}
	
	public void setCollisionPosition(float x, float y)
	{
		this.collisionPosition = new Vector2(x, y);
	}
	
	public void setCollisionVelocity(float x, float y)
	{
		this.collisionVelocity = new Vector2(x, y);
	}
	
	public void setOtherCollided(Collided otherCollided)
	{
		this.otherCollided = otherCollided;
	}
}
