package com.talk.service;

import java.util.List;
import java.util.Map;


import com.talk.dto.Group2Bd;

/**
 * 针对局向和组关联进行数据库操作
 * @author 12878
 *
 */
public interface Group2BdService {
	/**
	 * 分页查询所有关联信息
	 */
	public List<Group2Bd> getGroup2BdListByPage(Map<String,Object> params);
	/**
	 * 查询返回条数
	 */
	public int count(Map<String,Object> params);
	/**
	 * 查询是否有重复的组和局向关联
	 */
	public int countId(Map<String,String> params);
	
	/**
	 * 添加新的局向和组关联
	 */
	public int save(Map<String,String> params);
	/**
	 * 删除局向和组的关联
	 */
	public int delete(Map<String,String> params);
	/**
	 * 修改局向和组的关联
	 */
	public int update(Map<String,String> params);
	/**
	 * 查询组是否有局向关联
	 */
	public int countBdIdByGroupId(String groupId);
	/**
	 * 查询此局向关联的所有组
	 */
	public List<Map<String,String>> countGroupIdByBdId(String bdId);
	
}
