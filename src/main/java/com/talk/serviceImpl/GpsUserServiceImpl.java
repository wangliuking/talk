package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.GpsUserDao;
import com.talk.dto.GpsUser;
import com.talk.service.GpsUserService;

@Service("gpsuserService")
public class GpsUserServiceImpl implements GpsUserService{
	
	@Resource
	private GpsUserDao gpsuserDao;
	
	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return gpsuserDao.count(params);
	}

	@Override
	public List<GpsUser> getPageGpsUser(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return gpsuserDao.getPageGpsUser(params);
	}

	@Override
	public GpsUser getGpsUser(String gpsUserId) {
		// TODO Auto-generated method stub
		return gpsuserDao.getGpsUser(gpsUserId);
	}

	@Override
	public int updateGpsUser(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return gpsuserDao.updateGpsUser(map);
	}

	@Override
	public int checkById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsuserDao.checkById(map);
	}

}
