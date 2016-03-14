package com.StarDust.entity.components;

public class RegionCollider extends Collider
{
	public Float minX;
	public Float minY;
	public Float maxX;
	public Float maxY;
	
	public RegionCollider()
	{
		super(Shape.REGION);
	}
}
