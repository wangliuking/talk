package com.talk.service;

import java.util.List;
import java.util.Map;



import com.talk.dto.System;

/**
 * 专门针对System对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface SystemService {

	/**
	 * 统计行数
	 * @param system
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 系统参数
	 * @return
	 */
	public List<System> getPageSystem(Map<String,Object> params);
	/**
	 * 查询全部系统参数
	 * @return
	 */
	public System getSystem();
	

	/**
	 * 系统参数更新
	 * @param system
	 * @return
	 */
	public int updateSystem(System system);

}
