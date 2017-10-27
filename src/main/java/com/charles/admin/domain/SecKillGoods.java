package com.charles.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SecKillGoods implements Serializable {
    @Id
    private String id;
    /**
     * 剩余库存
     */
    private Integer remainNum;
    /**
     * 秒杀商品名称
     */
    private String goodsName;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(final Integer remainNum) {
        this.remainNum = remainNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(final String goodsName) {
        this.goodsName = goodsName;
    }
}