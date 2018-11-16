package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.SystemDao;
import com.talk.dto.System;
import com.talk.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService{
	
	@Resource
	private SystemDao systemDao;
	
	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return systemDao.count(params);
	}

	@Override
	public List<System> getPageSystem(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return systemDao.getPageSystem(params);
	}

	@Override
	public System getSystem() {
		// TODO Auto-generated method stub
		return systemDao.getSystem();
	}

	@Override
	public int updateSystem(System system) {
		// TODO Auto-generated method stub
		return systemDao.updateSystem(system);
	}

}
