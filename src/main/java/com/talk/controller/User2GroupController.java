package com.talk.controller;

import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.talk.common.PageModel;
import com.talk.common.PublicCallProtobuf;
import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.Group;
import com.talk.dto.User;
import com.talk.dto.User2Group;
import com.talk.service.GroupService;
import com.talk.service.User2GroupService;
import com.talk.service.UserService;

@Controller
@RequestMapping("/admin/user2group")
public class User2GroupController {
	private FlexJSON json=new FlexJSON();
	
	@Resource
	private User2GroupService user2groupService;
	@Resource
	private UserService userService;
	@Resource
	private GroupService groupService;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	
	/**
	 * 跳转到用户和组列表页面
	 */
	@RequestMapping("/toUser2groupList")
	public String toUser2groupList(Model data){
		return "user2group/user2groupList";
	}
	
	/**
	 * 分页查询用户（ajax模式）
	 * @param user2group
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/user2groupList",method = RequestMethod.GET)
	public void getOrderList(HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String tStart = request.getParameter("start");
		String tLimit = request.getParameter("limit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("start", Integer.parseInt(tStart));
		map.put("limit", Integer.parseInt(tLimit));
		HashMap result = new HashMap();
		result.put("user2groupList",user2groupService.getUser2GroupListByPage(map));
		result.put("totals", user2groupService.count(map));
		response.setContentType("application/json;charset=utf-8");
		String jsonstr = json.Encode(result);
		try {
			response.getWriter().write(jsonstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加用户页面获取数据源（ajax模式）
	 */
	@RequestMapping(value="/addUser2GroupData",method = RequestMethod.GET)
	public void addUser2GroupData(HttpServletRequest request, HttpServletResponse response){
		List<User> userList = userService.getAllUserId();
		List<Group> groupList = groupService.getAllGroupId();
		String selectUserId=request.getParameter("selectUserId");
		String tStart=request.getParameter("start");
		String tLimit=request.getParameter("limit");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", Integer.parseInt(tStart));
		map.put("limit", Integer.parseInt(tLimit));
		HashMap result = new HashMap();
		result.put("userList",userService.getAllUserId());
		result.put("totals", groupService.getAllGroupId().size());
		List<Map<String,String>> list = groupService.getAllGroupIdByPage(map);
		if("请选择".equals(selectUserId)){
			System.out.println("已进入1111111111！！！");
			for(int i=0;i<list.size();i++){
				Map<String,String> iMap = list.get(i);
				iMap.put("choosed", "0");
			}
		}else{
			System.out.println("已进入2222222222！！！");
			List<Map<String,String>> tempList = user2groupService.selectGroupIdsByUserId(selectUserId);
			for(int i=0;i<list.size();i++){
				if(tempList.size()==0){
					Map<String,String> iMap = list.get(i);
					iMap.put("choosed", "0");
				}else{
					for(int j=0;j<tempList.size();j++){
						Map<String,String> iMap = list.get(i);
						Map<String,String> jMap = tempList.get(j);
						if(iMap.get("groupId").equals(jMap.get("groupId"))){
							iMap.put("choosed", "1");
							break;
						}else if(j==tempList.size()-1){
							iMap.put("choosed", "0");
							break;
						}
					}
				}
			}
		}
		result.put("groupList", list);
		response.setContentType("application/json;charset=utf-8");  
		String jsonstr = json.Encode(result);
		try {
			response.getWriter().write(jsonstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String userId,String groupId,Model data){
		List<User> userList = userService.getAllUserId();
		List<Group> groupList = groupService.getAllGroupId();
		data.addAttribute("userList", userList);
		data.addAttribute("groupList",groupList);
		data.addAttribute("userId",userId);
		data.addAttribute("groupId", groupId);
		return "user2group/updateUser2Group";
	}
	/**
	 * 关联数据更新
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String,Object> update(String userId,String groupId,String updateUserId,String updateGroupId){
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		params.put("updateUserId", updateUserId);
		params.put("updateGroupId", updateGroupId);
		int countId = user2groupService.countIdUpdate(params);
		if(countId==0){
			int result = user2groupService.update(params);	
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0]=1;
				commandId[1]=4;
				PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
						.newBuilder();
				pub.setUserId(userId);
				pub.setGroupId(groupId);
				pub.setOperation(2);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId,content);
				map.put("tip", "添加成功");
				map.put("status", 0);
			}else{
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		}else if(countId!=0){
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);		
		}	
		return map;
	}
	/**
	 * 跳转到添加用户界面
	 */
	@RequestMapping("/toAddUser2Group")
	public String toAddUser(Model data){
		return "user2group/addUser2Group";
	}
	
	/**
	 * 添加新的用户和组关联
	 */
	@ResponseBody
	@RequestMapping("/addUser2Group")
	public Map<String,Object> addUser2Group(String userId,String groupIds){
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> params = new HashMap<>();
		params.put("userId", userId);
		List<String> list = Arrays.asList(groupIds.split(","));
		params.put("groupId", list);
		int countId = user2groupService.countIdAdd(params);
		System.out.println("countId: "+countId);
		if(countId==0){
			int result = user2groupService.save(params);	
			if(result>0){
				for(int i=0;i<list.size();i++){
					String groupId = list.get(i);
					// 发起udp通知交换中心操作
					byte[] commandId = new byte[2];
					commandId[0]=1;
					commandId[1]=4;
					PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
							.newBuilder();
					pub.setUserId(userId);
					pub.setGroupId(groupId);
					pub.setOperation(1);
					byte[] content = pub.build().toByteArray();
					UDPTest.sendOperation(commandId,content);
				}
				map.put("tip", "添加成功");
				map.put("status", 0);
			}else{
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		}else if(countId!=0){
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);		
		}
		return map;
	}
	/**
	 * 删除用户和组的关联
	 */
	@RequestMapping("/delete")
	public String deleteUser2Group(String userId,String groupId){
		Map<String,String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		int result = user2groupService.delete(params);
		if(result>0){
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0]=1;
			commandId[1]=4;
			PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
					.newBuilder();
			pub.setUserId(userId);
			pub.setGroupId(groupId);
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId,content);
		}
		return "forward:/admin/user2group/toUser2groupList";
	}
}
