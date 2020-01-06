/**
 * 公司：DLUT
 * 文件名：CacheUtil
 * 作者：haibing
 * 时间：2020/1/4 19:17
 * 描述：
 */

package com.hundsun.xone.ssm.cache;

public class CacheUtil {
    /**
     * 缓存管理器，主要执行缓存操作
     */
    private static CacheManager cacheManager;
    /**
     * 锁管理器，主要执行加锁与解锁操作
     */
    private static CacheManager lockManager;

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    public static void setLockManager(CacheManager cacheManager) {
        CacheUtil.lockManager = cacheManager;
    }

    public static CacheManager getCache() {
        return cacheManager;
    }

    public static CacheManager getLockManager() {
        return lockManager;
    }

    /** 获取锁 */
    public static boolean tryLock(String key) {
        int expires = 1000 * PropertiesFileUtil.getInstance("config/redis.properties").getInt("redis.lock.expires", 180);
        return lockManager.setnx(key, expires);
    }

    /** 获取锁 */
    public static boolean getLock(String key) {
        return lockManager.lock(key);
    }

    /** 解锁 */
    public static void unlock(String key) {
        lockManager.unlock(key);
    }
}