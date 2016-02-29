package com.StarDust.entity.components;

public class Position extends Component
{
	public float x, y;
	
	public Position()
	{
		super(ComponentType.POSITION);
	}
	
	public Position(float x, float y)
	{
		super(ComponentType.POSITION);
		this.x = x;
		this.y = y;
	}
}
