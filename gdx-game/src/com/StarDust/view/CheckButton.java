package com.StarDust.view;
import com.StarDust.MyGdxGame;
import com.StarDust.entity.Entity;

public class CheckButton extends BaseButton
{
	Entity chosenEntity;
	public CheckButton(String buttonText)
	{
		super(buttonText, MyGdxGame.getUISkin(), "toggle");
	}
	
	public void setChosenEntity(Entity chosenEntity)
	{
		this.chosenEntity = chosenEntity;
	}
	
	public Entity getChosenEntity()
	{
		return chosenEntity;
	}
	
	@Override
	protected Object getLockTarget()
	{
		if(chosenEntity != null)
		{
			return chosenEntity.getEntityType();
		}
		return new Object();
	}
}
