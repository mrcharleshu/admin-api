package com.charles.admin.web;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

@RestController
public class SecKillSimulationOpController {
    private final static String takeOrderUrl = "http://127.0.0.1:8080/seckill.html";

    /**
     * 模拟并发下单
     */
    @RequestMapping("/simulationConcurrentTakeOrder")
    public String simulationConcurrentTakeOrder(@RequestParam(required = false) Integer total) throws InterruptedException {
        //httpClient工厂
        final SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        //开100个线程模拟并发秒杀下单
        total = total == null ? 100 : total;
        for (int i = 0; i < total; i++) {
            pushTest(httpRequestFactory, i);
        }
        return "SimulationConcurrentTakeOrder";
    }

    private void pushTest(SimpleClientHttpRequestFactory httpRequestFactory, int i) {
        //购买人姓名
        final String consumerName = "consumer" + i;
        new Thread(() -> {
            ClientHttpRequest request;
            try {
                URI uri = new URI(takeOrderUrl + "?consumer=consumer" + consumerName + "&goodsId=123456&num=1");
                request = httpRequestFactory.createRequest(uri, HttpMethod.POST);
                InputStream body = request.execute().getBody();
                BufferedReader br = new BufferedReader(new InputStreamReader(body));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    result.append(line);//获得页面内容或返回内容
                }
                System.out.println(consumerName + ":" + result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
