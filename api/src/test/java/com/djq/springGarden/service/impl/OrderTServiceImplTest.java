package com.djq.springGarden.service.impl;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.mapper.ProductMapper;
import com.djq.springGarden.service.OrderService;
import com.djq.springGarden.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTServiceImplTest {
    @Resource
    OrderService orderService;

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductService productService;



    @Test
    void check() {
        Product product = new Product();
        List<Product> select = productMapper.select(product);
        select.sort(Comparator.comparing(Product::getCreateTime));
        System.out.println(select);
        select.sort(Comparator.comparing(Product::getCreateTime, Date::compareTo));

    }
}