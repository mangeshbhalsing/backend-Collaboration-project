package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Event;



public interface EventDAO {

	public Event getEventById(String id);
	
	public Event getEventByName(String name);
	
	public boolean save(Event event);
	
	public boolean update(Event event);
	
	public boolean deleteById(String id);
	
	/*public boolean deletebyEvent(Event event);
	
	public boolean deleteByName(String name);*/
	
	public List<Event> list();
	
	
}
