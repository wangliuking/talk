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
import com.talk.dto.Bureaudirection;
import com.talk.service.BureaudirectionService;
import com.talk.service.Group2BdService;

@Controller
@RequestMapping("/admin/bureaudirection")
public class BureaudirectionController {
	private FlexJSON json=new FlexJSON();

	@Resource
	private BureaudirectionService bureaudirectionService;
	@Resource
	private Group2BdService group2bdService;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	
	/**
	 * 跳转到局向页面
	 */
	@RequestMapping("/toBureaudirectionList")
	public String toUserList(Model data){
		return "bureaudirection/bureaudirectionList";
	}

	/**
	 * 分页查询局向（ajax模式）
	 * 
	 * @param bureaudirection
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/bureaudirectionList",method = RequestMethod.GET)
	public void getOrderList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String bdId = request.getParameter("bdId");
		String tStart = request.getParameter("start");
		String tLimit = request.getParameter("limit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bdId", bdId);
		map.put("start", Integer.parseInt(tStart));
		map.put("limit", Integer.parseInt(tLimit));
		HashMap result = new HashMap();
		result.put("bureaudirectionList",bureaudirectionService.getPageBureaudirection(map));
		result.put("totals", bureaudirectionService.count(map));
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
	 * 删除操作
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String bdId) {
		// 查询所有与此局向关联的组
		List<Map<String,String>> list = group2bdService.countGroupIdByBdId(bdId);
		//删除此局向关联的所有组
		int result = bureaudirectionService.deleteBureaudirection(bdId);
		
		if(list!=null && result>0){
			for(int i=0;i<list.size();i++){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 6;
				PublicCallProtobuf.STDbGroup2BdSync.Builder pub = PublicCallProtobuf.STDbGroup2BdSync
						.newBuilder();
				pub.setBdId(bdId);
				pub.setGroupId(list.get(i).get("groupId"));
				pub.setOperation(3);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}			
		}
		
		if(result>0){
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0]=1;
			commandId[1]=5;
			PublicCallProtobuf.STDbBureauDirectionSync.Builder pub = PublicCallProtobuf.STDbBureauDirectionSync
					.newBuilder();
			pub.setBdId(bdId);
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId,content);
		}
		return "forward:/admin/bureaudirection/toBureaudirectionList";

	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String bdId, Model data) {
		Bureaudirection bureaudirection = bureaudirectionService.getBureaudirectionById(bdId);
		data.addAttribute("bureaudirection", bureaudirection);
		return "bureaudirection/updateBureaudirection";
	}

	/**
	 * 局向更新
	 * 
	 * @param Bureaudirection
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Bureaudirection bureaudirection) {
		String[] g = bureaudirection.getBdId().split(",");
		bureaudirection.setBdId(g[1]);
		TempModel tempModel = new TempModel();
		tempModel.setTempBureaudirection(g[0]);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bureaudirection", bureaudirection);
		params.put("tempModel", tempModel);
		if (bureaudirectionService.countBureaudirectionId(bureaudirection.getBdId())
				&& !bureaudirection.getBdId().equals(tempModel.getTempBureaudirection())) {
			return "exception/existUpdateBureaudirectionId";
		} else {
			int result = bureaudirectionService.updateBureaudirection(params);
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0]=1;
				commandId[1]=5;
				PublicCallProtobuf.STDbBureauDirectionSync.Builder pub = PublicCallProtobuf.STDbBureauDirectionSync
						.newBuilder();
				pub.setBdId(g[1]);
				pub.setOperation(2);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId,content);
			}
			return "redirect:/admin/bureaudirection/toBureaudirectionList";
		}
	}

	/**
	 * 跳转到局向添加界面
	 */
	@RequestMapping("/toAddBureaudirection")
	public String toAddBureaudirection() {
		return "bureaudirection/addBureaudirection";
	}

	/**
	 * 添加新的局向
	 */
	@RequestMapping("/addBureaudirection")
	public String addBureaudirection(Bureaudirection bureaudirection) {
		String bdId = bureaudirection.getBdId();
		if (bureaudirectionService.countBureaudirectionId(bdId)) {
			return "exception/existBureaudirectionId";
		} else {
			int result = bureaudirectionService.save(bureaudirection);
			if(result>0){
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0]=1;
				commandId[1]=5;
				PublicCallProtobuf.STDbBureauDirectionSync.Builder pub = PublicCallProtobuf.STDbBureauDirectionSync
						.newBuilder();
				pub.setBdId(bdId);
				pub.setOperation(1);
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId,content);
			}
			return "redirect:/admin/bureaudirection/toBureaudirectionList";
		}
	}

}
