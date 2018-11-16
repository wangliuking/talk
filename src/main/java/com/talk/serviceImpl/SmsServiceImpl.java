package com.talk.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.talk.dao.SmsDao;
import com.talk.dto.Sms;
import com.talk.service.SmsService;

@Service("smsService")
public class SmsServiceImpl implements SmsService{
	
	@Resource
	private SmsDao smsDao;

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return smsDao.count(params);
	}

	@Override
	public List<Sms> getPageSms(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return smsDao.getPageSms(params);
	}

	@Override
	public Sms getSms() {
		// TODO Auto-generated method stub
		return smsDao.getSms();
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return smsDao.delete(id);
	}

}
