package com.example.supermarket.domain.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supermarket.domain.model.MItem;
import com.example.supermarket.domain.repository.goods.GoodsRepository;

import jakarta.transaction.Transactional;

/**
 * 商品サービス.
 * 2023.1.17
 * 
 * @author Murahgaki
 *
 */
@Service
@Transactional
public class GoodsService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public List<MItem> findGoods() {
		return goodsRepository.findAllByEnableflagFalseOrderByItemcode();
	}
	
	public List<MItem> findGoodsAll() {
		return goodsRepository.findAllByOrderByItemcode();
	}

	public MItem findItem(String itemcode) {
		return goodsRepository.findById(itemcode).get();
	}
	
	public void itemregist(MItem item) {
		goodsRepository.save(item);
	}
}
