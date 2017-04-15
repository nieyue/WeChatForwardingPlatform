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

import com.nieyue.bean.Domain;
import com.nieyue.dto.StateResult;
import com.nieyue.service.DomainService;


/**
 * 域名控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/domain")
public class DomainController {
	@Resource
	private DomainService domainService;
	
	/**
	 * 域名分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Domain> browsePagingDomain(
			@RequestParam(value="status",required=false)String status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="domain_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Domain> list = new ArrayList<Domain>();
			list= domainService.browsePagingDomain(status,pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 域名修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateDomain(@ModelAttribute Domain domain,HttpSession session)  {
		boolean um = domainService.updateDomain(domain);
		return StateResult.getSR(um);
	}
	/**
	 * 域名增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addDomain(@ModelAttribute Domain domain, HttpSession session) {
		boolean am = domainService.addDomain(domain);
		return StateResult.getSR(am);
	}
	/**
	 * 域名删除
	 * @return
	 */
	@RequestMapping(value = "/{domainId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delDomain(@PathVariable("domainId") Integer domainId,HttpSession session)  {
		boolean dm = domainService.delDomain(domainId);
		return StateResult.getSR(dm);
	}
	/**
	 * 域名浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="status",required=false)String status,
			HttpSession session)  {
		int count = domainService.countAll(status);
		return count;
	}
	/**
	 * 域名单个加载
	 * @return
	 */
	@RequestMapping(value = "/{domainId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Domain loadDomain(@PathVariable("domainId") Integer domainId,HttpSession session)  {
		Domain Domain=new Domain();
			Domain = domainService.loadDomain(domainId);
		return Domain;
	}
	
}
