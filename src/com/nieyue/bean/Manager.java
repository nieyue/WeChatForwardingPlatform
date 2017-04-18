package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员类
 * 
 * @author yy
 * 
 */
public class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 管理员id
	 */
	private Integer managerId;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String managerPhone;
	/**
	 * email
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String managerPassword;
	/**
	 *创建时间
	 */
	private Date createDate;
	/**
	 * 最后登陆时间
	 */
	private Date lastLoginDate;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	public Manager() {
		super();
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Manager(Integer managerId, String name, String managerPhone, String email, String managerPassword,
			Date createDate, Date lastLoginDate, Integer roleId) {
		super();
		this.managerId = managerId;
		this.name = name;
		this.managerPhone = managerPhone;
		this.email = email;
		this.managerPassword = managerPassword;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
		this.roleId = roleId;
	}
	
		
}
