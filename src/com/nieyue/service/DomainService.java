package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Domain;

/**
 * 域名逻辑层接口
 * @author yy
 *
 */
public interface DomainService {
	/** 新增域名 */	
	public boolean addDomain(Domain Domain) ;	
	/** 删除域名 */	
	public boolean delDomain(Integer DomainId) ;
	/** 更新域名*/	
	public boolean updateDomain(Domain Domain);
	/** 装载域名 */	
	public Domain loadDomain(Integer DomainId);	
	/** 域名总共数目 */	
	public int countAll(String status);
	/** 分页域名信息 */
	public List<Domain> browsePagingDomain(String status,int pageNum,int pageSize,String orderName,String orderWay) ;
}
