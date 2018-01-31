package com.abin.lee.curator.logic.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: tinkpad
 * Date: 16-5-28
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
public class CuratorWatcher {
    private static Cache<String, String> zkCache = CacheBuilder.newBuilder().build();
    private static CuratorFramework curatorFramework = null;
    private static final String zkPath = "/curator/zknode1";
    private static final String zkFatherPath = "/curator";
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final String ADDRESS = "172.16.2.146:2181";

    public static void main(String[] args) throws Exception {
        init();
        monitorExchange();
        monitorChildExchange();
        while(true){
            Thread.sleep(10000L);
        }
    }
    public static void init() throws Exception {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ADDRESS)
                .sessionTimeoutMs(20000)
                .connectionTimeoutMs(30000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();

//        String data = "my test";
//        curatorFramework.create().creatingParentsIfNeeded()
//                .forPath(zkFatherPath, data.getBytes("UTF-8"));
    }

    public static void monitorExchange() throws Exception {
        final NodeCache nodeCache = new NodeCache(curatorFramework, zkPath);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Node data is changed, new data: " +
                        new String(nodeCache.getCurrentData().getData()));
            }
        },
                executorService
        );
    }

    public static void monitorChildExchange() throws Exception {
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, zkFatherPath, true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("zkCache"+zkCache.asMap());
                switch(event.getType()){
                    case CHILD_ADDED:
                        zkCache.put(event.getData().getPath(), event.getType().name());
                        System.out.println("CHILD_ADDED: " + event.getData().getPath());
                        break;
                    case  CHILD_REMOVED:
                        zkCache.invalidate(event.getData().getPath());
                        System.out.println("CHILD_REMOVED: " + event.getData().getPath());
                        break;
                    case  CHILD_UPDATED:
                        zkCache.put(event.getData().getPath(), event.getType().name());
                        System.out.println("CHILD_UPDATED: " + event.getData().getPath());
                        break;
                    case  CONNECTION_LOST:
                        System.out.println("CONNECTION_LOST: " + event.getData().getPath());
                        break;
                    case  CONNECTION_RECONNECTED:
                        System.out.println("CONNECTION_RECONNECTED: " + event.getData().getPath());
                        break;
                    case CONNECTION_SUSPENDED:
                        System.out.println("CONNECTION_SUSPENDED: " + event.getData().getPath());
                        break;
                    default:
                        System.out.println("default: " );
                        break;
                }
            }
        },
        executorService);
    }

}
