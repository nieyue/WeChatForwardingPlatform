package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Data;
import com.nieyue.dto.StateResult;
import com.nieyue.service.DataService;


/**
 * 数据控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/data")
public class DataController {
	@Resource
	private DataService dataService;
	
	/**
	 * 数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Data> browsePagingData(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Data> list = new ArrayList<Data>();
			list= dataService.browsePagingData(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 数据修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateData(@ModelAttribute Data data,HttpSession session)  {
		boolean um = dataService.updateData(data);
		return StateResult.getSR(um);
	}
	/**
	 * 数据增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addData(@ModelAttribute Data data, HttpSession session) {
		boolean am = dataService.addData(data);
		return StateResult.getSR(am);
	}
	/**
	 * 数据删除
	 * @return
	 */
	@RequestMapping(value = "/{dataId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delData(@PathVariable("dataId") Integer dataId,HttpSession session)  {
		boolean dm = dataService.delData(dataId);
		return StateResult.getSR(dm);
	}
	/**
	 * 数据浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = dataService.countAll();
		return count;
	}
	/**
	 * 数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{dataId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Data loadData(@PathVariable("dataId") Integer dataId,HttpSession session)  {
		Data data=new Data();
			data = dataService.loadData(dataId);
		return data;
	}
	
}
