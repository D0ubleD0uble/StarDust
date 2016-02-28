package com.StarDust.stage;

import com.StarDust.StageManager;
import com.StarDust.entity.Entity;
import com.StarDust.view.CheckButton;
import com.StarDust.view.MissionButton;
import com.StarDust.view.NavigationButton;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DeployStage extends BaseStage
{
	Table missionTable;
	Table entityTable;
	Table pilotTable;
	MissionButton missionButton;
	
	public DeployStage()
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
		
		table.add(new NavigationButton("Back", StageType.HEADQUARTERS));
		
		table.row();
		pilotTable = new Table();
		//pilotTable.center();
		//pilotTable.setFillParent(true);
		table.add(pilotTable);
		
		table.row();
		entityTable = new Table();
		//entityTable.center();
		//entityTable.setFillParent(true);
		table.add(entityTable);
		
		table.row();
		missionTable = new Table();
		//missionTable.center();
		//missionTable.setFillParent(true);
		table.add(missionTable);
		
		table.row();
		this.missionButton = new MissionButton("DEPLOY");
		table.add(missionButton);
	}

	@Override
	public void updateOnNavigation()
	{
		StageManager.removeLock(StageType.SPARSEASTEROID);
		missionTable.clear();
		ButtonGroup missionGroup = new ButtonGroup();
		missionGroup.setMaxCheckCount(1);
		CheckButton missionButton = new CheckButton("Sparse Asteroid Field");
		missionGroup.add(missionButton);
		missionTable.add(missionButton);
		
		/*pilotTable.clear();
		ButtonGroup pilotGroup = new ButtonGroup();
		pilotGroup.setMaxCheckCount(1);
		for(Entity e : StageManager.getStage(StageType.RECRUITMENT).getPurchasedEntities())
		{
			CheckButton button = new CheckButton(e.getDisplayName());
			button.setChosenEntity(e);
			pilotGroup.add(button);
			pilotTable.add(button);
		}*/
		
		entityTable.clear();
		ButtonGroup entityGroup = new ButtonGroup();
		entityGroup.setMaxCheckCount(1);
		for(Entity e : StageManager.getStage(StageType.HANGAR).getPurchasedEntities())
		{
			CheckButton button = new CheckButton(e.getDisplayName());
			button.setChosenEntity(e);
			entityGroup.add(button);
			entityTable.add(button);
		}
		
		this.missionButton.setShipGroup(entityGroup);
		this.missionButton.setMissionStage(StageType.SPARSEASTEROID);
	}
}
