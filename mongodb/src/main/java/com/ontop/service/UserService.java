package com.ontop.service;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.ontop.entity.UserInfo;

public interface UserService {
	void saveUser(UserInfo user);

	DeleteResult deleteUser(Long id);

	UpdateResult modifyUser(UserInfo user);

	List<UserInfo> findAll(UserInfo user);

	UserInfo findById(Long id);
}
