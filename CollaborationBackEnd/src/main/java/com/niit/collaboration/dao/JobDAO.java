package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Job;

public interface JobDAO {
	
	public Job getJobById(String id);
	
	/*public Job getJobByName(String name);*/
	
	public boolean saveOrupdate(Job job);
	
	public boolean deleteById(String id);
	
	/*public boolean deletebyBlog(Job job);
	
	public boolean deleteByName(String name);*/
	
	public List<Job> list();
	
	

}
