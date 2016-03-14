package com.StarDust.entity;
import com.StarDust.entity.components.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.StarDust.entity.components.Image;
import com.StarDust.*;
import java.util.*;
import com.badlogic.gdx.math.*;

public class EntityFactory
{
	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	 public static Entity createHarvester()
	{
		Entity entity = new Entity();
		entity.addComponent(new Image(createHarvesterTexture()));
		//entity.addComponent(new Position(300, 300));
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
	
	public static void createScreenBorders()
	{
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		
		Entity leftSide = new Entity();
		leftSide.addComponent(new Position(screenWidth/2, screenHeight/2));
		RegionCollider leftRCollider = new RegionCollider();
		leftRCollider.minX = 100f;
		leftSide.addComponent(leftRCollider);
		leftSide.addComponent(new CollisionReaction(CollisionReaction.Value.StaticXRebound));
		leftSide.addComponent(new CameraFollow());
		
		Entity rightSide = new Entity();
		rightSide.addComponent(new Position(screenWidth/2, screenHeight/2));
		RegionCollider rightRCollider = new RegionCollider();
		rightRCollider.maxX = screenWidth - 100f;
		rightSide.addComponent(rightRCollider);
		rightSide.addComponent(new CollisionReaction(CollisionReaction.Value.StaticXRebound));
		rightSide.addComponent(new CameraFollow());
		
		Entity bottomSide = new Entity();
		bottomSide.addComponent(new Position(screenWidth/2, screenHeight/2));
		RegionCollider bottomRCollider = new RegionCollider();
		bottomRCollider.minY = 100f;
		bottomSide.addComponent(bottomRCollider);
		bottomSide.addComponent(new CollisionReaction(CollisionReaction.Value.StaticYRebound));
		bottomSide.addComponent(new CameraFollow());
		
		Entity topSide = new Entity();
		topSide.addComponent(new Position(screenWidth/2, screenHeight/2));
		RegionCollider topRCollider = new RegionCollider();
		topRCollider.maxY = screenHeight-100f;
		topSide.addComponent(topRCollider);
		topSide.addComponent(new CollisionReaction(CollisionReaction.Value.StaticYRebound));
		topSide.addComponent(new CameraFollow());
	}
	
	public static Entity createAsteroid(float x, float y, int size)
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
		entity.addComponent(new Velocity(1000f, 1000f, 0f));
		entity.addComponent(new CircleCollider(size/2-1));
		return entity;
	}
	
	public static Texture createAsteroidTexture(int size)
	{
		if(textures.containsKey("Asteroid"+size))
		{
			return textures.get("Asteroid"+size);
		}
		else
		{
		    Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
		    pixmap.setColor(Color.WHITE);
		    pixmap.drawCircle(size/2, size/2, (size/2)-1);
			pixmap.drawCircle(size/2, size/2, 3);
		
		    Texture texture = new Texture(pixmap);
		    pixmap.dispose();
			textures.put("Asteroid"+size, texture);
		    return texture;
		}
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
	
	public static void displayCollisionDetectionTime()
	{
		Entity detectionTime = new Entity();
		Label timeLabel = new Label("", MyGdxGame.getUISkin()){
			public void act(float delta)
			{
				//setText(
			}
		};
		
	}
	
	public static void displayCollisionResolutionTime()
	{
		
	}
	
	public static void displayMovementTime()
	{
		
	}
	
	public static void displayRenderTime()
	{
		
	}
}
