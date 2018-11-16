package com.talk.dao;

import java.util.List;
import java.util.Map;

import com.talk.dto.Sms;

/**
 * 专门针对Sms对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface SmsDao {

	/**
	 * 统计行数
	 * @param sms
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 短信记录
	 * @return
	 */
	public List<Sms> getPageSms(Map<String,Object> params);
	/**
	 * 查询全部短信记录
	 * @return
	 */
	public Sms getSms();
	

	/**
	 * 根据id删除短信记录
	 * @param sms
	 * @return
	 */
	public int delete(int id);

}
