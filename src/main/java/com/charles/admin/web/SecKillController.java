package com.charles.admin.web;

import com.charles.admin.domain.SecKillGoods;
import com.charles.admin.repository.SecKillGoodsRepository;
import com.charles.admin.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class SecKillController {
    private static int orderRequestCount = 0;
    private static int orderedGoodsCount = 0;
    @Autowired
    private SecKillGoodsRepository secKillGoodsRepository;
    @Autowired
    private SecKillService secKillService;

    /**
     * 普通写法
     *
     * @return
     */
    @RequestMapping("/seckill.html")
    //public synchronized String SecKill(String consumer, String goodsId, Integer num) throws InterruptedException {
    public String SecKill(String consumer, String goodsId, Integer num) throws InterruptedException {
        orderRequestCount++;
        System.out.println(String.format("### [%s]==第[%s]个抢购", LocalTime.now(), orderRequestCount));
        long startMillis = System.currentTimeMillis();
        //查找出用户要买的商品
        SecKillGoods goods = secKillGoodsRepository.findOne(goodsId);
        //如果有这么多库存
        if (goods.getRemainNum() >= num) {
            //模拟网络延时
            Thread.sleep(10000);
            // return controllerMethodSyncLock(consumer, goodsId, num);
            return repositorySqlLock(consumer, goodsId, num, goods);
        }
        System.out.println("Method cost millis = " + (System.currentTimeMillis() - startMillis));
        return "购买失败,库存不足";
    }

    private String repositorySqlLock(final String consumer, final String goodsId,
            final Integer num, final SecKillGoods goods) {
        if (goods.getRemainNum() > 0) {
            //先减去库存
            int modifyLines = secKillGoodsRepository.reduceStock(goodsId, num);
            if (modifyLines != 0) {
                orderedGoodsCount++;
                System.out.println(String.format("### [%s]==第[%s]个商品被抢完", LocalTime.now(), orderedGoodsCount));
                //保存订单
                secKillService.generateOrder(consumer, goodsId, num);
                return "购买成功";
            } else {
                return "购买失败,库存不足";
            }
        } else {
            return "购买失败,库存不足";
        }
    }

    private String controllerMethodSyncLock(final String consumer, final String goodsId, final Integer num) {
        //先减去库存
        secKillGoodsRepository.reduceStock(goodsId, num);
        //保存订单
        secKillService.generateOrder(consumer, goodsId, num);
        return "购买成功";
    }
}
