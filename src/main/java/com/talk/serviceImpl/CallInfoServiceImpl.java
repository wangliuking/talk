package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.CallInfoDao;
import com.talk.dto.CallInfo;
import com.talk.service.CallInfoService;

@Service("callInfoService")
public class CallInfoServiceImpl implements CallInfoService{
	
	@Resource
	private CallInfoDao callInfoDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return callInfoDao.count(params);
	}

	@Override
	public List<CallInfo> getPageCallInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return callInfoDao.getPageCallInfo(params);
	}

	@Override
	public CallInfo getCallInfo() {
		// TODO Auto-generated method stub
		return callInfoDao.getCallInfo();
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return callInfoDao.delete(id);
	}

}
