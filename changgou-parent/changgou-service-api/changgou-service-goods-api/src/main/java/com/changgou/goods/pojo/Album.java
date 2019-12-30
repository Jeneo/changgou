package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(description = "Album",value = "Album")
@Table(name = "tb_album")
public class Album {
    @ApiModelProperty(value = "相册id",required = false)
    @Id
    @Column(name="id")
    private Integer id;

    @ApiModelProperty(value = "相册名称",required = false)
    @Column(name="title")
    private String  title;

    @ApiModelProperty(value = "相册图片路径",required = false)
    @Column(name="image")
    private  String image;

    @ApiModelProperty(value = "相册图片明细路径",required = false)
    @Column(name="image_items")
    private String imageItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageItems() {
        return imageItems;
    }

    public void setImageItems(String imageItems) {
        this.imageItems = imageItems;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imageItems='" + imageItems + '\'' +
                '}';
    }
}
