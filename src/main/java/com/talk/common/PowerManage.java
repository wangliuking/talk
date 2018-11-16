package com.talk.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PowerManage {
	public static List<String> powerToList(String str){
		List<String> list = new ArrayList<>();
        String[] strs = new String[str.length()];
        for(int i=0;i<str.length();i++){
            strs[i] = str.substring(i, i+1);
            list.add(i, strs[i]);
        }
        return list;
	}
	public static List<String> FunctionModule(){
		List<String> list = new LinkedList<>();
		list.add(0, "用户模块");
		list.add(1, "系统参数模块");
		list.add(2, "组模块");
		list.add(3, "用户和组关联模块");
		list.add(4, "短信记录模块");
		list.add(5, "GPS数据模块");
		list.add(6, "呼叫信息模块");
		return list;
	}

}
