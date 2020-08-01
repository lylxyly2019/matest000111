package com.itheima;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

public class Test01 {
    @Test
    public void show01(){
        try {
            //创建是被重连策略RetryPolicy,ExponentialBackoffRetry
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3,10);
            //创建客户端CuratorFrameworkFactory.newClient
            CuratorFramework curatorFramework =
                    CuratorFrameworkFactory.newClient("127.0.0.1:2181", 3000, 1000, retryPolicy);
            //开启start
            curatorFramework.start();
            //使用API
            //1. 创建一个空节点(a)（只能创建一层节点）
            //curatorFramework.create().forPath("/a");
            //2. 创建一个有内容的b节点（只能创建一层节点）
            //curatorFramework.create().forPath("/b","长胜八百战,武艺天下尊".getBytes());
            //3. 创建持久节点，同时创建多层节点
            //curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/c");
            //4. 创建带有的序号的持久节点
            //curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/d");
            //5. 创建临时节点（客户端关闭，节点消失），设置延时5秒关闭（Thread.sleep(5000)）
            //curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/e");
            //6. 创建临时带序号节点（客户端关闭，节点消失），设置延时5秒关闭（Thread.sleep(5000)）
            //curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/f");
            //修改节点
            //curatorFramework.setData().forPath("/g/gg","gggggg".getBytes());
            //查询节点数据
            //byte[] bytes = curatorFramework.getData().forPath("/g/gg");
            //System.out.println(new String(bytes));
            //删除节点delete
            //curatorFramework.delete().forPath("/a");
            //删除节点,递归
            //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/g");
            //删除节点,断网等异常情况也能删
            curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/b");
            //关闭资源
            //Thread.sleep(5000);
            curatorFramework.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
