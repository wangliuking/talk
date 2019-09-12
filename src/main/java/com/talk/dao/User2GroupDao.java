package com.talk.dao;

import java.util.List;
import java.util.Map;






import com.talk.dto.User2Group;

/**
 * 针对关联表进行数据库操作
 * @author 12878
 *
 */
public interface User2GroupDao {
	/**
	 * 分页查询所有关联信息
	 */
	public List<Map<String,String>> getUser2GroupListByPage(Map<String,Object> map);
	/**
	 * 查询返回条数
	 */
	public int count(Map<String,Object> map);
	/**
	 * 查询是否有重复的用户和组关联(批量添加时)
	 */
	public int countIdAdd(Map<String,Object> params);
	/**
	 * 查询是否有重复的用户和组关联(单个更新时)
	 */
	public int countIdUpdate(Map<String,Object> params);
	
	/**
	 * 添加新的用户和组关联
	 */
	public int save(Map<String,Object> params);
	/**
	 * 批量开户时添加用户和组关系
	 */
	public int batchSave(Map<String,Object> params);
	/**
	 * 删除用户和组的关联
	 */
	public int delete(Map<String,String> params);
	/**
	 * 修改用户和组的关联
	 */
	public int update(Map<String,Object> params);
	/**
	 * 修改用户时，根据用户Id查询是否有用户和组关联存在
	 * @return true为重复 false为不重复
	 */
	public int countExistUserId(String userId);
	/**
	 * 修改组时，根据组id查询是否有用户和组关联存在
	 * @return true为重复 false为不重复
	 */
	public int countExistGroupId(String groupId);
	/**
	 * 查询所有与组关联的用户
	 */
	public List<Map<String,String>> userIdByGroupId(String groupId);
	/**
	 * 根据用户ID查询所有相关联的组
	 */
	public List<Map<String,String>> selectGroupIdsByUserId(String userId);
	/**
	 * 根据用户id删除所有关联
	 */
	public int deleteByUserId(String userId);
	/**
	 * 根据组id删除所有关联
	 */
	public int deleteByGroupId(String userId);
}
