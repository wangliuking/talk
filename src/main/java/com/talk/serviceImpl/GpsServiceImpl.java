package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.GpsDao;
import com.talk.dto.Gps;
import com.talk.service.GpsService;

@Service("gpsService")
public class GpsServiceImpl implements GpsService{
	
	@Resource
	private GpsDao gpsDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return gpsDao.count(params);
	}

	@Override
	public List<Gps> getPageGps(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return gpsDao.getPageGps(params);
	}

	@Override
	public Gps getGps() {
		// TODO Auto-generated method stub
		return gpsDao.getGps();
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return gpsDao.delete(id);
	}

	@Override
	public List<Map<String,Object>> getUserPosition() {
		// TODO Auto-generated method stub
		return gpsDao.getUserPosition();
	}

}
