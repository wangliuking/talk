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
import com.talk.dto.Gps;
import com.talk.dto.User;
import com.talk.service.GpsService;

/**
 * GPS数据处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/gps")
public class GpsController {
	
	@Resource
	private GpsService gpsService;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询短信记录
	 * @param gps
	 * @param data
	 * @return
	 */
	@RequestMapping("/gpsList")
	public String getOrderList(Gps gps,@RequestParam(defaultValue="0")int pageIndex,Model data,HttpSession session){
		User userSession = (User)session.getAttribute(WebConstant.SESSION_USER);
		String str6 = userSession.getPower().substring(5, 6);
		String person = userSession.getUserId();
		if("2".equals(str6) || "1".equals(str6)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("gps",gps);
			int recordCount = gpsService.count(params);
			if(recordCount != 0){
				//设置总共有多个条记录（方便进行分页计算）
				pageModel.setRecordCount(recordCount);
				//设置当前页
				pageModel.setPageIndex(pageIndex);
				
				params.put("pageModel", pageModel);
				List<Gps> gpsList = gpsService.getPageGps(params);
				
				data.addAttribute("pageModel", pageModel);
				data.addAttribute("gpsList", gpsList);
				data.addAttribute("str6", str6);
				data.addAttribute("person", person);
			}
			return "gps/gpsList";
		}else{
			return "exception/noPower";
		}
		
	}
	

	/**
	 * 根据id删除gps记录
	 * @param gps
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		gpsService.delete(id);
		return "forward:/admin/gps/gpsList";
	}
	
	
}
