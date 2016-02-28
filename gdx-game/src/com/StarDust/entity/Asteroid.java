package com.StarDust.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Asteroid extends Entity
{
	public Asteroid(int size)
	{
		//super(EntityType.ASTEROID, createAsteroidTexture(size));
	}
	
	private static Texture createAsteroidTexture(int size)
	{
		Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawCircle(size/2, size/2, (size/2)-1);
		
		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
}
