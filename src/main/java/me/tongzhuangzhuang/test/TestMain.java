package me.tongzhuangzhuang.test;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangtingbang on 2017/3/22.
 */
public class TestMain {

  private static Logger log = LoggerFactory.getLogger(TestMain.class);

  public static AtomicInteger stock = new AtomicInteger(5000);
  public static AtomicInteger frezon = new AtomicInteger(0);
  public static AtomicInteger draw = new AtomicInteger(0);

  public static void main(String[] argv) {
//    int times = 5;
//    int count = 1000;
//    List<Processor> threads = Lists.newArrayList();
//
//    for (int i = 0; i < count; i++) {
//      Processor p = new Processor("ID"+i,"NAME"+i);
//      threads.add(p);
//    }
//    for(Processor p:threads){
//      for(int i=0;i<times; i++) {
//        new Thread(p).start();
//      }
//    }
//
//    try {
//      Thread.sleep(10000);
//    }catch (Exception e){
//      e.printStackTrace();
//    }
//    log.info("s:{}, f:{}, d:{}", stock, frezon, draw);

    RedisLockProcessor processor = new RedisLockProcessor();
    processor.init();
    processor.run();
  }
}
