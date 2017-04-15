package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Manager;

/**
 * 管理接口
 * @author yy
 *
 */
public interface ManagerDao {
	/** 账户登录 */
	public Manager managerLogin(@Param("name")String name,@Param("password")String password);	
	/** 增加账户*/
	public boolean addManager(Manager manager);	
	/** 更新账户*/
	public boolean updateManager(Manager manager);	
	/** 分页账户信息 */
	public List<Manager> browsePagingManager(@Param("roleId")Integer roleId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 所有登录手机号 */	
	public List<String> browseAllManagerPhone();
	/** manager总共数目 */	
	public int countAll(@Param("roleId")Integer roleId);	
}
