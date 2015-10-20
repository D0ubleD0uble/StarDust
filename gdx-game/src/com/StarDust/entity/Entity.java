package com.StarDust.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.StarDust.entity.helper.*;

/**
    Any Object which can be displayed
**/
public class Entity extends Actor
{
	private EntityType entityType;
	private Texture texture;
	private Vector2 moveToPosition;
	boolean moveReached = true;
	private Vector2 currentVelocity = new Vector2(0, 0);
	private float acceleration = 50f;
	//private float maxVelocity = 50;
	
	boolean selected = false;
	
	public Entity(EntityType entityType, Texture texture)
	{
		this.entityType = entityType;
		this.texture = texture;
		setOrigin(texture.getWidth()/2, texture.getHeight()/2);
		setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
		setUpInputListener();
	}
	
	public String getDisplayName()
	{
		return entityType.getName();
	}
	
	public EntityType getEntityType()
	{
		return entityType;
	}
	
	@Override
	public void draw(Batch batch, float alpha)
	{
		if (selected)
		{
			Selection selection = new Selection(this.getWidth(), this.getHeight());
			selection.setX(this.getX());
			selection.setY(this.getY());
			selection.draw(batch, alpha);
		}
		batch.draw(texture,this.getX(), this.getY(),this.getOriginX(),this.getOriginY(),this.getWidth(), this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0, texture.getWidth(),texture.getHeight(),false,false);
	}

	@Override
	public void act(float delta)
	{
		if (moveToPosition!=null)
		{
			Vector2 offset = new Vector2(moveToPosition.x - this.getX(), moveToPosition.y - this.getY());
			currentVelocity.add(offset.nor().scl(acceleration*delta));
			moveToPosition = null;
		}
		this.setX(this.getX()+(currentVelocity.x*delta));
		this.setY(this.getY()+(currentVelocity.y*delta));
		super.act(delta);
	}
	
	public void moveTo(Vector2 stageCoordinates)
	{
		this.moveToPosition = stageCoordinates;
		moveReached = false;
		/*MoveToVelocityAccel moveAction = (MoveToVelocityAccel)getCurrentAction(MoveToVelocityAccel.class);
		if (moveAction == null)
			moveAction = new MoveToVelocityAccel();
		this.removeAction(moveAction);
		moveAction.reset();
		
		moveAction.setEndPosition(new Vector2(stageCoordinates));
		moveAction.setAcceleration(this.acceleration);
		moveAction.setMaxVelocity(this.maxVelocity);
		this.addAction(moveAction);*/
		/*MoveToAction moveAction = (MoveToAction)getCurrentAction(MoveToAction.class);
		if (moveAction == null)
			moveAction = new MoveToAction();
		this.removeAction(moveAction);
		moveAction.reset();
		
		moveAction.setPosition(stageCoordinates.x, stageCoordinates.y);
		float distance = stageCoordinates.dst(getX(), getY());
		float duration = distance / this.maxVelocity;
		moveAction.setDuration(duration);
		this.addAction(moveAction);*/
	}
	
	public void rotateTo(float targetRotation)
	{
		/*DirectRotateAction rotateAction = (DirectRotateAction)getCurrentAction(DirectRotateAction.class);
		if (rotateAction == null)
			rotateAction = new DirectRotateAction();
		this.removeAction(rotateAction);
		rotateAction.reset();
		
		rotateAction.setRotation(targetRotation);
		rotateAction.setDuration(2f);
		this.addAction(rotateAction);*/
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	private void setUpInputListener()
	{
		InputListener listener = new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				selected = !selected;
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
			}
		};
		
		this.addListener(listener);
	}
}
