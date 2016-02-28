package com.StarDust.entity.components;

import com.StarDust.entity.Entity;
import com.StarDust.entity.helper.Selection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class RenderComponent
{
	Entity entity;
	private Texture texture;
	
	public RenderComponent(Entity entity, Texture texture)
	{
		this.entity = entity;
		this.texture = texture;
	}
	
	public void draw(Batch batch, float alpha)
	{
		if (entity.isSelected() || entity.isAggressed())
		{
			Selection selection = new Selection(entity.getWidth(), entity.getHeight());
			selection.setX(entity.getX());
			selection.setY(entity.getY());
			selection.setRotation(0f - entity.getRotation());
			if (entity.isAggressed())
			{
				selection.setColor(Color.RED);
			}
			selection.draw(batch, alpha);
		}
		batch.draw(texture,
				entity.getX(), entity.getY(),
				entity.getOriginX(), entity.getOriginY(),
				entity.getWidth(), entity.getHeight(),
				entity.getScaleX(), entity.getScaleY(),
				entity.getRotation(),
				0, 0,
				texture.getWidth(), texture.getHeight(),
				false, false);
	}
	
	public Texture getTexture()
	{
		return texture;
	}
}
