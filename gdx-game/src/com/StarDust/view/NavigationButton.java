package com.StarDust.view;
import com.StarDust.MyGdxGame;
import com.StarDust.StageManager;
import com.StarDust.stage.StageType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class NavigationButton extends BaseButton
{
	StageType lockTarget;
	
	public NavigationButton(String buttonText, StageType navigateTo)
	{
		super(buttonText, MyGdxGame.getUISkin());
		lockTarget = navigateTo;
		this.addListener(new NavigationListener(navigateTo));
	}
	
	@Override
	protected Object getLockTarget()
	{
		return lockTarget;
	}
}

class NavigationListener extends ChangeListener
{
	StageType navigateTo;
	public NavigationListener(StageType navigateTo)
	{
		super();
		this.navigateTo = navigateTo;
	}
	@Override
	public void changed(ChangeListener.ChangeEvent p1, Actor p2)
	{
		StageManager.setCurrentStage(this.navigateTo);
	}
}
