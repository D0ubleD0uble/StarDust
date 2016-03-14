package com.StarDust.entity.components;

import com.StarDust.entity.components.collisions.*;
import com.badlogic.gdx.math.*;
import java.util.*;

public class Collided extends Component
{
	List<Event> collideEvents;
	
	public Collided() {
		super(ComponentType.COLLIDED);
		collideEvents = new ArrayList<Event>();
	}
	
	public void addEvent(Event e)
	{
		collideEvents.add(e);
	}
	
	public List<Event> getEvents()
	{
		return collideEvents;
	}
}
