package com.StarDust.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.StarDust.MyGdxGame;
import com.StarDust.entity.components.Component;
import com.StarDust.entity.components.ComponentType;

public class Entity
{
	private Map<ComponentType, Component> components;
	
	private ArrayList<Entity> targets;
	
	public Entity()
	{
		components = new HashMap<ComponentType, Component>();
		MyGdxGame.allEntities.add(this);
	}
	
	public void addComponent(Component component)
	{
		this.components.put(component.getComponentType(), component);
	}
	
	public void removeComponent(ComponentType componentType)
	{
		components.remove(componentType);
	}
	
	public <T extends Component>T getComponent(ComponentType componentType)
	{
		return (T)components.get(componentType);
	}
	
	public boolean hasComponents(ComponentType... componentTypes)
	{
		for (ComponentType componentType : componentTypes)
		{
			if (getComponent(componentType) == null)
			{
				return false;
			}
		}
		return true;
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
}
