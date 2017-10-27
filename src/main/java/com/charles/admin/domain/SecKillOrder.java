package com.charles.admin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SecKillOrder implements Serializable {
    @Id
    @GenericGenerator(name = "PKUUID", strategy = "uuid2")
    @GeneratedValue(generator = "PKUUID")
    @Column(length = 36)
    private String id;
    //用户名称
    private String consumer;
    //秒杀产品编号
    private String goodsId;
    //购买数量
    private Integer num;

    public SecKillOrder() {
    }

    public SecKillOrder(final String consumer, final String goodsId, final Integer num) {
        this.consumer = consumer;
        this.goodsId = goodsId;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(final String consumer) {
        this.consumer = consumer;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(final String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(final Integer num) {
        this.num = num;
    }
}