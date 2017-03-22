package me.tongzhuangzhuang.test;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RAtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangtingbang on 2017/3/22.
 */
public class RedisLockProcessor implements Runnable{

  private static Logger log = LoggerFactory.getLogger(RedisLockProcessor.class);

  private static Redisson redisson;
  private static Config config;
  private static String REDIS_SERVER_ADDRESS = "192.168.101.112:6379";
  private static final int REDIS_SERVER_POOL_SIZE = 5;
  public static void init() {
    if (redisson != null) {
      return;
    }
    try {
      config = new Config();

      if (REDIS_SERVER_POOL_SIZE > 0) {
        config.useSingleServer().setAddress(REDIS_SERVER_ADDRESS) // SystemProperties.get("redis.server.address")
          .setConnectionPoolSize(REDIS_SERVER_POOL_SIZE); //
      } else {
        config.useSingleServer().setAddress(REDIS_SERVER_ADDRESS); // SystemProperties.get("redis.server.address")
      }
      redisson = Redisson.create(config);
    } catch (Exception e) {
      log.error("无法连接Redis");
      throw e;
    }
  }

  @Override
  public void run(){

    RAtomicLong rastock = redisson.getAtomicLong("stock");
    if(rastock==null){
      log.error("redis key null");
      return;
    }
    long stock = rastock.get();
    log.info("stock:{}", stock);
  }
}
