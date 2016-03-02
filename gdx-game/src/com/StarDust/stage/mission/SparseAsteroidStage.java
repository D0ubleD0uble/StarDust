package com.StarDust.stage.mission;

public class SparseAsteroidStage extends BaseMissionStage
{
	int numberOfAsteroids;
	
	public SparseAsteroidStage()
	{
		super();
	}
	
	@Override
	protected void initialize()
	{
		
	}

	/*@Override
	public void updateOnNavigation()
	{
		this.getCamera().position.set(-(Gdx.graphics.getWidth()/2), -(Gdx.graphics.getHeight()/2), 0);
		numberOfAsteroids = 100;
		for(int i = 0; i < numberOfAsteroids; i++)
		{
			Asteroid asteroid = new Asteroid(64);
			this.addActor(asteroid);
			asteroid.moveBy(i*50, i*50);
		}

		Table table = new Table();
		this.addActor(table);
		table.setFillParent(true);
		table.top().left();
		//table.add(new Label(this.getCamera().position.x + "," + this.getCamera().position.y, MyGdxGame.getUISkin()));
		table.row();
		//table.add(new Label(this.getViewport().getScreenX() + "," + this.getViewport().getScreenY(), MyGdxGame.getUISkin()));
	}*/
}
