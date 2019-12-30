package com.changgou.service.impl;

import com.changgou.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;


    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    @Override
    public List<Album> findList(Album album) {
        //自定义条件搜索对象
        Example example = createCriteria(album);
        return albumMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        /**
         * 分页实现
         * 当前页
         * 显示数目
         */
        PageHelper.startPage(page, size);
        List<Album> albums = albumMapper.selectAll();
        //封装PageInfo
        return new PageInfo<Album>(albums);
    }

    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
        /**
         * 分页实现
         * 当前页
         * 显示数目
         */
        int j=10/0;
        PageHelper.startPage(page, size);
        Example example = createCriteria(album);
        List<Album> albums = albumMapper.selectByExample(example);
        //封装PageInfo
        return new PageInfo<Album>(albums);
    }

    /**
     * @return
     */
    @Override
    public Album findById(Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Album Album) {
        albumMapper.insertSelective(Album);
      
    }

    @Override
    public void update(Album album) {
         albumMapper.updateByPrimaryKeySelective(album);
    }


    @Override
    public void deleteById(Integer id) {
         albumMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件构建
     *
     * @param
     * @return
     */
    public Example createCriteria(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器

        if (album != null) {
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id",  album.getId() );
            }
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andEqualTo("title",  album.getTitle() );
            }
        }
        return example;
    }

}
