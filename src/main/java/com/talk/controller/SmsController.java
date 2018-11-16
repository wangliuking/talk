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
import com.talk.dto.Sms;
import com.talk.dto.User;
import com.talk.service.SmsService;

/**
 * 短信记录处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/sms")
public class SmsController {
	
	@Resource
	private SmsService smsService;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询短信记录
	 * @param sms
	 * @param data
	 * @return
	 */
	@RequestMapping("/smsList")
	public String getOrderList(Sms sms,@RequestParam(defaultValue="0")int pageIndex,Model data,HttpSession session){
		User userSession = (User)session.getAttribute(WebConstant.SESSION_USER);
		String str5 = userSession.getPower().substring(4, 5);
		String person = userSession.getUserId();
		if("2".equals(str5) || "1".equals(str5)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("sms",sms);
			int recordCount = smsService.count(params);
			if(recordCount != 0){
				//设置总共有多个条记录（方便进行分页计算）
				pageModel.setRecordCount(recordCount);
				//设置当前页
				pageModel.setPageIndex(pageIndex);
				
				params.put("pageModel", pageModel);
				List<Sms> smsList = smsService.getPageSms(params);
				
				data.addAttribute("pageModel", pageModel);
				data.addAttribute("smsList", smsList);
				data.addAttribute("str5", str5);
				data.addAttribute("person", person);
			}
			return "sms/smsList";
		}else{
			return "exception/noPower";
		}
		
	}
	

	/**
	 * 根据id删除短信记录
	 * @param sms
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		smsService.delete(id);
		return "forward:/admin/sms/smsList";
	}
	
	
}
