package com.StarDust.stage;

import com.StarDust.*;
import com.StarDust.entity.*;
import com.StarDust.view.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class ConstructionYardStage extends BaseStage
{
	
	public ConstructionYardStage()
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
		//PurchaseButton buyHarvester = new PurchaseButton(EntityType.HARVESTER.getName(), 250f, EntityType.HARVESTER);
		//table.add(buyHarvester);
		
		table.row();
		NavigationButton backButton = new NavigationButton("Back", StageType.HEADQUARTERS);
		table.add(backButton);
	}
	
	@Override
	public void updateOnNavigation()
	{
		if(this.isFirstVisit())
		{
		    HeadquartersStage.addCash(250);
		    //StageManager.removeLock(EntityType.HARVESTER);
		}
		super.updateOnNavigation();
	}
}
