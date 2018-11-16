package com.talk.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.talk.common.PageModel;
import com.talk.common.PublicCallProtobuf;
import com.talk.common.TempModel;
import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.Group;
import com.talk.dto.User;
import com.talk.service.Group2BdService;
import com.talk.service.GroupService;
import com.talk.service.User2GroupService;

@Controller
@RequestMapping("/admin/group")
public class GroupController {
	private FlexJSON json=new FlexJSON();

	@Resource
	private GroupService groupService;
	@Resource
	private User2GroupService user2groupService;
	@Resource
	private Group2BdService group2bdService;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	
	/**
	 * 跳转到组页面
	 */
	@RequestMapping("/toGroupList")
	public String toUserList(Model data){
		return "group/groupList";
	}

	/**
	 * 分页查询组（ajax模式）
	 * 
	 * @param group
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/groupList",method = RequestMethod.GET)
	public void getOrderList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		User userSession = (User) session
				.getAttribute(WebConstant.SESSION_USER);
		String str3 = userSession.getPower().substring(2, 3);
		if ("2".equals(str3) || "1".equals(str3)) {
			String groupId = request.getParameter("groupId");
			String tStart = request.getParameter("start");
			String tLimit = request.getParameter("limit");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupId", groupId);
			map.put("start", Integer.parseInt(tStart));
			map.put("limit", Integer.parseInt(tLimit));
			HashMap result = new HashMap();
			result.put("groupList",groupService.getPageGroup(map));
			result.put("totals", groupService.count(map));
			response.setContentType("application/json;charset=utf-8");
			String jsonstr = json.Encode(result);
			try {
				response.getWriter().write(jsonstr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String groupId) {
		//检测是否有组和用户关联
		List<Map<String,String>> list = user2groupService.userIdByGroupId(groupId);
		//检测是否有组和局向关联
		int temp = group2bdService.countBdIdByGroupId(groupId);
		//删除组
		int result = groupService.deleteGroup(groupId);
		
		if(result>0 && list!=null){
			for(int i=0;i<list.size();i++){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 4;
				PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
						.newBuilder();
				pub.setUserId(list.get(i).get("userId"));
				pub.setGroupId(groupId);
				pub.setOperation(3);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}		
		}
		
		if(temp>0){
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0] = 1;
			commandId[1] = 6;
			PublicCallProtobuf.STDbGroup2BdSync.Builder pub = PublicCallProtobuf.STDbGroup2BdSync
					.newBuilder();
			pub.setBdId("");
			pub.setGroupId(groupId);
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId, content);
		}
		
		if(result>0){
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0] = 1;
			commandId[1] = 3;
			PublicCallProtobuf.STDbGroupSync.Builder pub = PublicCallProtobuf.STDbGroupSync
					.newBuilder();
			pub.setGroupId(groupId);
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId, content);
		}	
		return "forward:/admin/group/toGroupList";

	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String groupId, Model data) {
		Group group = groupService.getGroupById(groupId);
		data.addAttribute("group", group);
		return "group/updateGroup";
	}

	/**
	 * 组更新
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Group group) {
		String[] g = group.getGroupId().split(",");
		group.setGroupId(g[1]);
		TempModel tempModel = new TempModel();
		tempModel.setTempGroup(g[0]);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", group);
		params.put("tempModel", tempModel);
		if (groupService.countGroupId(group.getGroupId())
				&& !group.getGroupId().equals(tempModel.getTempGroup())) {
			return "exception/existUpdateGroupId";
		} else {
			int result = groupService.updateGroup(params);
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 3;
				PublicCallProtobuf.STDbGroupSync.Builder pub = PublicCallProtobuf.STDbGroupSync
						.newBuilder();
				pub.setGroupId(group.getGroupId());
				pub.setOperation(2);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}		
			return "redirect:/admin/group/toGroupList";
		}
	}

	/**
	 * 跳转到组添加界面
	 */
	@RequestMapping("/toAddGroup")
	public String toAddGroup() {
		return "group/addGroup";
	}

	/**
	 * 添加新的组
	 */
	@RequestMapping("/addGroup")
	public String addGroup(Group group) {
		String groupId = group.getGroupId();
		if (groupService.countGroupId(groupId)) {
			return "exception/existGroupId";
		} else {
			int result = groupService.save(group);
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 3;
				PublicCallProtobuf.STDbGroupSync.Builder pub = PublicCallProtobuf.STDbGroupSync
						.newBuilder();
				pub.setGroupId(groupId);
				pub.setOperation(1);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}		
			return "redirect:/admin/group/toGroupList";
		}
	}

}
