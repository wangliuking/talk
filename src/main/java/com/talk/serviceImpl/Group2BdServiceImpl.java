package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.Group2BdDao;
import com.talk.dto.Group2Bd;
import com.talk.service.Group2BdService;

@Service("group2bdService")
public class Group2BdServiceImpl implements Group2BdService{
	
	@Resource
	private Group2BdDao group2bdDao;

	@Override
	public List<Group2Bd> getGroup2BdListByPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return group2bdDao.getGroup2BdListByPage(params);
	}

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return group2bdDao.count(params);
	}

	@Override
	public int countId(Map<String, String> params) {
		// TODO Auto-generated method stub
		return group2bdDao.countId(params);
	}

	@Override
	public int save(Map<String, String> params) {
		// TODO Auto-generated method stub
		return group2bdDao.save(params);
	}

	@Override
	public int delete(Map<String, String> params) {
		// TODO Auto-generated method stub
		return group2bdDao.delete(params);
	}

	@Override
	public int update(Map<String, String> params) {
		// TODO Auto-generated method stub
		return group2bdDao.update(params);
	}

	@Override
	public int countBdIdByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return group2bdDao.countBdIdByGroupId(groupId);
	}

	@Override
	public List<Map<String, String>> countGroupIdByBdId(String bdId) {
		// TODO Auto-generated method stub
		return group2bdDao.countGroupIdByBdId(bdId);
	}

}
