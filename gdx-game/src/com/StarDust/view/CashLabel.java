package com.StarDust.view;

import com.StarDust.MyGdxGame;
import com.StarDust.stage.HeadquartersStage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class CashLabel extends Label
{
	public CashLabel()
	{
		super(String.valueOf(HeadquartersStage.getCash()), MyGdxGame.getUISkin());
	}

	@Override
	public void act(float delta)
	{
		setText(String.valueOf(HeadquartersStage.getCash()));
		super.act(delta);
	}
}
