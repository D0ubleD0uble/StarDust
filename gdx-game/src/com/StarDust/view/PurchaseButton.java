package com.StarDust.view;
import com.StarDust.MyGdxGame;
import com.StarDust.entity.Entity;
import com.StarDust.entity.EntityType;
import com.StarDust.stage.HeadquartersStage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PurchaseButton extends BaseButton
{
	EntityType lockTarget;
	public PurchaseButton(String buttonText, float cost, EntityType entityType)
	{
		super(cost + " : " + buttonText, MyGdxGame.getUISkin());
		this.lockTarget = entityType;
		this.addListener(new PurchaseListener(cost, entityType));
	}
	
	@Override
	protected Object getLockTarget()
	{
		return lockTarget;
	}
}

class PurchaseListener extends ChangeListener
{
	float cost;
	EntityType entityType;
	
	public PurchaseListener(float cost, EntityType entityType)
	{
		super();
		this.cost = cost;
		this.entityType = entityType;
	}
	@Override
	public void changed(ChangeListener.ChangeEvent p1, Actor p2)
	{
		if (HeadquartersStage.getCash() >= cost)
		{
			HeadquartersStage.setCash(HeadquartersStage.getCash() - cost);
			try{
			    entityType.getPurchaseStorage().addPurchase((Entity)entityType.getEntityClass().newInstance());
			} catch(InstantiationException e)
			{
				e.printStackTrace();
			} catch(IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
}
