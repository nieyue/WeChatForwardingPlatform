package com.nieyue.test;

import java.util.ArrayList;
import java.util.List;

import com.nieyue.bean.User;

public class Test4 {
	public static void main(String[] args) {
		System.out.println("freeMemory:");
		System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
		System.out.println("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
		System.out.println("totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		//userUser();
		System.out.println("freeMemory:");
		System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
		System.out.println("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
		System.out.println("totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		StringBuffer a = new StringBuffer();
		a.append("http://");
		a.append("www.baidu.com").append("/sddf");
		System.out.println(a.toString());
		
	}
	static void userUser(){
		List<User> al=new ArrayList<User>();
		for (int i = 0; i < 3600*60; i++) {
		}
	}
}
