package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Manager;
import com.nieyue.dao.ManagerDao;
import com.nieyue.service.ManagerService;

/**
 * 管理业务实现类
 * @author Administrator
 *
 */
@Service
public class ManagerServiceImpl implements ManagerService {
	@Resource
	ManagerDao managerDao;
	/**
	 * 登录
	 * @param manager
	 */
	
	@Override
	public Manager managerLogin(String name, String password) {
		Manager m = managerDao.managerLogin(name, password);
		return m;
	}
	@Override
	public boolean addManager(Manager manager) {
		manager.setCreateDate(new Date());
		manager.setLastLoginDate(new Date());
		boolean b = managerDao.addManager(manager);
		return b;
	}
	@Override
	public List<Manager> browsePagingManager(Integer roleId,int pageNum, int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Manager> l = managerDao.browsePagingManager( roleId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	@Override
	public List<String> browseAllManagerPhone() {
		List<String> l = managerDao.browseAllManagerPhone();
		return l;
	}
	@Override
	public int countAll(Integer roleId) {
		int i = managerDao.countAll(roleId);
		return i;
	}
	@Override
	public boolean updateManager(Manager manager) {
		boolean b = managerDao.updateManager(manager);
		return b;
	}
	@Override
	public List<String> browseAllManagerEmail() {
		List<String> l = managerDao.browseAllManagerEmail();
		return l;
	}
	
}
