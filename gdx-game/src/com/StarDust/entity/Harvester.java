package com.StarDust.entity;
import com.StarDust.*;
import com.StarDust.stage.*;
import com.badlogic.gdx.graphics.*;

public class Harvester extends Entity
{
	public Harvester()
	{
		super(EntityType.HARVESTER, createHarvesterTexture());
		StageManager.removeLock(StageType.DEPLOY);
	}
	
	private static Texture createHarvesterTexture()
	{
		Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawCircle(32, 32, 30);

		Texture texture = new Texture(pixmap);
		pixmap.dispose();
		return texture;
	}
}
