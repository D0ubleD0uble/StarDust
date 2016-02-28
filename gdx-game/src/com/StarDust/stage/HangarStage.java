package com.StarDust.stage;
import com.StarDust.entity.*;
import com.StarDust.view.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.StarDust.*;

public class HangarStage extends BaseStage
{
	Table scrollTable;
	
	public HangarStage()
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
		
		table.row();
		scrollTable = new Table();
		scrollTable.setFillParent(true);
		//ScrollPane scrollPane = new ScrollPane(scrollTable, MyGdxGame.getUISkin());
		//table.add(scrollPane);
		scrollTable.top().left();
		
		
		table.row();
		NavigationButton backButton = new NavigationButton("Back", StageType.HEADQUARTERS);
		table.add(backButton);
	}

	@Override
	public void updateOnNavigation()
	{
		scrollTable.clear();
		for (Entity entity : getPurchasedEntities())
		{
			//scrollTable.add(new TextButton(entity.getDisplayName(), MyGdxGame.getUISkin()));
		}
	}
}
