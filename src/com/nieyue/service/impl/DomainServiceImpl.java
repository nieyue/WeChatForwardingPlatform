package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Domain;
import com.nieyue.dao.DomainDao;
import com.nieyue.service.DomainService;
@Service
public class DomainServiceImpl implements DomainService{
	@Resource
	DomainDao domainDao;
	@Override
	public boolean addDomain(Domain domain) {
		domain.setCreateDate(new Date());
		domain.setStatus("正常");
		boolean b = domainDao.addDomain(domain);
		return b;
	}
	@Override
	public boolean delDomain(Integer domainId) {
		boolean b = domainDao.delDomain(domainId);
		return b;
	}
	@Override
	public boolean updateDomain(Domain domain) {
		boolean b = domainDao.updateDomain(domain);
		return b;
	}

	@Override
	public Domain loadDomain(Integer domainId) {
		Domain r = domainDao.loadDomain(domainId);
		return r;
	}

	@Override
	public int countAll(String status) {
		int c = domainDao.countAll(status);
		return c;
	}

	@Override
	public List<Domain> browsePagingDomain(String status,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Domain> l = domainDao.browsePagingDomain(status,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
