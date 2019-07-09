package com.ontop.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.ontop.entity.GoodsInfo;

@Repository
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long>{
	

}
