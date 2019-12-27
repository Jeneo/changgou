package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "Brand",value = "Brand")
@Table(name = "tb_brand")
public class Brand implements Serializable {
    @ApiModelProperty(value = "品牌id",required = false)
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "JDBC")//此处加上注解,插入后数据后会把id回填
    private Integer id;//品牌id

    @ApiModelProperty(value = "品牌名称",required = false)
    @Column(name="name")
    private String name;//品牌名称

    @ApiModelProperty(value = "品牌图片地址",required = false)
    @Column(name="image")
    private String image;//品牌图片地址

    @ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name="letter")
    private String letter;//品牌的首字母

    @ApiModelProperty(value = "排序",required = false)
    @Column(name="seq")
    private Integer seq;//排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
