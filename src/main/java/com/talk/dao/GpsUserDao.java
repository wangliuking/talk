package com.talk.dao;

import java.util.List;
import java.util.Map;




import com.talk.dto.GpsUser;

/**
 * 专门针对gps用户对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface GpsUserDao {

	/**
	 * 统计行数
	 * @param gpsuser
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 
	 * @return
	 */
	public List<GpsUser> getPageGpsUser(Map<String,Object> params);
	/**
	 * 查询全部gps用户
	 * @return
	 */
	public GpsUser getGpsUser(String gpsUserId);
	
	/**
	 * gps用户更新前查询是否有重复的主键
	 */
	public int checkById(Map<String,Object> map);

	/**
	 * gps用户更新
	 * @param gpsuser
	 * @return
	 */
	public int updateGpsUser(Map<String,Object> map);
	
	

}
