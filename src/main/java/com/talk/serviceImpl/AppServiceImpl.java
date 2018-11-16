package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.AppDao;
import com.talk.dto.App;
import com.talk.service.AppService;

@Service("appService")
public class AppServiceImpl implements AppService{
	
	@Resource
	private AppDao appDao;
	
	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return appDao.count(params);
	}

	@Override
	public List<App> getPageApp(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return appDao.getPageApp(params);
	}

	@Override
	public App getApp() {
		// TODO Auto-generated method stub
		return appDao.getApp();
	}

	@Override
	public int addApp(App app) {
		// TODO Auto-generated method stub
		return appDao.addApp(app);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return appDao.deleteById(id);
	}

}
