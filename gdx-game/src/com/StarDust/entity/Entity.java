package com.StarDust.entity;

import java.util.ArrayList;

import com.StarDust.entity.helper.Selection;
import com.StarDust.stage.mission.BaseMissionStage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
    Any Object which can be displayed
**/
public class Entity extends Group
{
	private EntityType entityType;
	private Texture texture;
	
	private Vector2 moveToPosition;
	private Vector2 currentVelocity = new Vector2(0, 0);
	private float acceleration = 50f;
	
	private Vector2 rotateToPosition;
	private float desiredRotation;
	private float rotateSpeed = 180f;
	
	private ArrayList<Entity> targets;
	
	boolean selected = false;
	boolean aggressed = false;
	
	public Entity(EntityType entityType, Texture texture)
	{
		this.entityType = entityType;
		this.texture = texture;
		setOrigin(texture.getWidth()/2, texture.getHeight()/2);
		setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
		setUpInputListener();
		targets = new ArrayList<Entity>();
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
		if (selected || aggressed)
		{
			Selection selection = new Selection(this.getWidth(), this.getHeight());
			selection.setX(this.getX());
			selection.setY(this.getY());
			selection.setRotation(0f - this.getRotation());
			if (aggressed)
			{
				selection.setColor(Color.RED);
			}
			selection.draw(batch, alpha);
		}
		batch.draw(texture,this.getX(), this.getY(),this.getOriginX(),this.getOriginY(),this.getWidth(), this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0, texture.getWidth(),texture.getHeight(),false,false);
		super.draw(batch, alpha);
	}

	@Override
	public void act(float delta)
	{
		//May need to split this up later, possibly should move everything, then calculate other things
		if (moveToPosition!=null)
		{
			//Vector2 offset = new Vector2(moveToPosition.x - (this.getX()+this.getOriginX()), moveToPosition.y - (this.getY()+this.getOriginY()));
			//currentVelocity.add(offset.nor().scl(acceleration*delta));
			//moveToPosition = null;
		}
		//this.setX(this.getX()+(currentVelocity.x*delta));
		//this.setY(this.getY()+(currentVelocity.y*delta));
		Vector2 nextPosition = getNextPosition(delta);
		this.setPosition(nextPosition.x, nextPosition.y);
		
		if (moveToPosition!=null && rotateToPosition!=null)
		{
			this.rotateTo(moveToPosition, rotateToPosition);
			rotateToPosition = null;
		}
		this.setRotation(getNextRotation(delta));
		
		super.act(delta);
	}
	
	public void moveTo(Vector2 stageCoordinates)
	{
		this.moveToPosition = stageCoordinates;
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
	
	public void stopMoving()
	{
		//TODO NEEDS TO CHANGE
		this.currentVelocity.x = 0;
		this.currentVelocity.y = 0;
		//moveToPosition = null;
	}
	
	public void rotateTo(Vector2 fromPosition, Vector2 stageCoordinates)
	{
		//this.rotateToPosition = stageCoordinates;
		double angle = Math.atan2(stageCoordinates.y - fromPosition.y, stageCoordinates.x - fromPosition.x);
		angle = angle * (180/Math.PI);
		angle+=180;
		desiredRotation = (float)angle;
	}
	
	public void rotateTo(float targetRotation)
	{
		desiredRotation = targetRotation;
		/*DirectRotateAction rotateAction = null;//(DirectRotateAction)getCurrentAction(DirectRotateAction.class);
		if (rotateAction == null)
			rotateAction = new DirectRotateAction();
		this.removeAction(rotateAction);
		rotateAction.reset();
		
		rotateAction.setRotation(targetRotation);
		rotateAction.setDuration(2f);
		this.addAction(rotateAction);*/
	}
	
	public Vector2 getNextPosition(float delta)
	{
		if(moveToPosition!=null)
		{
			float timeToStop = currentVelocity.len()/acceleration;
			double xDistanceToStop = (currentVelocity.x / 2)*timeToStop;
			double yDistanceToStop = (currentVelocity.y / 2)*timeToStop;
			//double distanceToStop = Math.sqrt(xDistanceToStop*xDistanceToStop+yDistanceToStop*yDistanceToStop);
			
			double xPropulsionVector = moveToPosition.x - xDistanceToStop - (getX()+getOriginX());
			double yPropulsionVector = moveToPosition.y - yDistanceToStop - (getY()+getOriginY());
			double propulsionScale = Math.sqrt(xPropulsionVector*xPropulsionVector+yPropulsionVector*yPropulsionVector);
			Vector2 propulsion = new Vector2((float)(xPropulsionVector/propulsionScale*acceleration*delta), (float)(yPropulsionVector/propulsionScale*acceleration*delta));
			currentVelocity.add(propulsion);
		}
		else
		{
			this.currentVelocity.x = 0;
			this.currentVelocity.y = 0;
			//currentVelocity.add(new Vector2(currentVelocity.x, currentVelocity.y).nor().scl(-acceleration*delta));
		}
		return new Vector2((float)(getX()+currentVelocity.x*delta),
						   (float)(getY()+currentVelocity.y*delta));
		//float timeToStop = currentVelocity.len()/acceleration;
		/*Vector2 oldVelocity = new Vector2(currentVelocity.x, currentVelocity.y);
		Vector2 offset;
		if (moveToPosition!=null && !isOvershooting())
		{
		    offset = new Vector2(moveToPosition.x - (this.getX()+this.getOriginX()), moveToPosition.y - (this.getY()+this.getOriginY()));
		}
		else
		{
			offset = new Vector2(-currentVelocity.x, -currentVelocity.y);
		}
		currentVelocity.add(offset.nor().scl(acceleration*delta));
		return new Vector2((float)(getX()+(currentVelocity.x+oldVelocity.x)*0.5*delta),
						   (float)(getY()+(currentVelocity.y+oldVelocity.y)*0.5*delta));*/
	}
	
	public float getNextRotation(float delta)
	{
		float now = (getRotation()+360)%360;
		float target = (desiredRotation-getTotalRotation(this.getParent())+360)%360;
		if (now - target > 180) target += 360;
		if (target - now > 180) now += 360;
		
		if (rotateSpeed*delta >= Math.abs(now-target)) return target;
		if (now < target) return now+(rotateSpeed*delta);
		if (now > target) return now-(rotateSpeed*delta);
		return now;
	}
	
	public void addTarget(Entity entity)
	{
		targets.add(entity);
	}
	
	public ArrayList<Entity> getTargets()
	{
		return targets;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public float getTotalRotation(Group g)
	{
		if (g != null)
			return getTotalRotation(g.getParent()) + this.getRotation();
		
		return 0.00f;
	}
	
	private void setUpInputListener()
	{
		ActorGestureListener listener = new ActorGestureListener(){
			public void tap(InputEvent event, float x, float y, int count, int button)
			{
				if (count == 1)
				{
					selected = !selected;
				}
				else if (count == 2)
				{
					aggressed = true;
					BaseMissionStage.player.addTarget((Entity)this.getTouchDownTarget());
				}
			}
		};
		
		this.addListener(listener);
	}
}
