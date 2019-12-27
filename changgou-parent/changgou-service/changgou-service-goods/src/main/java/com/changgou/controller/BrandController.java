package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandServcie;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/brand")
/**
 * 什么是跨域
 * 当一个请求url的协议、域名、端口三者之间任意一个与当前页面url不同即为跨域
 *
 * 当前页面url	被请求页面url	是否跨域	原因
 * http://www.test.com/	http://www.test.com/index.html	否	同源（协议、域名、端口号相同）
 * http://www.test.com/	https://www.test.com/index.html	跨域	协议不同（http/https）
 * http://www.test.com/	http://www.baidu.com/	跨域	主域名不同（test/baidu）
 * http://www.test.com/	http://blog.test.com/	跨域	子域名不同（www/blog）
 * http://www.test.com:8080/	http://www.test.com:7001/	跨域	端口号不同（8080/7001）
 */
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandServcie brandServcie;

    /**
     * 条件查询
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brands = brandServcie.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "根据条件查询品牌成功", brands);
    }

    /**
     * 根据主键查询品牌
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandServcie.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询品牌成功", brand);
    }

    /**
     * 查询所有品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandServcie.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功", brands);
    }

    /**
     * 分页查询
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable Integer page,
                                            @PathVariable Integer size) {
        PageInfo<Brand> pageInfo = brandServcie.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "查询品牌集合分页成功", pageInfo);
    }

    /**
     * 分页+条件查询
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable Integer page,
                                            @PathVariable Integer size) {
        PageInfo<Brand> pageInfo = brandServcie.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "根据条件查询品牌分页集合成功", pageInfo);
    }


    /**
     * 增加品牌,并返回增加后的id
     */
    @PostMapping
    public Result<Integer> insert(@RequestBody Brand brand) {
        int id = brandServcie.add(brand);
        return new Result<Integer>(true, StatusCode.OK, "增加品牌成功!", id);
    }

    /**
     * 更新品牌
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandServcie.update(brand);
        return new Result(true, StatusCode.OK, "修改品牌成功!", null);
    }

    /**
     * 删除品牌
     */
    @DeleteMapping("/{id}")
    public Result deleteByid(@PathVariable("id") Integer id) {
        brandServcie.deleteById(id);
        return new Result(true, StatusCode.OK, "删除品牌成功!", null);
    }


}

