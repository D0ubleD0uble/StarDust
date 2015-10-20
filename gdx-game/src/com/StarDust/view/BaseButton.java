package com.StarDust.view;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.StarDust.*;

public abstract class BaseButton extends TextButton
{
	public BaseButton(String buttonText)
	{
		this(buttonText, MyGdxGame.getUISkin());
	}
	
	public BaseButton(String buttonText, Skin skin)
	{
		super(buttonText, skin);
	}
	
	public BaseButton(String buttonText, Skin skin, String config)
	{
		super(buttonText, skin, config);
	}

	@Override
	public void act(float delta)
	{
		if (StageManager.isLocked(this) && StageManager.isLocked(getLockTarget()))
		{
			if(this.isVisible())
			{
				this.setVisible(false);
			}
		}
		else
		{
			if(!this.isVisible())
			{
				this.setVisible(true);
			}
		}
		super.act(delta);
	}
	
	protected abstract Object getLockTarget();
}
