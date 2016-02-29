package com.StarDust.entity.components;

public class Velocity extends Component
{
	//Change in x position, y position, and rotation
	public float dx, dy, dr;
	
	public Velocity()
	{
		super(ComponentType.VELOCITY);
	}
	
	public Velocity(float dx, float dy, float dr)
	{
		super(ComponentType.VELOCITY);
		this.dx = dx;
		this.dy = dy;
		this.dr = dr;
	}
}
