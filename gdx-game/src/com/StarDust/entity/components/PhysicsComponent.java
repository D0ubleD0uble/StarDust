package com.StarDust.entity.components;

import com.StarDust.entity.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class PhysicsComponent
{
	Entity entity;
	
	private Vector2 moveToPosition;
	private Vector2 currentVelocity = new Vector2(0, 0);
	private float acceleration = 50f;
	
	private Vector2 rotateToPosition;
	private float desiredRotation;
	private float rotateSpeed = 180f;
	
	public PhysicsComponent(Entity entity)
	{
		this.entity = entity;
	}
	
	public void act(float delta)
	{
		//Vector2 nextPosition = getNextPosition(delta);
		//entity.setPosition(nextPosition.x, nextPosition.y);
		
		if (moveToPosition!=null && rotateToPosition!=null)
		{
			this.rotateTo(moveToPosition, rotateToPosition);
			rotateToPosition = null;
		}
		//entity.setRotation(getNextRotation(delta));
	}
	
	//public Vector2 getNextPosition(float delta)
	/*{
		if(moveToPosition!=null)
		{
			float timeToStop = currentVelocity.len()/acceleration;
			double xDistanceToStop = (currentVelocity.x / 2)*timeToStop;
			double yDistanceToStop = (currentVelocity.y / 2)*timeToStop;
			//double distanceToStop = Math.sqrt(xDistanceToStop*xDistanceToStop+yDistanceToStop*yDistanceToStop);
			
			//double xPropulsionVector = moveToPosition.x - xDistanceToStop - (entity.getX()+entity.getOriginX());
			//double yPropulsionVector = moveToPosition.y - yDistanceToStop - (entity.getY()+entity.getOriginY());
			//double propulsionScale = Math.sqrt(xPropulsionVector*xPropulsionVector+yPropulsionVector*yPropulsionVector);
			//Vector2 propulsion = new Vector2((float)(xPropulsionVector/propulsionScale*acceleration*delta), (float)(yPropulsionVector/propulsionScale*acceleration*delta));
			//currentVelocity.add(propulsion);
		}
		else
		{
			this.currentVelocity.x = 0;
			this.currentVelocity.y = 0;
			//currentVelocity.add(new Vector2(currentVelocity.x, currentVelocity.y).nor().scl(-acceleration*delta));
		}
		//return new Vector2((float)(entity.getX()+currentVelocity.x*delta),
		//				   (float)(entity.getY()+currentVelocity.y*delta));
		//float timeToStop = currentVelocity.len()/acceleration;
		Vector2 oldVelocity = new Vector2(currentVelocity.x, currentVelocity.y);
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
						   (float)(getY()+(currentVelocity.y+oldVelocity.y)*0.5*delta));
	}*/
	
	//public float getNextRotation(float delta)
	//{
		//float now = (entity.getRotation()+360)%360;
		//float target = (desiredRotation-getTotalRotation(entity.getParent())+360)%360;
		//if (now - target > 180) target += 360;
		//if (target - now > 180) now += 360;
		
		//if (rotateSpeed*delta >= Math.abs(now-target)) return target;
		//if (now < target) return now+(rotateSpeed*delta);
		//if (now > target) return now-(rotateSpeed*delta);
		//return now;
	//}
	
	public float getTotalRotation(Group g)
	{
		//if (g != null)
			//return getTotalRotation(g.getParent()) + entity.getRotation();
		
		return 0.00f;
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
}
