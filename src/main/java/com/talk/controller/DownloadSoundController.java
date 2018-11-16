package com.talk.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 录音文件下载处理类
 * @author 12878
 *
 */
@Controller
public class DownloadSoundController {
	/**
	 * 下载方法
	 */
	@RequestMapping("/download")
	public void downloadSound(HttpServletRequest request,HttpServletResponse response){
			try{
			String path = request.getParameter("downloadId");
			String str = this.getClass().getClassLoader().getResource("").toURI().getPath();
			//截取字符串
			String strTemp = str.substring(0, str.length()-16);
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
