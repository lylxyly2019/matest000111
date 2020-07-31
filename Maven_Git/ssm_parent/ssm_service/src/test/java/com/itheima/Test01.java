package com.itheima;

import com.itheima.dao.ItemsDao;
import com.itheima.service.ItemsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void show01() {
    ApplicationContext applicationContext=
            new ClassPathXmlApplicationContext("spring-service.xml");
        ItemsService itemsService = applicationContext.getBean(ItemsService.class);
        System.out.println("商品列表service:"+itemsService.findAll());
    }
}
