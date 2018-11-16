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
import com.talk.common.PublicCallProtobuf;
import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.GpsUser;
import com.talk.dto.User;
import com.talk.service.GpsUserService;

/**
 * gps用户处理类
 * 
 * @author 12878
 * 
 */
@Controller
@RequestMapping("admin/gpsuser")
public class GpsUserController {

	@Resource
	private GpsUserService gpsuserService;

	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();

	/**
	 * 分页查询用户
	 * 
	 * @param gpsuser
	 * @param data
	 * @return
	 */
	@RequestMapping("/gpsuserList")
	public String getOrderList(GpsUser gpsuser,
			@RequestParam(defaultValue = "0") int pageIndex, Model data,
			HttpSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gpsuser", gpsuser);
		int recordCount = gpsuserService.count(params);
		if (recordCount != 0) {
			// 设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			// 设置当前页
			pageModel.setPageIndex(pageIndex);

			params.put("pageModel", pageModel);
			List<GpsUser> gpsuserList = gpsuserService.getPageGpsUser(params);

			data.addAttribute("pageModel", pageModel);
			data.addAttribute("gpsuserList", gpsuserList);
		}
		return "gpsuser/gpsuserList";

	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(Model data, HttpServletRequest req) {
		String gpsUserId = req.getParameter("gpsUserId");
		GpsUser gpsuser = gpsuserService.getGpsUser(gpsUserId);
		data.addAttribute("gpsuser", gpsuser);
		return "gpsuser/updateGpsUser";
	}

	/**
	 * gps用户更新
	 * 
	 * @param gpsuser
	 * @return
	 */
	@RequestMapping("/update")
	public String update(GpsUser gpsuser, HttpServletRequest req) {
		String gpsId = req.getParameter("gpsId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gpsuser", gpsuser);
		params.put("gpsId", gpsId);
		int count = gpsuserService.checkById(params);
		if (count > 0) {
			return "exception/existGpsId";
		} else {
			int result = gpsuserService.updateGpsUser(params);
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 1;
				PublicCallProtobuf.STDbGpsUserSync.Builder pub = PublicCallProtobuf.STDbGpsUserSync
						.newBuilder();
				pub.setOperation(2);
				pub.setGpsUserId(Integer.parseInt(gpsId));
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}		
			return "forward:/admin/gpsuser/gpsuserList";
		}
	}

}
