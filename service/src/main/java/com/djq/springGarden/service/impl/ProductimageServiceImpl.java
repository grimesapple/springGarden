package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ProductimageMapper;
import com.djq.springGarden.entity.Productimage;
import com.djq.springGarden.service.ProductimageService;


import javax.annotation.Resource;


/**
 * 客房图片Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class ProductimageServiceImpl implements ProductimageService {
    @Resource
    private ProductimageMapper productimageMapper;

    /**
     * 条件查询客房图片列表
     *
     * @param productimage  客房图片
     * @return 客房图片
     */
    @Override
    public List<Productimage> select(Productimage productimage) {
        return productimageMapper.select(productimage);
    }

    /**
     * 条件查询客房图片单个
     *
     * @param productimage 客房图片
     * @return 客房图片
     */
    @Override
    public Productimage selectOne(Productimage productimage) {
        return productimageMapper.selectOne(productimage);
    }

    /**
     * 新增客房图片
     *
     * @param productimage 客房图片
     * @return 结果
     */
    @Override
    public int insertProductimage(Productimage productimage) {
        return productimageMapper.insert(productimage);
    }

    /**
     * 修改客房图片
     *
     * @param productimage 客房图片
     * @return 结果
     */
    @Override
    public int updateProductimage(Productimage productimage) {
        return productimageMapper.updateByPrimaryKey(productimage);
    }

    /**
     * 删除客房图片信息
     *
     * @param id 客房图片主键
     * @return 结果
     */
    @Override
    public int deleteProductimageById(Integer id) {

        Productimage productimage = new Productimage();
        productimage.setId(id);
        return productimageMapper.delete(productimage);
    }

}
