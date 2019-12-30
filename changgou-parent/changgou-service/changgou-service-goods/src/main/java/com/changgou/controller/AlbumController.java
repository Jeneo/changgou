package com.changgou.controller;

import com.changgou.goods.pojo.Album;
import com.changgou.service.AlbumService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/album")

@CrossOrigin
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    /**
     * 条件查询
     */
    @PostMapping(value = "/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        List<Album> brands = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "根据条件查询相册成功", brands);
    }

    /**
     * 根据主键查询相册
     */
    @GetMapping(value = "/{id}")
    public Result<Album> findById(@PathVariable("id") Integer id) {
        Album album = albumService.findById(id);
        return new Result<Album>(true, StatusCode.OK, "查询相册成功", album);
    }

    /**
     * 查询所有相册
     */
    @GetMapping
    public Result<List<Album>> findAll() {
        List<Album> brands = albumService.findAll();
        return new Result<List<Album>>(true, StatusCode.OK, "查询相册集合成功", brands);
    }

    /**
     * 分页查询
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@PathVariable Integer page,
                                            @PathVariable Integer size) {
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<PageInfo<Album>>(true, StatusCode.OK, "查询相册集合分页成功", pageInfo);
    }


    /**
     * 增加相册,并返回增加后的id
     */
    @PostMapping
    public Result<Integer> insert(@RequestBody Album album) {
        albumService.add(album);
        return new Result<Integer>(true, StatusCode.OK, "增加相册成功!", null);
    }

    /**
     * 更新相册
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Album album) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改相册成功!", null);
    }

    /**
     * 删除相册
     */
    @DeleteMapping("/{id}")
    public Result deleteByid(@PathVariable("id") Integer id) {
        albumService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除相册成功!", null);
    }


}

