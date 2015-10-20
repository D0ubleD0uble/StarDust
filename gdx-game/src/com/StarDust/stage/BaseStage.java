package com.StarDust.stage;
import com.StarDust.entity.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;

public abstract class BaseStage extends Stage
{
	//A list of things the Player owns on this Stage
	private ArrayList<Entity> purchasedEntities;
	private boolean firstVisit = true;
	
	public BaseStage()
	{
		super();
		purchasedEntities = new ArrayList<Entity>();
		initializeViewport();
		initialize();
	}
	
	private void initializeViewport()
	{
		//float virtualHeight = 100;
		//float aspectRatio = Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
		//float virtualWidth = virtualHeight*aspectRatio;

		Viewport viewport = new ExtendViewport(320,240, this.getCamera());
		//viewport.getCamera().lookAt(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2,0);
		//viewport.setScreenPosition((int)virtualWidth/2, (int)virtualHeight/2);
		this.setViewport(viewport);
	}
	
	public void updateOnNavigation()
	{
		//Can be overridden to do some updating when first loaded
		if (firstVisit)
		{
			this.firstVisit = false;
		}
	}
	
	public void addPurchase(Entity entity)
	{
		purchasedEntities.add(entity);
	}
	
	public void removePurchase(Entity entity)
	{
		purchasedEntities.remove(entity);
	}
	
	public ArrayList<Entity> getPurchasedEntities()
	{
		return purchasedEntities;
	}
	
	protected boolean isFirstVisit()
	{
		return firstVisit;
	}
	
	abstract protected void initialize();
}
