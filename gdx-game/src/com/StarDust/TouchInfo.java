package com.StarDust;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TouchInfo
{
	Stage stage;
	public Vector2 position;
	public Vector2 stagePosition;
	public boolean touched;
	
	public TouchInfo()
	{
		//this.stage = stage;
	}
	
	public void setPosition(float x, float y)
	{
		position = new Vector2(x, y);
		//stagePosition = stage.screenToStageCoordinates(position);
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	/*public Vector2 getStagePosition()
	{
		return stagePosition;
	}*/
}
