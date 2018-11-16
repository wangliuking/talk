package com.talk.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.talk.dto.User;

/**
 * 专门针对User对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface UserDao {

	/**
	 * 统计订单行数
	 * @param user
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 用户数据
	 * @return
	 */
	public List<Map<String,String>> getPageUser(Map<String,Object> params);
	
	/**
	 * 通过用户名和密码查询用户名（登录）
	 * @param u
	 * @return
	 */
	public User getUserByUserIdAndByPassword(User u);
	
	/**
	 * 通过用户编号查询用户数据
	 * @param id
	 * @return user
	 */
	public User getUserById(String userId);
	
	/**
	 * 检查用户名是否重复
	 * @param userId
	 * @return true 表示重复，false表示不重复
	 */
	public int countUserId(String userId);
	/**
	 * 管理员登录
	 * @param u
	 * @return
	 */
	public User getAdmin(User u);
	/**
	 * 新增用户
	 * @param u
	 * @return
	 */
	public int save(User u);
	
	/**
	 * 用户数据更新
	 * @param user
	 * @return
	 */
	public int updateUser(Map<String,Object> params);
	
	/**
	 * 删除用户操作
	 * @param id
	 * @return
	 */
	public int deleteUser(String userId);
	/**
	 * 更新用户登录时间和登录状态
	 */
	public int updateLogin(String userId);
	/**
	 * 注销用户登录状态
	 */
	public int updateLogout(String userId);
	/**
	 * 查询所有用户ID数据
	 *
	 */
	public List<User> getAllUserId();
	/**
	 * 查询用户表所有信息用于redis缓存
	 * 
	 */
	public List<User> getForRedis();
	/**
	 * 超级管理员更新一个用户的访问权限
	 */
	public int powerUpdate(Map<String,String> params);
	/**
	 * 超级管理员批量开户操作
	 */
	public int insertBatch(ArrayList<Map<String, String>> userList);
	/**
	 * 添加excel到数据库
	 * @return
	 * @throws Exception
	 */
	public int insertExcel(List<User> list);
	
}
