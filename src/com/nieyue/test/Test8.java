package com.nieyue.test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Set;

import com.mchange.v2.ser.SerializableUtils;
import com.nieyue.bean.News;

import redis.clients.jedis.Jedis;

public class Test8 {
	
	 public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
	        while (true) {
	        	Thread.sleep(1000);
				System.out.println(11);
				
			}
	    }
}
