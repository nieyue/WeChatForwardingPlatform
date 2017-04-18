package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Manager;
/**
 * 管理接口
 * @author yy
 *
 */
public interface ManagerService {
	/** 账户登录 */
	public Manager managerLogin(String name,String password);	
	/** 增加账户*/
	public boolean addManager(Manager manager);
	/** 更新账户*/
	public boolean updateManager(Manager manager);
	/** 分页账户信息 */
	public List<Manager> browsePagingManager(Integer roleId,int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 所有登录手机号 */	
	public List<String> browseAllManagerPhone();
	/** 所有登录email */	
	public List<String> browseAllManagerEmail();
	/** manager总共数目 */	
	public int countAll(Integer roleId);
}
