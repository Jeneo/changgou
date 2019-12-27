package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandServcie;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandServcie {
    @Autowired
    private BrandMapper brandMapper;


    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public List<Brand> findList(Brand brand) {
        //自定义条件搜索对象
        Example example = createCriteria(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        /**
         * 分页实现
         * 当前页
         * 显示数目
         */
        PageHelper.startPage(page, size);
        List<Brand> brands = brandMapper.selectAll();
        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        /**
         * 分页实现
         * 当前页
         * 显示数目
         */
        int j=10/0;
        PageHelper.startPage(page, size);
        Example example = createCriteria(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装PageInfo
        return new PageInfo<Brand>(brands);
    }

    /**
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Brand brand) {
        brandMapper.insertSelective(brand);
        return brand.getId();
    }

    @Override
    public int update(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteById(Integer id) {
        return brandMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件构建
     *
     * @param brand
     * @return
     */
    public Example createCriteria(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器

        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

}
