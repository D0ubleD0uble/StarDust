package com.StarDust.stage.mission;
import com.StarDust.entity.Entity;
import com.StarDust.entity.components.Turret;
import com.StarDust.stage.BaseStage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseMissionStage extends BaseStage
{
	public static Entity player;
	Touch moveToTouch;
	Touch rotateToTouch;
	
	float lastZoomDistance;
	
	public BaseMissionStage()
	{
		super();
	}

	@Override
	public void act(float delta)
	{
		if (moveToTouch != null)
		{
		    player.moveTo(moveToTouch.stageCoordinates);
			if (rotateToTouch != null)
			{
				player.rotateTo(moveToTouch.stageCoordinates, rotateToTouch.stageCoordinates);
				rotateToTouch = null;
			}
		}
		super.act(delta);
		this.getCamera().position.set(player.getX()+player.getOriginX(), player.getY()+player.getOriginY(), 0);
	}
	
	public void setPlayer(Entity entity)
	{
		player = entity;
		player.clearListeners();
		player.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				player.stopMoving();
				return true;
			}
		});
		this.addActor(player);
		Turret t = new Turret();
		t.setY((player.getHeight()/2)-(t.getHeight()/2));
		t.setX(0);
		t.setOwner(player);
		player.addActor(t);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		boolean handled = super.touchDown(screenX, screenY, pointer, button);
		if (!handled && pointer == 0)
		{
			moveToTouch = new Touch(this, screenX, screenY, pointer, button);
		}
		
		/*if (!handled && pointer == 0)
		{
			//this.addActor(new Label("Here", MyGdxGame.getUISkin()));
			rotateToTouch = new Touch(screenX, screenY, pointer, button);
		}*/
		
		return handled;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		if (pointer == 0)
		{
			//moveToTouch = new Touch(this, screenX, screenY, pointer, 0);
		}
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (pointer == 0)
		{
			rotateToTouch = new Touch(this, screenX, screenY, pointer, button);
		}
		lastZoomDistance = 0;
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		if (lastZoomDistance == 0)
		{
			lastZoomDistance = initialDistance;
		}
		((OrthographicCamera)getCamera()).zoom = Math.min(10, Math.max(((OrthographicCamera)getCamera()).zoom + (distance - lastZoomDistance)*0.01f, 1f));
		lastZoomDistance = distance;
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{
		if (count == 2)
		{
			
		}
		return false;
	}
	
	
}

class Touch
{
	Vector2 stageCoordinates;
	int pointer, button;
	public Touch(Stage stage, int screenX, int screenY, int pointer, int button)
	{
		stageCoordinates = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
		this.pointer = pointer;
		this.button = button;
	}
}
