package com.nieyue.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * @author 聂跃
 * @date 2017年4月27日
 */
@Component
public class MyJson  {
	 JsonConfig jsonConfig;
	 JsonDateValueProcessor jsonDateValueProcessor;
	public  JsonConfig getJsonConfig(){
		if(jsonConfig==null){
			 jsonConfig = new JsonConfig();
			}
			if(jsonDateValueProcessor==null){
				jsonDateValueProcessor = new JsonDateValueProcessor();
				}
			jsonConfig.registerJsonValueProcessor(Date.class, jsonDateValueProcessor);
		return jsonConfig;
	}
	/**
	 * 内部类
	 * @author 聂跃
	 * @date 2017年4月27日
	 */
	class JsonDateValueProcessor implements JsonValueProcessor {

	    private String format = "yyyy-MM-dd HH:mm:ss";

	    public JsonDateValueProcessor( ) {
	        super();
	    }

	    public JsonDateValueProcessor(String format) { // 自己需要的格式
	        super();
	        this.format = format;
	    }

	    @Override
	    public Object processArrayValue(Object value, JsonConfig paramJsonConfig) {
	        return process(value);
	    }

	    @Override
	    public Object processObjectValue(String key, Object value, JsonConfig paramJsonConfig) {
	        return process(value);
	    }

	    private Object process(Object value) {
	        if (value instanceof Date) {
	            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
	            return sdf.format(value);
	        }
	        return value == null ? "" : value.toString();
	    }
	}
}
