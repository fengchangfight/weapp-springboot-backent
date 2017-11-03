package com.xiaochengxu.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author xiefengchang
 *
 */
@Entity
@Table(name="app_key")
public class AppKey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**分发的应用ID*/
	private String appId;
	/**密钥*/	
	private String secretKey;
	/**创建日期*/	
	private Date createDate;
	/**有效截止日期，为2030-01-01 00：00:00表示无日期限制*/	
	private Date validDate;
	/**应用权限等级*/	
	private String appGrade;
	/**是否禁用*/	
	private Boolean disabled;

	

	public AppKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppKey(String appId, String secretKey, Date createDate, Date validDate, String appGrade, Boolean disabled) {
		super();
		this.appId = appId;
		this.secretKey = secretKey;
		this.createDate = createDate;
		this.validDate = validDate;
		this.appGrade = appGrade;
		this.disabled = disabled;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getAppGrade() {
		return appGrade;
	}

	public void setAppGrade(String appGrade) {
		this.appGrade = appGrade;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
}
