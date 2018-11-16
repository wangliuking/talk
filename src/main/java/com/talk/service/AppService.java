package com.talk.service;

import java.util.List;
import java.util.Map;
import com.talk.dto.App;

/**
 * 专门针对app对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface AppService {

	/**
	 * 统计行数
	 * @param app
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询
	 * @return
	 */
	public List<App> getPageApp(Map<String,Object> params);
	/**
	 * 查询全部app
	 * @return
	 */
	public App getApp();
	

	/**
	 * app添加
	 * @param app
	 * @return
	 */
	public int addApp(App app);
	
	/**
	 * 根据id删除app
	 */
	public int deleteById(int id);

}
