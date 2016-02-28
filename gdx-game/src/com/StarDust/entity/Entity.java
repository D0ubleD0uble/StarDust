package com.StarDust.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.StarDust.entity.components.Component;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.PhysicsComponent;
import com.StarDust.entity.components.RenderComponent;
import com.StarDust.entity.components.TargetableComponent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Entity extends Group
{
	private EntityType entityType;
	private Map<ComponentType, Component> components;
	/*private RenderComponent renderComponent;
	private TargetableComponent targetableComponent;
	public PhysicsComponent physicsComponent;*/
	
	private ArrayList<Entity> targets;
	
	public Entity()
	{
		components = new HashMap<ComponentType, Component>();
	}
	
	public void addComponent(Component component)
	{
		this.components.put(component.getComponentType(), component);
	}
	
	/*public Entity(EntityType entityType, Texture texture)
	{
		this.entityType = entityType;
		this.renderComponent = new RenderComponent(this, texture);
		this.physicsComponent = new PhysicsComponent(this);
		setOrigin(texture.getWidth()/2, texture.getHeight()/2);
		setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
		this.targetableComponent = new TargetableComponent(this);
		targets = new ArrayList<Entity>();
	}*/
	
	/*public String getDisplayName()
	{
		return entityType.getName();
	}
	
	public EntityType getEntityType()
	{
		return entityType;
	}*/
	
	/*@Override
	public void draw(Batch batch, float alpha)
	{
		renderComponent.draw(batch, alpha);
		super.draw(batch, alpha);
	}*/

	/*@Override
	public void act(float delta)
	{
		physicsComponent.act(delta);
		
		super.act(delta);
	}*/
	
	public void addTarget(Entity entity)
	{
		targets.add(entity);
	}
	
	public ArrayList<Entity> getTargets()
	{
		return targets;
	}
	
	/*public Texture getTexture()
	{
		return renderComponent.getTexture();
	}
	
	public boolean isSelected()
	{
		return targetableComponent.isSelected();
	}
	
	public boolean isAggressed()
	{
		return targetableComponent.isAggressed();
	}*/
}
