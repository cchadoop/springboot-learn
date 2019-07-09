package com.ontop.service;

import java.util.List;

import com.ontop.entity.GoodsInfo;

public interface GoodsService {

	List<GoodsInfo> search(Integer pageNumber, Integer pageSize, String searchContent);

}
