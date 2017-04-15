package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 域名类
 * 
 * @author yy
 * 
 */
public class Domain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 域名id
	 */
	private Integer domainId;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private String status;

	/**
	 *创建时间
	 */
	private Date createDate;
	/**
	 *停用时间
	 */
	private Date stopDate;
	public Domain() {
		super();
	}
	public Domain(Integer domainId, String name, String status, Date createDate, Date stopDate) {
		super();
		this.domainId = domainId;
		this.name = name;
		this.status = status;
		this.createDate = createDate;
		this.stopDate = stopDate;
	}
	public Integer getDomainId() {
		return domainId;
	}
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
