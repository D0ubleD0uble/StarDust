package com.StarDust.stage;
import com.StarDust.*;
import com.StarDust.view.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class MainMenuStage extends BaseStage
{
	public MainMenuStage()
	{
		super();
		StageManager.removeLock(StageType.HEADQUARTERS);
		StageManager.removeLock(StageType.CONSTRUCTIONYARD);
	}
	
	protected void initialize()
	{
		Table table = new Table();
		table.setFillParent(true);
		this.addActor(table);
		//table.setDebug(true);
		//table.center();
		
		NavigationButton newGameButton = new NavigationButton("New Game", StageType.HEADQUARTERS);
		table.add(newGameButton);
		table.row();
		
		//TextButton loadGameButton = new TextButton("Load Game", MyGdxGame.getUISkin());
		//table.add(loadGameButton);
		table.row();
	}
}
