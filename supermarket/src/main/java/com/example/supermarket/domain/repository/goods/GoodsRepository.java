package com.example.supermarket.domain.repository.goods;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.MItem;

/**
 * 商品リポジトリインターフェース.
 * 
 * @author Murahgaki
 *
 */
public interface GoodsRepository extends JpaRepository<MItem, String> {
	List<MItem> findAllByOrderByItemcode();

}
