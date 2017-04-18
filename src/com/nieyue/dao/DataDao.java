package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Data;

/**
 * 数据数据库接口
 * @author yy
 *
 */
public interface DataDao {
	/** 新增数据*/	
	public boolean addData(Data data) ;	
	/** 删除数据 */	
	public boolean delData(Integer dataId) ;
	/** 更新数据*/	
	public boolean updateData(Data data);
	/** 装载数据 */	
	public Data loadData(Integer dataId);	
	/** 数据总共数目 */	
	public int countAll();	
	/** 分页数据信息 */
	public List<Data> browsePagingData(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
