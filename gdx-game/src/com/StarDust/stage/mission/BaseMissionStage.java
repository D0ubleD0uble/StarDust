package com.StarDust.stage.mission;
import com.StarDust.entity.Entity;
import com.StarDust.stage.BaseStage;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseMissionStage extends BaseStage
{
	Entity player;
	Touch moveToTouch;
	
	public BaseMissionStage()
	{
		super();
	}

	@Override
	public void act(float delta)
	{
		if (moveToTouch != null)
		{
		    Vector2 stageCoordinates = screenToStageCoordinates(new Vector2(moveToTouch.screenX, moveToTouch.screenY));
		    player.moveTo(stageCoordinates);
		}
		super.act(delta);
		this.getViewport().setScreenX((int)(player.getX()+player.getOriginX()));
		this.getViewport().setScreenY((int)(player.getY()+player.getOriginY()));
	}
	
	public void setPlayer(Entity entity)
	{
		this.player = entity;
		this.addActor(entity);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		boolean handled = super.touchDown(screenX, screenY, pointer, button);
		if (!handled && pointer == 0)
		{
			moveToTouch = new Touch(screenX, screenY, pointer, button);
		}
		return handled;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		if (moveToTouch != null && pointer == 0)
		{
			moveToTouch = new Touch(screenX, screenY, pointer, 0);
		}
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (pointer == 0)
		{
			moveToTouch = null;
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
}

class Touch
{
	int screenX, screenY, pointer, button;
	public Touch(int screenX, int screenY, int pointer, int button)
	{
		this.screenX = screenX;
		this.screenY = screenY;
		this.pointer = pointer;
		this.button = button;
	}
}
