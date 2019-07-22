package com.ontop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.ontop.entity.UserInfo;
import com.ontop.service.UserService;

/*
 * MongoTemplate的使用
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveUser(UserInfo user) {
		mongoTemplate.save(user);
	}

	@Override
	public DeleteResult deleteUser(Long id) {
		CriteriaDefinition criteria = Criteria.where("id").is(id);
		Query query = Query.query(criteria);
		return mongoTemplate.remove(query, UserInfo.class);
	}

	@Override
	public UpdateResult modifyUser(UserInfo user) {
		CriteriaDefinition criteria = Criteria.where("id").is(user.getId());
		Query query = Query.query(criteria);
		Update update = Update.update("username", user.getUsername());
		update.set("password", user.getPassword());
		// 更新第一个文档
		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, UserInfo.class);
		// mongoTemplate.updateMulti(query, update, UserInfo.class);
		return updateFirst;
	}

	@Override
	public List<UserInfo> findAll(UserInfo user) {
		List<UserInfo> list = mongoTemplate.findAll(UserInfo.class);
		return list;
	}

	@Override
	public UserInfo findById(Long id) {
		// CriteriaDefinition criteria = Criteria.where("id").is(id);
		// Query query = Query.query(criteria);
		// UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
		UserInfo userInfo = mongoTemplate.findById(id, UserInfo.class);
		return userInfo;
	}

}
