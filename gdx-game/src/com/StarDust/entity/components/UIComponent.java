package com.StarDust.entity.components;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class UIComponent extends Component
{
	Widget libgdxWidget;
	
	public UIComponent(Widget libgdxWidget)
	{
		super(ComponentType.UICOMPONENT);
		this.libgdxWidget = libgdxWidget;
	}
	
	public void update(double deltaTime)
	{
		libgdxWidget.act((float)deltaTime);
	}
	
	public Widget getWidget()
	{
		return libgdxWidget;
	}
}
