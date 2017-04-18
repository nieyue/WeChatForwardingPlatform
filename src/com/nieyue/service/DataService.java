package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Data;

/**
 * 数据逻辑层接口
 * @author yy
 *
 */
public interface DataService {
	/** 新增数据 */	
	public boolean addData(Data Data) ;	
	/** 删除数据 */	
	public boolean delData(Integer DataId) ;
	/** 更新数据*/	
	public boolean updateData(Data Data);
	/** 装载数据 */	
	public Data loadData(Integer DataId);	
	/** 数据总共数目 */	
	public int countAll();
	/** 分页数据信息 */
	public List<Data> browsePagingData(int pageNum,int pageSize,String orderName,String orderWay) ;
}
