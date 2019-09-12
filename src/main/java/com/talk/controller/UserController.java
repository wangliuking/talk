package com.talk.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
import org.springframework.web.multipart.MultipartFile;

import com.talk.common.ExportExcel;
import com.talk.common.PageModel;
import com.talk.common.PowerManage;
import com.talk.common.PublicCallProtobuf;
import com.talk.common.ReadExcel;
import com.talk.common.TempModel;
import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.User;
import com.talk.service.GroupService;
import com.talk.service.User2GroupService;
import com.talk.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	private FlexJSON json=new FlexJSON();

	@Resource
	private UserService userService;
	@Resource
	private User2GroupService user2groupService;
	@Resource
	private GroupService groupService;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	
	/**
	 * 跳转到用户页面
	 */
	@RequestMapping("/toUserList")
	public String toUserList(Model data){
		return "user/userList";
	}

	/**
	 * 分页查询用户（ajax模式）
	 *
	 * @return
	 */
	@RequestMapping(value="/userList",method = RequestMethod.GET)
	public void getOrderList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		// 从session中取出power判断权限
		User userSession = (User) session.getAttribute(WebConstant.SESSION_USER);
		String str1 = userSession.getPower().substring(0, 1);
		if ("2".equals(str1) || "1".equals(str1)) {		
			String userId = request.getParameter("userId");
			String tStart = request.getParameter("start");
			String tLimit = request.getParameter("limit");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("start", Integer.parseInt(tStart));
			map.put("limit", Integer.parseInt(tLimit));
			HashMap result = new HashMap();
			result.put("userList",userService.getPageUser(map));
			result.put("totals", userService.count(map));
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
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String userId) {
		//检测是否有用户和组关联
		boolean result = user2groupService.countExistUserId(userId);
		//删除用户及用户的关联
		if(result){
			user2groupService.deleteByUserId(userId);
		}
		int count = userService.deleteUser(userId);
		
		if(count>0 && result){
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0] = 1;
			commandId[1] = 4;
			PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
					.newBuilder();
			pub.setUserId(userId);
			pub.setGroupId("");
			pub.setOperation(3);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId, content);
		}
		
		if (count > 0) {
			// 发起udp通知交换中心操作
			byte[] commandId = new byte[2];
			commandId[0] = 1;
			commandId[1] = 2;
			PublicCallProtobuf.STDbUserSync.Builder pub = PublicCallProtobuf.STDbUserSync
					.newBuilder();
			pub.setOperation(3);
			pub.setUserId(userId);
			byte[] content = pub.build().toByteArray();
			UDPTest.sendOperation(commandId, content);
		}
		return "forward:/admin/user/toUserList";

	}

	/**
	 * 跳转到编辑页面
	 *
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String userId, Model data) {
		User user = userService.getUserById(userId);
		data.addAttribute("user", user);
		return "user/updateUser";
	}

	/**
	 * 用户更新
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public String update(User user) {
		String[] u = user.getUserId().split(",");
		user.setUserId(u[1]);
		TempModel tempModel = new TempModel();
		tempModel.setTemp(u[0]);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("tempModel", tempModel);
		// 修改前判断是否有相同的用户Id
		if (userService.countUserId(user.getUserId())
				&& !user.getUserId().equals(tempModel.getTemp())) {
			return "exception/existUpdateUserId";
		} else {
			int result = userService.updateUser(params);
			if (result > 0) {
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 2;
				PublicCallProtobuf.STDbUserSync.Builder pub = PublicCallProtobuf.STDbUserSync
						.newBuilder();
				pub.setOperation(2);
				pub.setUserId(user.getUserId());
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}
			return "redirect:/admin/user/toUserList";
		}
	}

	/**
	 * 跳转到添加用户界面
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "user/addUser";
	}

	/**
	 * 添加新的用户
	 */
	@RequestMapping("/add")
	public String add(User user) {
		String userId = user.getUserId();
		if (userService.countUserId(userId)) {
			return "exception/existUserId";
		} else {
			int result = userService.save(user);
			if (result > 0) {
				// 发起udp通知交换中心操作
				byte[] commandId = new byte[2];
				commandId[0] = 1;
				commandId[1] = 2;
				PublicCallProtobuf.STDbUserSync.Builder pub = PublicCallProtobuf.STDbUserSync
						.newBuilder();
				pub.setOperation(1);
				pub.setUserId(user.getUserId());
				byte[] content = pub.build().toByteArray();
				UDPTest.sendOperation(commandId, content);
			}
			return "redirect:/admin/user/toUserList";
		}
	}

	/**
	 * 跳转到管理用户权限的页面
	 */
	@RequestMapping("/toChangePower")
	public String toChangePower(String userId, Model data) {
		Map<String, String> powerMap = new LinkedHashMap<>();
		User user = userService.getUserById(userId);
		String str = user.getPower();
		List<String> powerList = PowerManage.powerToList(str);
		List<String> functionList = PowerManage.FunctionModule();
		for (int i = 0; i < powerList.size(); i++) {
			powerMap.put(functionList.get(i), powerList.get(i));
		}
		data.addAttribute("powerMap", powerMap);
		data.addAttribute("user", user);
		return "powerManage/powerList";
	}

	/**
	 * 超级管理员修改一个用户的访问权限
	 */
	@ResponseBody
	@RequestMapping("/powerUpdate")
	public Map<String, Object> powerUpdate(String userId, String power) {
		Map<String, Object> map = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("power", power);
		int count = userService.powerUpdate(params);
		// 发起udp通知交换中心操作
		byte[] commandId = new byte[2];
		commandId[0] = 1;
		commandId[1] = 2;
		PublicCallProtobuf.STDbUserSync.Builder pub = PublicCallProtobuf.STDbUserSync
				.newBuilder();
		pub.setOperation(2);
		pub.setUserId(userId);
		byte[] content = pub.build().toByteArray();
		UDPTest.sendOperation(commandId, content);
		if (count > 0) {
			map.put("tip", "更新成功");
			map.put("status", 0);
		} else if (count == 0) {
			map.put("tip", "更新失败");
			map.put("status", 1);
		}
		return map;
	}

	/**
	 * 跳转到批量开户页面
	 * 
	 */
	@RequestMapping("/toInsertBatch")
	public String toInsertBatch(HttpSession session) {
		User userSession = (User) session.getAttribute(WebConstant.SESSION_USER);
		return "insertBatch/insertBatch";
	}

	/**
	 * 超级管理员批量开户操作
	 */
	@RequestMapping("/insertBatch")
	@ResponseBody
	public Map<String,Object> insertBatch(@RequestParam String num1,@RequestParam String num2,@RequestParam String typeInsert,@RequestParam("tempGroupIds[]") List<String> tempGroupIds) {
		System.out.println("===============");
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(typeInsert);
		System.out.println(tempGroupIds);
		Map<String,Object> res = new HashMap<String,Object>();
		int start = Integer.parseInt(num1);
		int end = Integer.parseInt(num2);
		if(end-start<=1000){
			//判断是批量开户还是批量开组
			if("0".equals(typeInsert)){
				ArrayList<Map<String, String>> userList = new ArrayList<Map<String, String>>();
				Map<String, String> userMap;
				for (int i = start; i <= end; i++) {
					userMap = new HashMap<String, String>();
					userMap.put("userId", "" + i);
					userMap.put("userName", "" + i);
					userList.add(userMap);
				}
				userService.insertBatch(userList);
				// 发起udp通知交换中心操作（添加用户）
				for(Map<String,String> map : userList){
					byte[] commandId = new byte[2];
					commandId[0] = 1;
					commandId[1] = 2;
					PublicCallProtobuf.STDbUserSync.Builder pub = PublicCallProtobuf.STDbUserSync
							.newBuilder();
					pub.setOperation(1);
					pub.setUserId(map.get("userId"));
					byte[] content = pub.build().toByteArray();
					UDPTest.sendOperation(commandId, content);
				}
				//批量添加用户和组关联
				List<Map<String,String>> tempList = new LinkedList<Map<String,String>>();
				for (int i = start; i <= end; i++) {
					for(int j = 0 ; j < tempGroupIds.size(); j++){
						Map<String,String> tempMap = new HashMap<String,String>();
						tempMap.put("userId",i+"");
						tempMap.put("groupId",tempGroupIds.get(j));
						tempList.add(tempMap);
					}
				}
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("list",tempList);
				user2groupService.batchSave(param);
				// 发起udp通知交换中心操作（添加用户和组关联）
				for(Map<String,String> map : tempList){
					byte[] commandId = new byte[2];
					commandId[0]=1;
					commandId[1]=4;
					PublicCallProtobuf.STDbUser2GroupSync.Builder pub = PublicCallProtobuf.STDbUser2GroupSync
							.newBuilder();
					pub.setUserId(map.get("userId"));
					pub.setGroupId(map.get("groupId"));
					pub.setOperation(1);
					byte[] content = pub.build().toByteArray();
					UDPTest.sendOperation(commandId,content);
				}
				res.put("success",true);
				return res;
			}else if("1".equals(typeInsert)){
				ArrayList<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
				Map<String, String> groupMap;
				for (int i = start; i <= end; i++) {
					groupMap = new HashMap<String, String>();
					groupMap.put("groupId", "" + i);
					groupMap.put("groupName", "" + i);
					groupList.add(groupMap);
				}
				groupService.insertBatch(groupList);
				// 发起udp通知交换中心操作
				for(Map<String,String> map : groupList){
					byte[] commandId = new byte[2];
					commandId[0] = 1;
					commandId[1] = 3;
					PublicCallProtobuf.STDbGroupSync.Builder pub = PublicCallProtobuf.STDbGroupSync
							.newBuilder();
					pub.setOperation(1);
					pub.setGroupId(map.get("groupId"));
					byte[] content = pub.build().toByteArray();
					UDPTest.sendOperation(commandId, content);
				}
				res.put("success",true);
				return res;
			}else{
				res.put("success",false);
				return res;
			}
		}else{
			res.put("success",false);
			return res;
		}
	}
	/**
	 * 导出所有用户excel文件
	 */
	@RequestMapping("/export")  
    public void export(HttpServletRequest request, HttpServletResponse response) {  
        List<User> userlList = userService.getForRedis();  
        ExportExcel<User> ee= new ExportExcel<User>();  
        String[] headers = {"用户id号","用户名","登录密码","鉴权码","优先级","类型","最近一次登录时间","登录状态","扫描使能","浏览器类型","管理员类型","权限码"};  
        String fileName = "用户信息表";  
        ee.exportExcel(headers,userlList,fileName,response);  
    }
	
	/**
	 * 将excel导入mysql数据库
	 * 
	 */
	@RequestMapping(value="/upload",method = RequestMethod.POST)  
    @ResponseBody  
    public String upload(@RequestParam(value="file",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){
    	//创建处理EXCEL的类 
    	ReadExcel readExcel=new ReadExcel();
        List<User> list = readExcel.getExcelInfo(file);
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        //解决时间.0无法插入数据库的问题
        for(int i=0;i<list.size();i++){
        	User user = list.get(i);
        	String tempTime = user.getLoginTime();        	
        	if(!"".equals(tempTime) && tempTime!=null){
        		String time = null;
    			try {
    				time = myFmt.format(myFmt.parse(tempTime));
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				System.out.println(user);
    			}
            	user.setLoginTime(time);
        	}else{
        		user.setLoginTime(null);
        	}
        }
        System.out.println(list);
    	userService.insertExcel(list);
    	return "user/toUserList";
    }
	
}
