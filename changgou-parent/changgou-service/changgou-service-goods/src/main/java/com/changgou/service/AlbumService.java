package com.changgou.service;

import com.changgou.goods.pojo.Album;
import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();

    List<Album> findList(Album album);

    PageInfo<Album> findPage(Integer page, Integer size);

    PageInfo<Album> findPage(Album Album, Integer page, Integer size);

    Album findById(Integer id);

    void add(Album Album);

    void update(Album album);

    void deleteById(Integer id);
}
