package com.StarDust.entity.components;

import com.StarDust.entity.Entity;
import com.StarDust.stage.mission.BaseMissionStage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class TargetableComponent
{
	private boolean selected = false;
	private boolean aggressed = false;
	
	public TargetableComponent(Entity entity)
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
					aggressed = !aggressed;
					//BaseMissionStage.player.addTarget((Entity)this.getTouchDownTarget());
				}
			}
		};
		
		//entity.addListener(listener);
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public boolean isAggressed()
	{
		return aggressed;
	}
}
