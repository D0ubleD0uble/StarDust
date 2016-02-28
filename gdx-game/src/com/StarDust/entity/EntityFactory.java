package com.StarDust.entity;
import com.StarDust.entity.components.ComponentType;
import com.StarDust.entity.components.Image;
import com.StarDust.entity.components.Position;
import com.StarDust.entity.components.Rotation;
import com.StarDust.entity.components.Velocity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class EntityFactory
{
	 public static Entity createHarvester()
	{
		Entity entity = new Entity();
		entity.addComponent(new Image(createHarvesterTexture()));
		entity.addComponent(new Position());
		entity.addComponent(new Rotation());
		entity.addComponent(new Velocity());
		//StageManager.removeLock(StageType.DEPLOY);
		return entity;
	}
	
	private static Texture createHarvesterTexture()
	{
		Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawCircle(32, 32, 30);

		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
	
	public static Entity createAsteroid(int x, int y, int size)
	{
		Entity entity = createAsteroid(size);
		Position positionComponent = entity.getComponent(ComponentType.POSITION);
		positionComponent.x = x;
		positionComponent.y = y;
		return entity;
	}
	
	public static Entity createAsteroid(int size)
	{
		Entity entity = new Entity();
		entity.addComponent(new Image(createAsteroidTexture(size)));
		entity.addComponent(new Position());
		entity.addComponent(new Rotation());
		entity.addComponent(new Velocity());
		return entity;
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
