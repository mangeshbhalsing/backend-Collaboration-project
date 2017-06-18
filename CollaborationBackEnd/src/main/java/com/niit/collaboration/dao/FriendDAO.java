package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;



public interface FriendDAO {
	
	public List<Friend> myFriendsList(String userId);
	public List<Friend> pendingFriendRequests(String userId);
	public Friend getFriend(String userId,String friendId);
	public Friend get(String userID, String friendID);
	public Friend getFriendToChangeStatus(String userId, String friendId);
	public boolean saveFriend(Friend friend);
	public boolean updateFriend(Friend friend);
	public void deleteFriend(String userId,String friendId);
	public void removeFriend(String userId, String friendId);
	
	public boolean isRequestAlreadySent(String userId,String friendId);
	public boolean isAlreadyAccepted(String userId,String friendId);
	
	
	
	
	
	/*//select * from Friend where userID =? and status ='A'
		public List<Friend> getMyFriends(String userID);
		
		public Friend get(String userID, String friendID);
		
		
		public Friend get(String friendID);*/
		
		
		//If you want to get all details of you friend
		//You can use get(userID) of UserDAO interface


		/*public boolean save(Friend friend);
		
		public boolean update(Friend friend);

		public void delete(String userID, String friendID);
		
		//select * from Friend where friendID = ? and status ='N'
		public List<Friend> getNewFriendRequests(String userID);*/
		
		public boolean setOnline(String userID);
		
		public boolean setOffLine(String userID);
		
		/*//select * from Friend where userID=? status = 'N'
		public List<Friend> getRequestsSendByMe(String userID);*/
}
