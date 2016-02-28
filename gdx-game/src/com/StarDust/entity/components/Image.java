package com.StarDust.entity.components;

import com.badlogic.gdx.graphics.Texture;

public class Image extends Component
{
	private Texture image;
	public int width, height;
	
	public Image(Texture image)
	{
		super(ComponentType.IMAGE);
		this.image = image;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public Texture getImage()
	{
		return image;
	}
}
