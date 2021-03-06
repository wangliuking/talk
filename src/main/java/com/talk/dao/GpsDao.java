package com.talk.dao;

import java.util.List;
import java.util.Map;
import com.talk.dto.Gps;

/**
 * 专门针对Gps对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface GpsDao {

	/**
	 * 统计行数
	 * @param Gps
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 短信记录
	 * @return
	 */
	public List<Gps> getPageGps(Map<String,Object> params);
	/**
	 * 查询全部短信记录
	 * @return
	 */
	public Gps getGps();
	

	/**
	 * 根据id删除短信记录
	 * @param gps
	 * @return
	 */
	public int delete(int id);
	
	/**
	 * 查询最近一分钟所有用户位置
	 */
	public List<Map<String,Object>> getUserPosition();

}
