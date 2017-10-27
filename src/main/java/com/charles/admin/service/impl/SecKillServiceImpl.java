package com.charles.admin.service.impl;

import com.charles.admin.domain.SecKillGoods;
import com.charles.admin.domain.SecKillOrder;
import com.charles.admin.repository.SecKillGoodsRepository;
import com.charles.admin.repository.SecKillOrderRepository;
import com.charles.admin.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Transactional
@Service
public class SecKillServiceImpl implements SecKillService {
    @Autowired
    private SecKillGoodsRepository secKillGoodsRepository;
    @Autowired
    private SecKillOrderRepository secKillOrderRepository;

    /**
     * 程序启动时：
     * 初始化秒杀商品，清空订单数据
     */
    @PostConstruct
    public void initSecKillEntity() {
        secKillGoodsRepository.deleteAll();
        secKillOrderRepository.deleteAll();
        SecKillGoods secKillGoods = new SecKillGoods();
        secKillGoods.setId("123456");
        secKillGoods.setGoodsName("秒杀产品");
        secKillGoods.setRemainNum(20);
        secKillGoodsRepository.save(secKillGoods);
    }

    /**
     * 购买成功,保存订单
     * @param consumer
     * @param goodsId
     * @param num
     */
    @Override
    public void generateOrder(String consumer, String goodsId, Integer num) {
        secKillOrderRepository.save(new SecKillOrder(consumer, goodsId, num));
    }
}