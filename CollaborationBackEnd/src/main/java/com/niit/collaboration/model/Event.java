package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.BLOB;

@Component
@Entity
@Table(name="C_EVENT")
public class Event extends  BaseDomain{
	
	@Id
	private int id;
	
	@Column
	private String name;
	
	private String venue;
	
	@Column(name="description")
	private String Description;
	
	@Column(name="EVENTDATE")
	private String eventDate;

	private BLOB image;

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
	

	

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public BLOB getImage() {
		return image;
	}

	public void setImage(BLOB image) {
		this.image = image;
	}

}
