package com.StarDust.entity.helper;

import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Selection extends Entity
{
	
	private enum POSITION {
		BOTTOM_LEFT,
		BOTTOM_RIGHT,
		TOP_LEFT,
		TOP_RIGHT
	}
	
	public Selection(float width, float height)
	{
		//super(EntityType.SELECTION, createSelectionTexture());
		this.setScale(0.25f);
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public void draw(Batch batch, float alpha)
	{
		//Draw Bottom-Left
		draw(batch, POSITION.BOTTOM_LEFT);
		//Draw Bottom-Right
		draw(batch, POSITION.BOTTOM_RIGHT);
		//Draw Top-Left
		draw(batch, POSITION.TOP_LEFT);
		//Draw Top-Right
		draw(batch, POSITION.TOP_RIGHT);
	}
	
	public void draw(Batch batch, POSITION pos)
	{
		float drawnRotation = 0;
		float xMod = 0;
		float yMod = 0;
		float modAmount = 0;
		
		switch(pos)
		{
			case BOTTOM_LEFT:
					xMod = -modAmount;
					yMod = -modAmount;
					drawnRotation = 90f;
					break;
			case BOTTOM_RIGHT:
					xMod = this.getWidth() + modAmount;
					yMod = -modAmount;
					drawnRotation = 180f;
					break;
			case TOP_LEFT:
					xMod = this.getWidth() + modAmount;
					yMod = this.getHeight() + modAmount;
					drawnRotation = 270f;
					break;
			case TOP_RIGHT:
					xMod = -modAmount;
					yMod = this.getHeight() + modAmount;
					drawnRotation = 0f;
					break;
		}
		
		batch.setColor(this.getColor());
		/*batch.draw(getTexture(),
				this.getX()+xMod-(getTexture().getWidth()/2), this.getY()+yMod-(getTexture().getHeight()/2),
				this.getOriginX(),this.getOriginY(),
				getTexture().getWidth(), getTexture().getHeight(),
				this.getScaleX(), this.getScaleY(),
				drawnRotation,
				0,0, 
				getTexture().getWidth(), getTexture().getHeight(),
				false,false);*/
		batch.setColor(Color.WHITE);
	}
	
	private static Texture createSelectionTexture()
	{
		return new Texture("selection.png");
	}
}
