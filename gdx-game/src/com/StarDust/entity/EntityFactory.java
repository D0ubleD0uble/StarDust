package com.StarDust.entity;
import com.badlogic.gdx.graphics.*;
import com.StarDust.entity.components.*;
import com.StarDust.*;
import com.StarDust.stage.*;

public class EntityFactory
{
	static public Entity createHarvester()
	{
		Entity entity = new Entity();
		entity.addComponent(new Image(createHarvesterTexture()));
		entity.addComponent(new Position());
		entity.addComponent(new Velocity());
		StageManager.removeLock(StageType.DEPLOY);
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
}
