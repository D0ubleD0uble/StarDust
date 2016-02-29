package com.StarDust.entity.helper;

import com.StarDust.entity.Entity;

public class CollisionPair
{
	public Entity entity1;
	public Entity entity2;
	
	public CollisionPair(Entity entity1, Entity entity2)
	{
		this.entity1 = entity1;
		this.entity2 = entity2;
	}
}
