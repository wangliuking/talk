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
import org.springframework.web.bind.annotation.ResponseBody;

import com.talk.common.PageModel;
import com.talk.common.PublicCallProtobuf;
import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.Group;
import com.talk.dto.Bureaudirection;
import com.talk.dto.Group2Bd;
import com.talk.service.GroupService;
import com.talk.service.Group2BdService;
import com.talk.service.BureaudirectionService;

@Controller
@RequestMapping("/admin/group2bd")
public class Group2BdController {

	@Resource
	private Group2BdService group2bdService;
	@Resource
	private BureaudirectionService bureaudirectionService;
	@Resource
	private GroupService groupService;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();

	/**
	 * 分页查询用户
	 * 
	 * @param group2bd
	 * @param data
	 * @return
	 */
	@RequestMapping("/group2bdList")
	public String getOrderList(Group2Bd group2bd,
			@RequestParam(defaultValue = "0") int pageIndex, Model data,
			HttpSession session) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group2bd", group2bd);
		int recordCount = group2bdService.count(params);
		if (recordCount != 0) {
			// 设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			// 设置当前页
			pageModel.setPageIndex(pageIndex);

			params.put("pageModel", pageModel);
			List<Group2Bd> group2bdList = group2bdService
					.getGroup2BdListByPage(params);

			data.addAttribute("pageModel", pageModel);
			data.addAttribute("group2bdList", group2bdList);
		}
		return "group2bd/group2bdList";

	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String bdId, String groupId, Model data) {
		List<Bureaudirection> bureaudirectionList = bureaudirectionService
				.getAllBureaudirectionId();
		List<Group> groupList = groupService.getAllGroupId();
		data.addAttribute("bureaudirectionList", bureaudirectionList);
		data.addAttribute("groupList", groupList);
		data.addAttribute("bdId", bdId);
		data.addAttribute("groupId", groupId);
		return "group2bd/updateGroup2Bd";
	}

	/**
	 * 关联数据更新
	 * 
	 * @param bureaudirection
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(String bdId, String groupId,
			String updateBdId, String updateGroupId) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("bdId", bdId);
		params.put("groupId", groupId);
		params.put("updateBdId", updateBdId);
		params.put("updateGroupId", updateGroupId);
		int countId = group2bdService.countId(params);
		if (countId == 0) {
			int count = group2bdService.update(params);
			if (count > 0) {

				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 6;
				PublicCallProtobuf.STDbGroup2BdSync.Builder pub = PublicCallProtobuf.STDbGroup2BdSync
						.newBuilder();
				pub.setBdId(bdId);
				pub.setGroupId(groupId);
				pub.setOperation(2);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);

				map.put("tip", "添加成功");
				map.put("status", 0);
			} else {
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		} else if (countId != 0) {
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);
		}
		return map;
	}

	/**
	 * 跳转到添加界面
	 */
	@RequestMapping("/toAddGroup2Bd")
	public String toAddBureaudirection(Model data) {
		List<Bureaudirection> bureaudirectionList = bureaudirectionService
				.getAllBureaudirectionId();
		List<Group> groupList = groupService.getAllGroupId();
		data.addAttribute("bureaudirectionList", bureaudirectionList);
		data.addAttribute("groupList", groupList);
		return "group2bd/addGroup2Bd";
	}

	/**
	 * 添加新的局向
	 */
	@ResponseBody
	@RequestMapping("/addGroup2Bd")
	public Map<String, Object> addGroup2Bd(String bdId, String groupId) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("bdId", bdId);
		params.put("groupId", groupId);
		int countId = group2bdService.countId(params);
		if (countId == 0) {
			int count = group2bdService.save(params);
			if (count > 0) {
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 6;
				PublicCallProtobuf.STDbGroup2BdSync.Builder pub = PublicCallProtobuf.STDbGroup2BdSync
						.newBuilder();
				pub.setBdId(bdId);
				pub.setGroupId(groupId);
				pub.setOperation(1);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
				map.put("tip", "添加成功");
				map.put("status", 0);
			} else {
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		} else if (countId != 0) {
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);
		}
		return map;
	}

	/**
	 * 删除用户和组的关联
	 */
	@RequestMapping("/delete")
	public String deleteGroup2Bd(String bdId, String groupId) {
		Map<String, String> params = new HashMap<>();
		params.put("bdId", bdId);
		params.put("groupId", groupId);
		int count = group2bdService.delete(params);
		if (count > 0) {
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0] = 1;
			commandId[1] = 6;
			PublicCallProtobuf.STDbGroup2BdSync.Builder pub = PublicCallProtobuf.STDbGroup2BdSync
					.newBuilder();
			pub.setBdId(bdId);
			pub.setGroupId(groupId);
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId, content);
		}
		return "forward:/admin/group2bd/group2bdList";
	}
}
