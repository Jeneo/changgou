package com.changgou.dao;


import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

/**
 * dao 使用通用mapper
 * 需要基础tk.mapper
 *  增加数据,直接调用Mapper.insert() ,insertSelective();
 *  修改数据Mapper.update(T),updateByPrimaryKey
 *
 */
public interface AlbumMapper extends Mapper<Album> {
}
