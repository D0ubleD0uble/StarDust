package com.StarDust.mission;
import com.StarDust.entity.*;
import java.util.*;

public class Encounter
{
	ArrayList<Entity> entities;
	
	public Encounter()
	{
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(EntityType entity, int numberOfEntity)
	{
		for(int i = 0; i < numberOfEntity; i++)
		{
			try
			{
				entities.add((Entity)entity.getEntityClass().newInstance());
			}
			catch (InstantiationException e)
			{e.printStackTrace();}
			catch (IllegalAccessException e)
			{e.printStackTrace();}
		}
	}
}
