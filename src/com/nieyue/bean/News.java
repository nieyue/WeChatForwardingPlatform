package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 新闻类
 * @author yy
 *
 */
public class News implements Serializable {
	
	private static final long serialVersionUID = 8198930199550185349L;
	private Integer newsId;
	private String type;
	private String title;
	private Integer isRecommend;//是否推荐 默认否
	private Integer fixedRecommend;//是否置顶推荐 默认否
	private Date time;
	private String imgAddress;
	private String content;
	private Double unitPrice;
	private Double totalPrice;
	private Integer readingNumber;
	private Double nowTotalPrice;
	private Double unitProfit;
	private Double nowTotalProfit;
	private Double scale;
	private Long pvs;
	private Long uvs;
	private Long ips;
	private String status;
	private Integer managerId;
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getFixedRecommend() {
		return fixedRecommend;
	}
	public void setFixedRecommend(Integer fixedRecommend) {
		this.fixedRecommend = fixedRecommend;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getReadingNumber() {
		return readingNumber;
	}
	public void setReadingNumber(Integer readingNumber) {
		this.readingNumber = readingNumber;
	}
	public Double getNowTotalPrice() {
		return nowTotalPrice;
	}
	public void setNowTotalPrice(Double nowTotalPrice) {
		this.nowTotalPrice = nowTotalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public News() {
		super();
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
	public Double getUnitProfit() {
		return unitProfit;
	}
	public void setUnitProfit(Double unitProfit) {
		this.unitProfit = unitProfit;
	}
	public Double getNowTotalProfit() {
		return nowTotalProfit;
	}
	public void setNowTotalProfit(Double nowTotalProfit) {
		this.nowTotalProfit = nowTotalProfit;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public News(Integer newsId, String type, String title, Integer isRecommend, Integer fixedRecommend, Date time,
			String imgAddress, String content, Double unitPrice, Double totalPrice, Integer readingNumber,
			Double nowTotalPrice, Double unitProfit, Double nowTotalProfit, Double scale, Long pvs, Long uvs, Long ips,
			String status, Integer managerId) {
		super();
		this.newsId = newsId;
		this.type = type;
		this.title = title;
		this.isRecommend = isRecommend;
		this.fixedRecommend = fixedRecommend;
		this.time = time;
		this.imgAddress = imgAddress;
		this.content = content;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.readingNumber = readingNumber;
		this.nowTotalPrice = nowTotalPrice;
		this.unitProfit = unitProfit;
		this.nowTotalProfit = nowTotalProfit;
		this.scale = scale;
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.status = status;
		this.managerId = managerId;
	}
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", type=" + type + ", title=" + title + ", isRecommend=" + isRecommend
				+ ", fixedRecommend=" + fixedRecommend + ", time=" + time + ", imgAddress=" + imgAddress + ", content="
				+ content + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", readingNumber="
				+ readingNumber + ", nowTotalPrice=" + nowTotalPrice + ", unitProfit=" + unitProfit
				+ ", nowTotalProfit=" + nowTotalProfit + ", scale=" + scale + ", pvs=" + pvs + ", uvs=" + uvs + ", ips="
				+ ips + ", status=" + status + ", managerId=" + managerId + "]";
	}

	
}
