package com.nieyue.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.nieyue.util.HttpClientUtil;

import net.sf.json.JSONObject;

public class Test6 {
public static void main(String[] args) throws UnsupportedEncodingException {
	String domain="http://a.fuwu88.cn/dailyRecommended/news_details?type=retx&news_id=9852";
	System.out.println(URLEncoder.encode(domain, "gb2312"));
	System.out.println(URLEncoder.encode("聂跃", "gb2312"));
	System.out.println(URLEncoder.encode(URLEncoder.encode("聂跃", "utf-8"),"utf-8"));
	System.out.println(URLEncoder.encode(URLEncoder.encode("聂跃", "utf-8"),"gb2312"));
	System.out.println(URLEncoder.encode(URLEncoder.encode("聂跃", "gb2312"),"utf-8"));
	System.out.println(URLEncoder.encode(URLEncoder.encode(domain, "utf-8"),"utf-8"));
	System.out.println(URLEncoder.encode(URLEncoder.encode(domain, "utf-8"),"gb2312"));
	System.out.println(URLEncoder.encode(URLEncoder.encode(domain, "gb2312"),"utf-8"));
	//String url="http://vip.weixin139.com/weixin/wx_domain.php?user=changsheng&key=a75b9dd795e34ad490cc6dda82dc112f&domain="+URLEncoder.encode(domain, "gb2312");
	//String url="http://vip.weixin139.com/weixin/wx_domain.php?user=changsheng&key=a75b9dd795e34ad490cc6dda82dc112f&domain="+URLEncoder.encode(URLEncoder.encode(domain, "gb2312"),"utf-8");
	//String url="http://vip.weixin139.com/weixin/wx_domain.php?user=changsheng&key=a75b9dd795e34ad490cc6dda82dc112f&domain="+URLEncoder.encode(URLEncoder.encode(domain, "utf-8"),"utf-8");
	String url="http://vip.weixin139.com/weixin/wx_domain.php?user=changsheng&key=a75b9dd795e34ad490cc6dda82dc112f&domain="+URLEncoder.encode(URLEncoder.encode(domain, "gb2312"),"gb2312");
	System.out.println(url);
	try {
		String s = HttpClientUtil.doGet(url);
		System.out.println(s);
		System.out.println(JSONObject.fromObject(s).get("status"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
