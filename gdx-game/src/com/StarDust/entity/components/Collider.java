package com.StarDust.entity.components;

public class Collider extends Component
{
	public enum Shape { CIRCLE };
	
	Shape collisionShape;
	
	public Collider(Shape collisionShape)
	{
		super(ComponentType.COLLIDER);
		this.collisionShape = collisionShape;
	}
	
	public Shape getShape()
	{
		return collisionShape;
	}
}
