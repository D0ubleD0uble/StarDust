package com.StarDust.entity.components;

import com.badlogic.gdx.math.*;

public class LineCollider extends Collider
{
	public Vector2 lineStart;
	public Vector2 lineEnd;
	
	public LineCollider(Vector2 lineStart, Vector2 lineEnd)
	{
		super(Shape.LINE);
		this.lineStart = lineStart;
		this.lineEnd = lineEnd;
	}
}
