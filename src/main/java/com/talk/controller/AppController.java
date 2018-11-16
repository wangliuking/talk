package com.talk.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.talk.common.PageModel;
import com.talk.dto.App;
import com.talk.service.AppService;

/**
 * 系统参数处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/app")
public class AppController {
	
	@Resource
	private AppService appService;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询用户
	 * @param app
	 * @param data
	 * @return
	 */
	@RequestMapping("/appList")
	public String getAppList(App app,@RequestParam(defaultValue="0")int pageIndex,Model data,HttpSession session){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("app",app);
			int recordCount = appService.count(params);
			if(recordCount != 0){
				//设置总共有多个条记录（方便进行分页计算）
				pageModel.setRecordCount(recordCount);
				//设置当前页
				pageModel.setPageIndex(pageIndex);				
				params.put("pageModel", pageModel);
				List<App> appList = appService.getPageApp(params);			
				data.addAttribute("pageModel", pageModel);
				data.addAttribute("appList", appList);
			}
			return "app/appList";
		
		
	}
	
	/**
	 * 跳转到添加页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showAdd")
	public String showAdd(){
		return "app/appAdd";
	}
	/**
	 * app添加
	 * @param app
	 * @return
	 */
	@RequestMapping("/add")
	public String add(App app){
		appService.addApp(app);
		return "forward:/admin/app/appList";
	}
	
	/**
	 * 根据id删除app
	 * @throws URISyntaxException 
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		String path = request.getParameter("path");
		appService.deleteById(id);
		//删除数据库成功后删除对应文件
		String str;
		try {
			str = this.getClass().getClassLoader().getResource("").toURI().getPath();
			//截取字符串
			String strTemp = str.substring(0, str.length()-17);
			String fileName = strTemp+path;
			File file = new File(fileName);
	        if (!file.exists()) {
	            System.out.println("文件不存在！");
	        } else {
	            if (file.isFile()){
	                deleteFile(fileName);
	            }
	            else{
	                System.out.println("不是文件类型");
	            }
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "forward:/admin/app/appList";
	}
	
	 /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
	/**
	 * ajax文件上传
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String fileUpload(@RequestParam("pathName")MultipartFile file,HttpSession session) throws IOException{
		System.out.println("已进入文件上传方法！！！");
		System.out.println("file : "+file);
		System.out.println("session : "+session);
		String fileName = imgsUpload(file, session,"/upload/");		
		return fileName;
	}

	/**
	 * 文件上传公共方法
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	private String imgsUpload(MultipartFile file, HttpSession session,String savePath)
			throws IOException {
		//获取文件在服务器的存储路径
		String path = session.getServletContext().getRealPath(savePath);
		//获取上传文件的名称
		String fileName = file.getOriginalFilename();
		//进行文件存储
		file.transferTo(new File(path,fileName));
		return savePath+fileName;
	}
	/**
	 * 文件下载方法
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response){
			try{
			String temp = request.getParameter("path");
			String path = "/upload/"+temp;
			String str = this.getClass().getClassLoader().getResource("").toURI().getPath();
			//截取字符串
			String strTemp = str.substring(0, str.length()-17);
			String filePath = strTemp+path; //文件在项目中的路径
		    File outfile = new File(filePath);
		    String filename = outfile.getName();// 获取文件名称
		    InputStream fis = new BufferedInputStream(new FileInputStream(
		          filePath));
		    byte[] buffer = new byte[fis.available()]; 
		    fis.read(buffer); //读取文件流
		    fis.close();
		    response.reset(); //重置结果集
		    response.addHeader("Content-Disposition", "attachment;filename="
		        + new String(filename.replaceAll(" ", "").getBytes("utf-8"),
		        "iso8859-1")); //返回头 文件名
		    response.addHeader("Content-Length", "" + outfile.length()); //返回头 文件大小
		    response.setContentType("application/octet-stream");  //设置数据种类
		    //获取返回体输出权
		    OutputStream os = new BufferedOutputStream(response.getOutputStream()); 
		    os.write(buffer); // 输出文件
		    os.flush();
		    os.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	

}
