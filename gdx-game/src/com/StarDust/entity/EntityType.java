package com.StarDust.entity;
import com.StarDust.StageManager;
import com.StarDust.stage.BaseStage;
import com.StarDust.stage.StageType;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.StarDust.entity.components.*;

public enum EntityType
{
	ASTEROID("Asteroid", Asteroid.class, StageType.SPARSEASTEROID),
	//HARVESTER("Harvester", Harvester.class, StageType.HANGAR),
	SELECTION("Selection", Selection.class, StageType.MAINMENU),
	TURRET("Turret", Turret.class, StageType.MAINMENU);
	
	//private final String texturePath;
	String name;
	Class<?> entityClass;
	StageType purchaseStorage;
	
	private EntityType(String name, Class<?> entityClass, StageType purchaseStorage)
	{
		this.name = name;
		this.entityClass = entityClass;
		this.purchaseStorage = purchaseStorage;
		//this.texturePath = texturePath;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Class<?> getEntityClass()
	{
		return this.entityClass;
	}
	
	public BaseStage getPurchaseStorage()
	{
		return StageManager.getStage(purchaseStorage);
	}
	
	/*public String getTexturePath()
	{
		return texturePath;
	}*/
}
