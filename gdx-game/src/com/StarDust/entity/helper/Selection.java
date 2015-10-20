package com.StarDust.entity.helper;

import com.StarDust.MyGdxGame;
import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Selection extends Entity
{
	Table debugTable;
	
	private enum POSITION {
		BOTTOM_LEFT,
		BOTTOM_RIGHT,
		TOP_LEFT,
		TOP_RIGHT
	}
	
	public Selection(int width, int height, Table table)
	{
		super(EntityType.SELECTION, createSelectionTexture());
		this.setWidth(width);
		this.setHeight(height);
		this.debugTable = table;
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
		int xMod = 0;
		int yMod = 0;
		
		switch(pos)
		{
			case BOTTOM_LEFT:
					xMod = -8;
					yMod = -8;
					drawnRotation = 90f;
					break;
			case BOTTOM_RIGHT:
					xMod = (int)this.getWidth() + 8;
					yMod = -8;
					drawnRotation = 180f;
					break;
			case TOP_LEFT:
					xMod = (int)this.getWidth() + 8;
					yMod = (int)this.getHeight() + 8;
					drawnRotation = 0f;
					break;
			case TOP_RIGHT:
					xMod = -8;
					yMod = (int)this.getHeight() + 8;
					drawnRotation = 270f;
					break;
		}
		
		batch.draw(getTexture(),
				this.getX()+xMod, this.getY()+yMod,
				this.getOriginX(),this.getOriginY(),
				getTexture().getWidth(), getTexture().getHeight(),
				this.getScaleX(), this.getScaleY(),
				drawnRotation,
				0,0, 
				getTexture().getWidth(), getTexture().getHeight(),
				false,false);
		debugTable.row();
		debugTable.add(new Label(drawnRotation+"", MyGdxGame.getUISkin()));
	}
	
	private static Texture createSelectionTexture()
	{
		return new Texture("selection.png");
	}
}
