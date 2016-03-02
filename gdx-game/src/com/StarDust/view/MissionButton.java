package com.StarDust.view;
import com.StarDust.StageManager;
import com.StarDust.entity.Entity;
import com.StarDust.stage.StageType;
import com.StarDust.stage.mission.BaseMissionStage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MissionButton extends NavigationButton
{
	MissionListener missionListener;
	public MissionButton(String buttonText)
	{
		super(buttonText, StageType.SPARSEASTEROID);
		this.missionListener = new MissionListener();
		this.addListener(missionListener);
	}
	
	public void setPilotGroup(ButtonGroup pilotGroup)
	{
		this.missionListener.setPilotGroup(pilotGroup);
	}
	
	public void setShipGroup(ButtonGroup shipGroup)
	{
		this.missionListener.setShipGroup(shipGroup);
	}
	
	public void setMissionStage(StageType stageType)
	{
		this.missionListener.setMissionStage(stageType);
	}
}

class MissionListener extends ChangeListener
{
	StageType missionStage;
	ButtonGroup pilotGroup;
	ButtonGroup shipGroup;
	
	public MissionListener()
	{
		super();
	}
	
	@Override
	public void changed(ChangeListener.ChangeEvent p1, Actor p2)
	{		
		for(Button cb : shipGroup.getAllChecked())
		{
			Entity e = ((CheckButton)cb).getChosenEntity();
			//((BaseMissionStage)StageManager.getStage(missionStage)).setPlayer(e);
		}
	}
	
	public void setMissionStage(StageType missionStage)
	{
		this.missionStage = missionStage;
	}
	
	public void setPilotGroup(ButtonGroup pilotGroup)
	{
		this.pilotGroup = pilotGroup;
	}

	public void setShipGroup(ButtonGroup shipGroup)
	{
		this.shipGroup = shipGroup;
	}
}
