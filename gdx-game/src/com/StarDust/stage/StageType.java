package com.StarDust.stage;
import com.StarDust.stage.mission.SparseAsteroidStage;

public enum StageType
{
	CONSTRUCTIONYARD("ConstructionYard", ConstructionYardStage.class),
	DEPLOY("Deploy", DeployStage.class),
	HEADQUARTERS("Headquarters", HeadquartersStage.class),
	HANGAR("Hangar", HangarStage.class),
	MAINMENU("MainMenu", MainMenuStage.class),
	SPARSEASTEROID("SparseAsteroid", SparseAsteroidStage.class);
	
	String stageName;
	Class<?> stageClass;
	
	private StageType(String stageName, Class<?> stageClass)
	{
		this.stageName = stageName;
		this.stageClass = stageClass;
	}
	
	public String getStageName()
	{
		return this.stageName;
	}
	
	public Class<?> getStageClass()
	{
		return this.stageClass;
	}
}
