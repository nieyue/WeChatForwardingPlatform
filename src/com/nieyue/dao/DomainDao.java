package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Domain;

/**
 * 域名数据库接口
 * @author yy
 *
 */
public interface DomainDao {
	/** 新增域名*/	
	public boolean addDomain(Domain Domain) ;	
	/** 删除域名 */	
	public boolean delDomain(Integer DomainId) ;
	/** 更新域名*/	
	public boolean updateDomain(Domain Domain);
	/** 装载域名 */	
	public Domain loadDomain(Integer DomainId);	
	/** 域名总共数目 */	
	public int countAll(@Param("status")String status);	
	/** 分页域名信息 */
	public List<Domain> browsePagingDomain(@Param("status")String status,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
