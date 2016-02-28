package com.StarDust.entity.components;

public class Component
{
	private ComponentType componentType;
	
	public Component(ComponentType componentType)
	{
		this.componentType = componentType;
	}
	
	public ComponentType getComponentType()
	{
		return componentType;
	}
}
