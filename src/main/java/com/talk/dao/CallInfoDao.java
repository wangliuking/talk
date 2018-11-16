package com.talk.dao;

import java.util.List;
import java.util.Map;
import com.talk.dto.CallInfo;

/**
 * 专门针对CallInfo对象的数据库操作
 * 
 * @author 12878
 *
 */
public interface CallInfoDao {

	/**
	 * 统计行数
	 * @param CallInfo
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 呼叫信息
	 * @return
	 */
	public List<CallInfo> getPageCallInfo(Map<String,Object> params);
	/**
	 * 查询全部呼叫信息
	 * @return
	 */
	public CallInfo getCallInfo();
	

	/**
	 * 根据id删除呼叫信息
	 * @param callInfo
	 * @return
	 */
	public int delete(int id);

}
