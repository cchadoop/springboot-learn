package com.ontop.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "user")
// Hibernate获取单个实体类数据后，会为每个实体类添加一个 hibernateLazyInitializer 属性，改属性在进行JSON转换时抛出异常
@JsonIgnoreProperties("hibernateLazyInitializer")
public class User implements Serializable {
	private static final long serialVersionUID = -2756690245381121543L;
	@ApiModelProperty(value = "主键id", hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@ApiModelProperty(value = "用户名称")
	@NotNull
	@Column
	private String userName;
	@ApiModelProperty(value = "用户密码")
	@Column
	private String userPassword;

	public User() {
		super();
	}

	public User(Integer id, @NotNull String userName, String userPassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
