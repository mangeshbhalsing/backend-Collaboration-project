package com.niit.collaboration.daoImpl;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.BlogCommentDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO{
	
	
	private static Logger log = LoggerFactory.getLogger("BlogCommentDAOImpl");

	
	private SessionFactory sessionFactory;

	public BlogCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public List<BlogComment> list() 
	{
	
		return sessionFactory.getCurrentSession().createQuery("from BlogComment ").list();
		
	}
	
	
	public boolean saveComment(BlogComment blogComment) {
		log.debug("Starting of the Save Method");
		
		blogComment.setCommentId(getMaxId());
		
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	/*WE do not update blog comments*/
	
	public boolean updateComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().update(blogComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteComment(int blogCommentId) {
		
		try {
			sessionFactory.getCurrentSession().delete(getCommentById(blogCommentId));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public BlogComment getCommentById(int blogCommentId){
		
		Query query = sessionFactory.getCurrentSession().createQuery("from BlogComment where id=?");

		return (BlogComment) query.setInteger(0, blogCommentId).uniqueResult();
		
		
	}

	public List<BlogComment> getComment(String userId) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from BlogComment where userId=?");

		return  query.setString(0, userId).list();
		
	}

	public List<BlogComment> getComments(int blogId) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from BlogComment where blogId=?");

		return  query.setInteger(0, blogId).list();
		
	}
	
	

	private Long getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		Long maxID = 100L;
		try {
			String hql = "select max(commentId) from BlogComment";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("hql" + hql);
			maxID = (Long) query.uniqueResult();
			if(maxID==null){
				maxID = 100L;
			}
		} catch (HibernateException e) {
			log.debug("It seems this is first record. setting initial id is 100 :");
			maxID = 100L;
			e.printStackTrace();
		}
		log.debug("Max id :" + maxID);
		return maxID + 1;

	}


	
	
	
	
}
