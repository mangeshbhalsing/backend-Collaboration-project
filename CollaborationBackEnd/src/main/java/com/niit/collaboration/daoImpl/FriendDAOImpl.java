package com.niit.collaboration.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	private static Logger log = LoggerFactory.getLogger("FriendDAOImpl");

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Friend friend;

	@Autowired
	FriendDAO friendDAO;

	public FriendDAOImpl() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Friend> myFriendsList(String userId) {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

		String hql1 = " from Friend where userId='" + userId + "' and status = 'A' ";

		/* + " union  " + */

		String hql2 = " from Friend where friendId='" + userId + "' and status = 'A'";

		log.debug("getMyFriends hql1 : " + hql1);
		log.debug("getMyFriends hql2 : " + hql2);

		List<Friend> list1 = sessionFactory.getCurrentSession().createQuery(hql1).list();
		List<Friend> list2 = sessionFactory.getCurrentSession().createQuery(hql2).list();

		list1.addAll(list2);

		return list1;

	}

	@Transactional
	public List<Friend> pendingFriendRequests(String friendId) {

		Session session;
		/*those SEND ME THE REQUEST THEN FREIEND ID CONSIST OF MY NAME*/
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String hql = "from Friend where friendId =" + "'" + friendId + "' and status = '" + "N'";
		Query query = session.createQuery(hql);

		return query.list();

	}

	// Tested
	@Transactional
	public Friend getFriend(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId =" + "'" + userId + "' and friendId = '" + friendId + "' and status = '"
				+ "A'";
		Query query = session.createQuery(hql);

		return (Friend) query.uniqueResult();

	}

	// Tested
	@Transactional
	public Friend getFriendToChangeStatus(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId =" + "'" + userId + "' and friendId = '" + friendId + "' and status = '"
				+ "N'";
		Query query = session.createQuery(hql);

		return (Friend) query.uniqueResult();

	}

	// Tested
	@Transactional
	public boolean saveFriend(Friend friend) {
		try {

			friend.setId(getMaxId()+1);
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	// Tested
	@Transactional
	public boolean updateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	// Tested
	@Transactional
	public void deleteFriend(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

		session.delete(getFriendToChangeStatus(friendId, userId));

	}

	// Tested
	@Transactional
	public void removeFriend(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

		session.delete(getFriend(userId,friendId ));

	}
	
	
	
	@Transactional
	public Friend get(String userID, String friendID) {
		String hql = "from Friend where userId=" + "'" + userID + "' and friendId= '" + friendID + "'";

		log.debug("hql: " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);

		return (Friend) query.uniqueResult();

	}

	/*
	 * @Transactional public void setOnline(String userId) { Session session;
	 * 
	 * try { session = sessionFactory.getCurrentSession(); } catch
	 * (HibernateException e) { session = sessionFactory.openSession(); } String
	 * hql = "update Friend set isOnline = 'Y' where friendId =" + "'" + userId
	 * + "'"; Query query = session.createQuery(hql); query.executeUpdate();
	 * 
	 * }
	 * 
	 * @Transactional public void setOffline(String userId) { Session session;
	 * 
	 * try { session = sessionFactory.getCurrentSession(); } catch
	 * (HibernateException e) { session = sessionFactory.openSession(); } String
	 * hql = "update Friend set isOnline = 'N' where friendId =" + "'" + userId
	 * + "'"; Query query = session.createQuery(hql); query.executeUpdate();
	 * 
	 * }
	 */

	// Tested
	public boolean isRequestAlreadySent(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId =" + "'" + userId + "' and friendId = '" + friendId + "' and status = '"
				+ "N'";
		Query query = session.createQuery(hql);

		return query.list().isEmpty();
	}

	// Tested
	public boolean isAlreadyAccepted(String userId, String friendId) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String hql = "from Friend where userId =" + "'" + userId + "' and friendId = '" + friendId + "' and status = '"
				+ "A'";
		Query query = session.createQuery(hql);

		return query.list().isEmpty();
	}

	@Transactional
	public boolean setOnline(String friendId) {
		log.debug("Starting of the metnod setOnline");
		// String hql = " UPDATE Friend SET isOnline = 'Y' where friendId='" +
		// friendId + "'";

		try {
			String hql = " UPDATE Friend SET isOnline = 'Y' where friendId= ?";
			log.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setString(0, friendId);
			query.executeUpdate();
			log.debug("Ending of the metnod setOnline");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean setOffLine(String friendId) {
		log.debug("Starting of the metnod setOffLine");
		try {
			String hql = " UPDATE Friend SET isOnline = 'N' where friendId=?";

			log.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setString(0, friendId);
			query.executeUpdate();
			log.debug("Ending of the metnod setOffLine");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private Long getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		Long maxID = 100L;
		try {
			String hql = "select max(id) from Friend ";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("hql" + hql);
			maxID = (Long) query.uniqueResult();
			if (maxID == null) {
				maxID = 100L;
			}
		} catch (HibernateException e) {
			log.debug("It seems this is first record. setting initial id is 100 :");
			maxID = 100L;
			e.printStackTrace();
		}
		log.debug("Max id :" + maxID);
		return maxID;

	}

}
