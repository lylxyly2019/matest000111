package com.itheima;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class Test03 {
    @Test
    public void show01() {
        try {
            //创建重连策略
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3, 1000);
            //创建客户端
            CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 1000, 1000, retryPolicy);
            //启动客户端
            curatorFramework.start();
            //创建监听对象
            TreeCache treeCache = new TreeCache(curatorFramework, "/g");
            //启动监听对象
            treeCache.start();
            //添加监听对象
            treeCache.getListenable().addListener(new TreeCacheListener() {
                public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                    //输出监听信息
                    if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.NODE_ADDED)) {
                        System.out.println("添加节点");
                        System.out.println("节点:" + treeCacheEvent.getData().getPath());
                        System.out.println("数据:" + treeCacheEvent.getData().getData());
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.NODE_UPDATED)) {
                        System.out.println("修改节点");
                        System.out.println("节点:" + treeCacheEvent.getData().getPath());
                        System.out.println("数据:" + treeCacheEvent.getData().getData());
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.NODE_REMOVED)) {
                        System.out.println("删除节点");
                        System.out.println("节点:" + treeCacheEvent.getData().getPath());
                        System.out.println("数据:" + treeCacheEvent.getData().getData());
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.CONNECTION_SUSPENDED)) {
                        System.out.println("连接超时");
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.CONNECTION_RECONNECTED)) {
                        System.out.println("重新连接");
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.CONNECTION_LOST)) {
                        System.out.println("连接失效");
                    } else if (treeCacheEvent.getType().equals(TreeCacheEvent.Type.INITIALIZED)) {
                        System.out.println("初始化操作");
                    }
                }
            });
            //线程等待
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
