package com.djq.springGarden.mapper;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.general.GeneralDAO;

import java.util.List;
import java.util.Map;

public interface ProductMapper extends GeneralDAO<Product> {
//    List<Map<String,Object>> selectAllMap();
    /**
     * 根据时间进行查询
     */
    List<Product> searchAllByTime(Product product);
}