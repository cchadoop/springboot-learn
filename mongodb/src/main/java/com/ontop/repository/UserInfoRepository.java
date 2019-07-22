package com.ontop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ontop.entity.UserInfo;

public interface UserInfoRepository
		extends MongoRepository<UserInfo, Long>, PagingAndSortingRepository<UserInfo, Long> {

}
