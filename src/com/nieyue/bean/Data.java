package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据类
 * 
 * @author yy
 * 
 */
public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 数据id
	 */
	private Integer dataId;
	
	/**
	 * pv数
	 */
	private Long pvs;
	/**
	 * uv数
	 */
	private Long uvs;
	
	/**
	 * ip数
	 */
	private Long ips;
	
	/**
	 *创建时间
	 */
	private Date createDate;
	/**
	 * 新闻ID
	 */
	private Integer newsId;
	public Data() {
		super();
	}
	public Data(Integer dataId, Long pvs, Long uvs, Long ips, Date createDate, Integer newsId) {
		super();
		this.dataId = dataId;
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.createDate = createDate;
		this.newsId = newsId;
	}
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public Long getPvs() {
		return pvs;
	}
	public void setPvs(Long pvs) {
		this.pvs = pvs;
	}
	public Long getUvs() {
		return uvs;
	}
	public void setUvs(Long uvs) {
		this.uvs = uvs;
	}
	public Long getIps() {
		return ips;
	}
	public void setIps(Long ips) {
		this.ips = ips;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		
}
