package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.BureaudirectionDao;
import com.talk.dto.Bureaudirection;
import com.talk.service.BureaudirectionService;

@Service("bureaudirectionService")
public class BureaudirectionServiceImpl implements BureaudirectionService{
	
	@Resource
	private BureaudirectionDao bureaudirectionDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.count(params);
	}

	@Override
	public List<Bureaudirection> getPageBureaudirection(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.getPageBureaudirection(params);
	}

	@Override
	public Bureaudirection getBureaudirectionById(String bdId) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.getBureaudirectionById(bdId);
	}

	@Override
	public int save(Bureaudirection u) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.save(u);
	}

	@Override
	public int updateBureaudirection(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.updateBureaudirection(params);
	}

	@Override
	public int deleteBureaudirection(String bdId) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.deleteBureaudirection(bdId);
	}

	@Override
	public List<Bureaudirection> getAllBureaudirectionId() {
		// TODO Auto-generated method stub
		return bureaudirectionDao.getAllBureaudirectionId();
	}
	/**
	 * 判断是否有相同局向id
	 * @return true为重复 false为不重复
	 */
	@Override
	public boolean countBureaudirectionId(String bdId) {
		// TODO Auto-generated method stub
		return bureaudirectionDao.countBureaudirectionId(bdId)>0?true:false;
	}

}
