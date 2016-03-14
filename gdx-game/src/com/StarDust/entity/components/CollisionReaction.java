package com.StarDust.entity.components;

public class CollisionReaction extends Component
{
	public enum Value { StaticXRebound, StaticYRebound }
	
	public Value reactionValue;
	
	public CollisionReaction(Value reactionValue)
	{
		super(ComponentType.COLLISION_REACTION);
		this.reactionValue = reactionValue;
	}
}
