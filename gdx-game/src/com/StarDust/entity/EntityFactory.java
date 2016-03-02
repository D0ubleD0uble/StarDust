package com.StarDust.entity;
import com.StarDust.entity.components.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.StarDust.entity.components.Image;
import com.StarDust.*;

public class EntityFactory
{
	 public static Entity createHarvester()
	{
		Entity entity = new Entity();
		entity.addComponent(new Image(createHarvesterTexture()));
		entity.addComponent(new Position(300, 300));
		//entity.addComponent(new Rotation());
		//entity.addComponent(new Velocity());
		entity.addComponent(new CircleCollider(30));
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
		entity.addComponent(new Velocity(1f, 1f, 0.2f));
		entity.addComponent(new CircleCollider(size/2-1));
		return entity;
	}
	
	public static Texture createAsteroidTexture(int size)
	{
		Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawCircle(size/2, size/2, (size/2)-1);
		
		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
	
	public static void displayFPS()
	{
		Entity fps = new Entity();
		Label fpsLabel = new Label("", MyGdxGame.getUISkin()){
			public void act(float delta)
			{
				setText("FPS "+Gdx.graphics.getFramesPerSecond());
			}
		};
		UIComponent fpscomponent = new UIComponent(fpsLabel);
		fps.addComponent(fpscomponent);
		fps.addComponent(new Position(0f, 16f));
	}
}
