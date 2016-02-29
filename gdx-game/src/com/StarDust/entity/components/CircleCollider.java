package com.StarDust.entity.components;

public class CircleCollider extends Collider
{
	public float radius;
	
	public CircleCollider(float radius)
	{
		super(Shape.CIRCLE);
		this.radius = radius;
	}
}
