package com.talk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.talk.common.PageModel;
import com.talk.common.WebConstant;
import com.talk.dto.CallInfo;
import com.talk.dto.User;
import com.talk.service.CallInfoService;

/**
 * 呼叫信息处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/callInfo")
public class CallInfoController {
	
	@Resource
	private CallInfoService callInfoService;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询呼叫信息
	 * @param callInfo
	 * @param data
	 * @return
	 */
	@RequestMapping("/callInfoList")
	public String getOrderList(CallInfo callInfo,@RequestParam(defaultValue="0")int pageIndex,Model data,HttpSession session){
		User userSession = (User)session.getAttribute(WebConstant.SESSION_USER);
		String str7 = userSession.getPower().substring(6, 7);
		String person = userSession.getUserId();
		if("2".equals(str7) || "1".equals(str7)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("callInfo",callInfo);
			int recordCount = callInfoService.count(params);
			if(recordCount != 0){
				//设置总共有多个条记录（方便进行分页计算）
				pageModel.setRecordCount(recordCount);
				//设置当前页
				pageModel.setPageIndex(pageIndex);
				
				params.put("pageModel", pageModel);
				List<CallInfo> callInfoList = callInfoService.getPageCallInfo(params);			
				data.addAttribute("pageModel", pageModel);
				data.addAttribute("callInfoList", callInfoList);
				data.addAttribute("str7", str7);
				data.addAttribute("person", person);
			}
			return "callInfo/callInfoList";
		}else{
			return "exception/noPower";
		}
		
	}
	

	/**
	 * 根据id删除呼叫信息
	 * @param callInfo
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		callInfoService.delete(id);
		return "forward:/admin/callInfo/callInfoList";
	}
	
	/**
	 * 不兼容h5 audio的浏览器使用弹窗播放
	 */
	@RequestMapping("/sound")
	public String sound(HttpServletRequest req,Model data){
		String filePathSound = req.getParameter("filePath");
		data.addAttribute("filePathSound",filePathSound);
		return "callInfo/abc";
	}

	/**
	 * 弹窗视频播放
	 */
	@RequestMapping("/flv")
	public String flv(HttpServletRequest req,Model data){
		String filePathSound = req.getParameter("filePath");
		data.addAttribute("filePathSound",filePathSound);
		return "callInfo/flv";
	}
	
	
}
