package com.StarDust.stage;
import com.StarDust.view.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class HeadquartersStage extends BaseStage
{
	private static float cash = 0;
	
	public HeadquartersStage()
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
		NavigationButton constructionYard = new NavigationButton("Construction Yard", StageType.CONSTRUCTIONYARD);
		table.add(constructionYard);
		
		table.row();
		NavigationButton shipHangar = new NavigationButton("Ship Hangar", StageType.HANGAR);
		table.add(shipHangar);
		
		table.row();
		table.add(new NavigationButton("Deploy", StageType.DEPLOY));
	}
	
	public static float getCash()
	{
		return cash;
	}
	
	public static void addCash(float cash)
	{
		HeadquartersStage.cash += cash;
	}
	
	public static void setCash(float cash)
	{
		HeadquartersStage.cash = cash;
	}
}
