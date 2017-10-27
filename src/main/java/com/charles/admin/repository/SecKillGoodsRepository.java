package com.charles.admin.repository;

import com.charles.admin.domain.SecKillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * 根据多线程编程的规范，提倡对共享资源加锁，在最有可能出现并发争抢的情况下加同步块的思想。应该同一时刻只有一个线程去减少库存。
 * 但是这里给出一个最好的方案，就是利用Oracle,Mysql的行级锁–同一时间只有一个线程能够操作同一行记录，对SecKillGoodsDao进行改造：
 */
public interface SecKillGoodsRepository extends JpaRepository<SecKillGoods, String> {
    //    @Query("update SecKillGoods g set g.remainNum = g.remainNum - ?2 where g.id=?1")
    @Query("update SecKillGoods g set g.remainNum = g.remainNum - ?2 where g.id=?1 and g.remainNum>0")
    @Modifying(clearAutomatically = true)
    @Transactional
    int reduceStock(String id, Integer remainNum);
}