package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.User2GroupDao;
import com.talk.dto.User2Group;
import com.talk.service.User2GroupService;

@Service("user2groupService")
public class User2GroupServiceImpl implements User2GroupService{
	
	@Resource
	private User2GroupDao user2groupDao;

	@Override
	public List<Map<String,String>> getUser2GroupListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return user2groupDao.getUser2GroupListByPage(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return user2groupDao.count(map);
	}

	@Override
	public int countIdAdd(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return user2groupDao.countIdAdd(params);
	}
	
	@Override
	public int countIdUpdate(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return user2groupDao.countIdUpdate(params);
	}

	@Override
	public int save(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return user2groupDao.save(params);
	}

	@Override
	public int batchSave(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return user2groupDao.batchSave(params);
	}

	@Override
	public int delete(Map<String, String> params) {
		// TODO Auto-generated method stub
		return user2groupDao.delete(params);
	}

	@Override
	public int update(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return user2groupDao.update(params);
	}

	@Override
	public boolean countExistUserId(String userId) {
		// TODO Auto-generated method stub
		return user2groupDao.countExistUserId(userId)>0?true:false;
	}

	@Override
	public boolean countExistGroupId(String groupId) {
		// TODO Auto-generated method stub
		return user2groupDao.countExistGroupId(groupId)>0?true:false;
	}

	@Override
	public List<Map<String,String>> userIdByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return user2groupDao.userIdByGroupId(groupId);
	}

	@Override
	public List<Map<String, String>> selectGroupIdsByUserId(String userId) {
		// TODO Auto-generated method stub
		return user2groupDao.selectGroupIdsByUserId(userId);
	}

	@Override
	public int deleteByUserId(String userId) {
		// TODO Auto-generated method stub
		return user2groupDao.deleteByUserId(userId);
	}

	@Override
	public int deleteByGroupId(String userId) {
		// TODO Auto-generated method stub
		return user2groupDao.deleteByGroupId(userId);
	}

}
