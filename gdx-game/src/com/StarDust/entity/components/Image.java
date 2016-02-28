package com.StarDust.entity.components;

import com.badlogic.gdx.graphics.Texture;

public class Image extends Component
{
	private Texture image;
	
	public Image(Texture image)
	{
		super(ComponentType.IMAGE);
		this.image = image;
	}
	
	public Texture getImage()
	{
		return image;
	}
}
