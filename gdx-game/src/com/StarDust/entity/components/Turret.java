package com.StarDust.entity.components;
import com.StarDust.entity.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class Turret extends Entity
{
	Entity owner;
	public Turret()
	{
		//super(EntityType.TURRET, createTurretTexture());
	}

	@Override
	public void act(float delta)
	{
		if (owner.getTargets().size() > 0)
		{
		    Entity target = owner.getTargets().get(0);
		    //physicsComponent.rotateTo(this.localToStageCoordinates(new Vector2(this.getX(), this.getY())), target.localToStageCoordinates(new Vector2(target.getX(), target.getY())));
		}
		super.act(delta);
	}
	
	public void setOwner(Entity owner)
	{
		this.owner = owner;
	}
	
	private static Texture createTurretTexture()
	{
		Pixmap pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fillRectangle(0,0,16,16);
		pixmap.setColor(Color.WHITE);
		pixmap.drawRectangle(0,0,16,16);
		pixmap.setColor(Color.GRAY);
		pixmap.fillRectangle(0,4,4,8);
		pixmap.setColor(Color.WHITE);
		pixmap.drawRectangle(0,4,4,8);

		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
}
