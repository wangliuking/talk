package com.talk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.talk.dto.Group;

/**
 * 专门针对Group对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface GroupService {

	/**
	 * 统计行数
	 * @param group
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 组数据
	 * @return
	 */
	public List<Group> getPageGroup(Map<String,Object> params);

	/**
	 * 通过组编号查询
	 * @param id
	 * @return group
	 */
	public Group getGroupById(String groupId);

	/**
	 * 新增组
	 * @param u
	 * @return
	 */
	public int save(Group u);
	
	/**
	 * 组数据更新
	 * @param group
	 * @return
	 */
	public int updateGroup(Map<String,Object> params);
	
	/**
	 * 删除组
	 * @param id
	 * @return
	 */
	public int deleteGroup(String groupId);
	
	/**
	 * 查询所有组ID数据
	 */
	public List<Group> getAllGroupId();
	
	/**
	 * 分页查询所有组ID数据
	 */
	public List<Map<String,String>> getAllGroupIdByPage(Map<String,Object> map);
	
	/**
	 * 新增组之前，根据组id查询是否有相同组id
	 * @return ture 有重复 false 无重复
	 */
	public boolean countGroupId(String groupId);
	/**
	 * 超级管理员批量开组
	 */
	public int insertBatch(ArrayList<Map<String, String>> groupList);
}
