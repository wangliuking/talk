package com.talk.service;

import java.util.List;
import java.util.Map;

import com.talk.dto.Bureaudirection;

/**
 * 专门针对局向的数据库操作
 * 
 * @author 12878
 *
 */
public interface BureaudirectionService {

	/**
	 * 统计行数
	 * @param Bureaudirection
	 * @return
	 */
	public int count(Map<String,Object> params);
	/**
	 * 分页查询 
	 * @return
	 */
	public List<Bureaudirection> getPageBureaudirection(Map<String,Object> params);

	/**
	 * 通过局向id查询
	 * @param id
	 * @return Bureaudirection
	 */
	public Bureaudirection getBureaudirectionById(String bdId);

	/**
	 * 新增局向
	 * @param u
	 * @return
	 */
	public int save(Bureaudirection u);
	
	/**
	 * 局向数据更新
	 * @param Bureaudirection
	 * @return
	 */
	public int updateBureaudirection(Map<String,Object> params);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteBureaudirection(String bdId);
	
	/**
	 * 查询所有局向数据
	 */
	public List<Bureaudirection> getAllBureaudirectionId();
	/**
	 * 新增局向之前，查询是否有相同id
	 * @return ture 有重复 false 无重复
	 */
	public boolean countBureaudirectionId(String bdId);
}
