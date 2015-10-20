package com.StarDust.mission;
import java.util.ArrayList;

import com.StarDust.entity.Entity;
import com.StarDust.entity.Pilot;

public class BaseMission
{
	float cashReward;//avg per minute?
	float mineralReward;//avg per minute?
	
	ArrayList<Pilot> storedPilots;
	ArrayList<Entity> storedEntities;
	
	public BaseMission(float cashReward, float mineralReward)
	{
		this.cashReward = cashReward;
		this.mineralReward = mineralReward;
		this.storedPilots = new ArrayList<Pilot>();
		this.storedEntities = new ArrayList<Entity>();
	}
	
	public void start()
	{
		
	}
	
	public void add(Pilot... pilots)
	{
		for (Pilot p : pilots)
		{
			this.storedPilots.add(p);
		}
	}
	
	public void add(Entity... entities)
	{
		for(Entity e : entities)
		{
			this.storedEntities.add(e);
		}
	}
}
