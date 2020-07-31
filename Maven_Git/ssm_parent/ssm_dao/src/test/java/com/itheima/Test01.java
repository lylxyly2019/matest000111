package com.itheima;

import com.itheima.dao.ItemsDao;
import org.apache.ibatis.io.ResolverUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void show01() {
    ApplicationContext applicationContext=
            new ClassPathXmlApplicationContext("spring-mybatis.xml");
        ItemsDao itemsDao = applicationContext.getBean(ItemsDao.class);
        System.out.println("商品列表dao:"+itemsDao.findAll());
    }
}
