package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import entity.Page;

import java.util.List;

public interface BrandServcie {

    /**
     * 查询所有
     */
    List<Brand> findAll();

    /**
     * 根据条件查询
     */

    List<Brand> findList(Brand brand);

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Integer page, Integer size);


    /**
     * 分页+条件查询
     *
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);


    /**
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 增加品牌
     */
    int add(Brand brand);

    /**
     * 修改品牌
     */
    int update(Brand brand);

    /**
     * 删除品牌
     */
    int deleteById(Integer id);


}
