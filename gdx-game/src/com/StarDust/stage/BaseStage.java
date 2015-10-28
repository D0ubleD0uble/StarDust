package com.StarDust.stage;
import com.StarDust.entity.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;

public abstract class BaseStage extends Stage implements GestureDetector.GestureListener
{
	GestureDetector gestureDetector;
	InputMultiplexer inputMultiplexer;
	//A list of things the Player owns on this Stage
	private ArrayList<Entity> purchasedEntities;
	private boolean firstVisit = true;
	
	public BaseStage()
	{
		super();
		gestureDetector = new GestureDetector(this);
		inputMultiplexer = new InputMultiplexer(gestureDetector, this);
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
	
	public InputMultiplexer getInputMultiplexer()
	{
		return inputMultiplexer;
	}
	
	protected boolean isFirstVisit()
	{
		return firstVisit;
	}

	public boolean touchDown(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean tap(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean longPress(float p1, float p2)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean fling(float p1, float p2, int p3)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean pan(float p1, float p2, float p3, float p4)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean panStop(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean zoom(float p1, float p2)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4)
	{
		// TODO: Implement this method
		return false;
	}
	
	abstract protected void initialize();
}
