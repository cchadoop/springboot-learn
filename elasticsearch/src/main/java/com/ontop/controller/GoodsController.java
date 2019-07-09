package com.ontop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontop.entity.GoodsInfo;
import com.ontop.repository.GoodsRepository;
import com.ontop.service.GoodsService;

@RestController
public class GoodsController {

	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
	private GoodsService goodsService;

	// http://localhost:8888/save
	@GetMapping("save")
	public String save() {
		//GoodsInfo goodsInfo = new GoodsInfo(3l, "华为手机", "价格3888元");
		//GoodsInfo goodsInfo = new GoodsInfo(5l, "苹果手机", "价格7888元");
		GoodsInfo goodsInfo = new GoodsInfo(6l, "权重测试", "苹果手机");
		goodsRepository.save(goodsInfo);
		return "success";
	}

	// http://localhost:8888/delete?id=1525415333329
	@GetMapping("delete")
	public String delete(long id) {
		goodsRepository.deleteById(id);
		return "success";
	}

	// http://localhost:8888/update?id=1525417362754&name=修改&description=修改
	@GetMapping("update")
	public String update(long id, String name, String description) {
		GoodsInfo goodsInfo = new GoodsInfo(id, name, description);
		goodsRepository.save(goodsInfo);
		return "success";
	}
	// http://localhost:8888/queryBypage?queryContent=商品&currentPage=1&pageSize=10

	@GetMapping("queryAll")
	public Iterable<GoodsInfo> queryAll() {
		Iterable<GoodsInfo> iterable = goodsRepository.findAll();
		return iterable;
	}

	@GetMapping("searchGoods")
	public List<GoodsInfo> searchGoods(Integer pageNumber, Integer pageSize, String searchContent) {
		return goodsService.search(pageNumber,pageSize,searchContent);
	}
}
