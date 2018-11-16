package com.talk.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.User2GroupDao;
import com.talk.dao.UserDao;
import com.talk.dto.User;
import com.talk.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;
	@Resource
	private User2GroupDao user2groupDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userDao.count(params);
	}

	@Override
	public List<Map<String,String>> getPageUser(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userDao.getPageUser(params);
	}

	@Override
	public User getUserByUserIdAndByPassword(User u) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserIdAndByPassword(u);
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}
	/**
	 * @return true为重复 false为不重复
	 */
	@Override
	public boolean countUserId(String userId) {
		// TODO Auto-generated method stub
		return userDao.countUserId(userId)>0?true:false;
	}

	@Override
	public User getAdmin(User u) {
		// TODO Auto-generated method stub
		return userDao.getAdmin(u);
	}

	@Override
	public int save(User u) {
		// TODO Auto-generated method stub
		return userDao.save(u);
	}

	@Override
	public int updateUser(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userDao.updateUser(params);
	}

	@Override
	public int deleteUser(String userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateLogin(String userId) {
		// TODO Auto-generated method stub
		return userDao.updateLogin(userId);
	}

	@Override
	public int updateLogout(String userId) {
		// TODO Auto-generated method stub
		return userDao.updateLogout(userId);
	}

	@Override
	public List<User> getAllUserId() {
		// TODO Auto-generated method stub
		return userDao.getAllUserId();
	}

	@Override
	public List<User> getForRedis() {
		// TODO Auto-generated method stub
		return userDao.getForRedis();
	}

	@Override
	public int powerUpdate(Map<String, String> params) {
		// TODO Auto-generated method stub
		return userDao.powerUpdate(params);
	}

	@Override
	public int insertBatch(ArrayList<Map<String, String>> userList) {
		// TODO Auto-generated method stub
		return userDao.insertBatch(userList);
	}

	@Override
	public int insertExcel(List<User> list) {
		// TODO Auto-generated method stub
		return userDao.insertExcel(list);
	}

}
