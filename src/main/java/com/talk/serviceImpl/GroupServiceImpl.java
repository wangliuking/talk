package com.talk.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.GroupDao;
import com.talk.dto.Group;
import com.talk.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService{
	
	@Resource
	private GroupDao groupDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return groupDao.count(params);
	}

	@Override
	public List<Group> getPageGroup(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return groupDao.getPageGroup(params);
	}

	@Override
	public Group getGroupById(String groupId) {
		// TODO Auto-generated method stub
		return groupDao.getGroupById(groupId);
	}

	@Override
	public int save(Group u) {
		// TODO Auto-generated method stub
		return groupDao.save(u);
	}

	@Override
	public int updateGroup(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return groupDao.updateGroup(params);
	}

	@Override
	public int deleteGroup(String groupId) {
		// TODO Auto-generated method stub
		return groupDao.deleteGroup(groupId);
	}

	@Override
	public List<Group> getAllGroupId() {
		// TODO Auto-generated method stub
		return groupDao.getAllGroupId();
	}
	/**
	 * 判断是否有相同组id
	 * @return true为重复 false为不重复
	 */
	@Override
	public boolean countGroupId(String groupId) {
		// TODO Auto-generated method stub
		return groupDao.countGroupId(groupId)>0?true:false;
	}

	@Override
	public int insertBatch(ArrayList<Map<String, String>> groupList) {
		// TODO Auto-generated method stub
		return groupDao.insertBatch(groupList);
	}

	@Override
	public List<Map<String,String>> getAllGroupIdByPage(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return groupDao.getAllGroupIdByPage(map);
	}

}
