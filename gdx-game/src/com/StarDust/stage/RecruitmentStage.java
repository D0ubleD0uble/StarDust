package com.StarDust.stage;

import com.StarDust.entity.*;
import com.StarDust.view.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.StarDust.*;

public class RecruitmentStage extends BaseStage
{
	public RecruitmentStage()
	{
		super();
	}

	@Override
	protected void initialize()
	{
		Table table = new Table();
		table.setFillParent(true);
		this.addActor(table);
		table.top().left();

		CashLabel cashLabel = new CashLabel();
		table.add(cashLabel);

		table.row();
		PurchaseButton buyPilot = new PurchaseButton(EntityType.PILOT.getName(), 100f, EntityType.PILOT);
		table.add(buyPilot);

		table.row();
		NavigationButton backButton = new NavigationButton("Back", StageType.HEADQUARTERS);
		table.add(backButton);
	}

	@Override
	public void updateOnNavigation()
	{
		if(this.isFirstVisit())
		{
		    HeadquartersStage.addCash(100);
		    StageManager.removeLock(EntityType.PILOT);
		}
		super.updateOnNavigation();
	}
}
