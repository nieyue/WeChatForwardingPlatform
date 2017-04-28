package com.nieyue.test;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Set;

import com.mchange.v2.ser.SerializableUtils;
import com.nieyue.bean.News;

import redis.clients.jedis.Jedis;

public class Test7 {
	
	 public static void main(String[] args) throws ClassNotFoundException, IOException {
	        Jedis redis = new  Jedis("120.25.228.28");
	        redis.auth("liliu");
	    //  SimpleExample(redis);
	        
	      //ListExample(redis,20);
	        //ListExamplePop(redis,2);
	        
	        //PublishExample(redis,100);
	       // SubscriptExample(redis,100);
	        Set<String> ss = redis.keys("We*");
	        System.out.println(ss);
	    }
	    //简单添加信息
	    public static void SimpleExample(Jedis redis){
	        redis.set("key1", "I am value 1");  
	        String ss = redis.get("key1");  
	        System.out.println(ss);
	    }
	    //队列添加信息
	    public static void ListExample(Jedis redis,int number){
	        String messageStr = "";
	        int count = 0;
	        while(count++ < number){
	            messageStr =  "this is "+count+" message!";
	            Long lpush = redis.lpush("logstash-test-list",messageStr);
	            //redis.rpush("logstash-test-list",messageStr);
	            System.out.println(lpush);
	            System.out.println(messageStr);
	        }
	    }
	    //队列删除信息
	    public static void ListExamplePop(Jedis redis,int number){
	    	String messageStr = "";
	    	int count = 0;
	    	while(count++ < number){
	    		messageStr =  "this is "+count+" message!";
	    		String rpop = redis.rpop("logstash-test-list");
	    		//redis.rpush("logstash-test-list",messageStr);
	    		System.out.println(rpop);
	    		System.out.println(messageStr);
	    	}
	    }
	    //发布订阅
	    public static void PublishExample(Jedis redis,int number){
	        String messageStr = "";
	        int count = 0;
	        while(count++ < number){
	            messageStr =  "this is "+count+" message!";
	            redis.publish("publish-test-list",messageStr);
	            System.out.println(messageStr);
	        }
	    }
	    //消费订阅
	    public static void SubscriptExample(Jedis redis,int number){
	    	String messageStr = "";
	    	int count = 0;
	    	while(count++ < number){
	    		messageStr =  "this is "+count+" message!";
	    		redis.substr("publish-test-list",0,-1);
	    		System.out.println(messageStr);
	    	}
	    }
}
