package com.StarDust;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class DirectRotateAction extends TemporalAction
{
	private float start, end;
	
	protected void begin()
	{
		start = (this.getActor().getRotation()+360)%360;
		if (start - end > 180) end += 360;
		if (end - start > 180) start += 360;
	}
	
	protected void update(float percent)
	{
		this.getActor().setRotation((start+(end-start)*percent)%360);
	}
	
	public float getRotation() {
		return end;
	}
	
	public void setRotation(float rotation) {
		this.end = (rotation+360)%360;
	}
}
