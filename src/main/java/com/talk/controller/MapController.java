package com.talk.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.talk.common.WebConstant;
import com.talk.dto.Gps;
import com.talk.dto.User;
import com.talk.service.GpsService;

@Controller
public class MapController {
	
	@Resource
	private GpsService gpsService;
	/**
	 * 地图资源处理类
	 * @author 12878
	 * @param map
	 * @return
	 */
	@RequestMapping("/test")
	public String testMap(HttpServletRequest req){
		return "map-simple";
	}
	
	/**
	 * 查询最近一分钟的用户位置
	 */
	@RequestMapping("/toJSON")
	@ResponseBody
	public JSONArray toJSON(HttpServletRequest req){
		User user = (User)req.getSession().getAttribute(WebConstant.SESSION_USER);
		JSONArray json = new JSONArray();
		List<Map<String,Object>> gpsList = gpsService.getUserPosition();
			int temp=0;
			for(int i=0;i<gpsList.size();i++){
				json.add(temp, gpsList.get(temp));
				temp++;
			}
			return json;
	}
}
