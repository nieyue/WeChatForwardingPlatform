package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Manager;
import com.nieyue.bean.Role;
import com.nieyue.dto.StateResult;
import com.nieyue.service.ManagerService;
import com.nieyue.service.RoleService;
import com.nieyue.util.MyDESutil;

/**
 * 管理控制类
 * @author yy
 *
 */
@Controller("managerController")
@RequestMapping("/manager")
public class ManagerController {
	@Resource
	private ManagerService managerService;
	@Resource
	private RoleService roleService;
	
	/**
	 * 分页
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Manager> browsePagingMer(
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="roleId",required=false) Integer roleId,
			@RequestParam(value="orderName",required=false,defaultValue="manager_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay,HttpSession session)  {
			List<Manager> list = new ArrayList<Manager>();
				list= managerService.browsePagingManager(roleId,pageNum, pageSize, orderName, orderWay);
				return list;
	}
	/**
	 * 增加
	 * @return
	 */
	@RequestMapping(value="/add",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addManager(@ModelAttribute Manager manager,HttpSession session){
		List<String> l = managerService.browseAllManagerPhone();
		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			if(s!=null&&s.equals(manager.getManagerPhone())){
				return StateResult.getSlefSR(40002, "手机号已经存在");
			}
		}
		List<String> le = managerService.browseAllManagerEmail();
		for (int i = 0; i < le.size(); i++) {
			String se = le.get(i);
			if(se!=null&&se.equals(manager.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		manager.setRoleId(1001);
		manager.setManagerPassword(MyDESutil.getMD5(manager.getManagerPassword()));
		boolean b = managerService.addManager(manager);
		return StateResult.getSR(b);
	}
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Manager login(@ModelAttribute Manager manager,HttpSession session){
		Manager m = managerService.managerLogin(manager.getManagerPhone(), MyDESutil.getMD5(manager.getManagerPassword()));
		if(m!=null&&!m.equals("")){
			m.setLastLoginDate(new Date());
			boolean b = managerService.updateManager(m);
			if(b){
			session.setAttribute("manager", m);
			Integer roleId = m.getRoleId();
			Role r = roleService.loadRole(roleId);
			session.setAttribute("role", r);
			}
		}
		return m;
	}
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value="/loginOut",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult loginOut(HttpSession session){
		if(session.getAttribute("manager")!=null&&!session.getAttribute("manager").equals("") ){
			session.removeAttribute("manager");
			
			return StateResult.getSuccess();
		}
		return StateResult.getFail();
	}
	/**
	 * 是否登录
	 * @return
	 */
	@RequestMapping(value="/isLogin",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Manager isLogin(HttpSession session){
		if(session.getAttribute("manager")!=null&&!session.getAttribute("manager").equals("") ){
			return (Manager) session.getAttribute("manager");
		}
		return null;
	}
	/**
	 * 浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="roleId",required=false) Integer roleId,
			HttpSession session)  {
		int count = managerService.countAll(roleId);
		return count;
	}
	
}
