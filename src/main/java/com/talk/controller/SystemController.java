package com.talk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.talk.common.PageModel;
import com.talk.common.WebConstant;
import com.talk.dto.System;
import com.talk.dto.User;
import com.talk.service.SystemService;

/**
 * 系统参数处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/system")
public class SystemController {
	
	@Resource
	private SystemService systemService;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询用户
	 * @param system
	 * @param data
	 * @return
	 */
	@RequestMapping("/systemList")
	public String getOrderList(System system,@RequestParam(defaultValue="0")int pageIndex,Model data,HttpSession session){
		//从session中取出power判断权限
		User userSession = (User)session.getAttribute(WebConstant.SESSION_USER);
		String str2 = userSession.getPower().substring(1, 2);
		if("2".equals(str2) || "1".equals(str2)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("system",system);
			int recordCount = systemService.count(params);
			if(recordCount != 0){
				//设置总共有多个条记录（方便进行分页计算）
				pageModel.setRecordCount(recordCount);
				//设置当前页
				pageModel.setPageIndex(pageIndex);
				
				params.put("pageModel", pageModel);
				List<System> systemList = systemService.getPageSystem(params);
				
				data.addAttribute("pageModel", pageModel);
				data.addAttribute("systemList", systemList);
				data.addAttribute("str2", str2);
			}
			return "system/systemList";
		}else{
			return "exception/noPower";
		}
		
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(Model data){
		System system = systemService.getSystem();
		data.addAttribute("system", system);
		return "system/updateSystem";
	}
	/**
	 * 系统参数更新
	 * @param system
	 * @return
	 */
	@RequestMapping("/update")
	public String update(System system){
		systemService.updateSystem(system);
		return "forward:/admin/system/systemList";
	}
	
	
}
