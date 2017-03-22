package me.tongzhuangzhuang.test;

import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangtingbang on 2017/3/22.
 */
public class Processor implements Runnable {

  private static Logger log = LoggerFactory.getLogger(Processor.class);

  private String pId;
  private String pName;


  Processor(String pId, String pName) {
    this.pId = pId;
    this.pName = pName;
  }


  @Override public void run() {

    int s = TestMain.stock.get();
    int f = TestMain.frezon.get();
    int d = TestMain.draw.get();

    if (s - f <= 0) {
      log.error("{}-{}=====>over", pId, pName);
      return;
    }
    int f1 = TestMain.frezon.incrementAndGet();
    try {
      Thread.sleep(500);
    } catch (Exception e) {
      e.printStackTrace();
    }

    int s1 = TestMain.stock.decrementAndGet();
    int f2 = TestMain.frezon.decrementAndGet();
    int d1 = TestMain.draw.incrementAndGet();


    log.info("{}==>>thread:{}-{}====> s:{}, s1:{}, f:{}, f1:{}, f2:{}, d:{}, d1:{}", //
      System.currentTimeMillis(),pId, pName, s, s1, f, f1, f2, d, d1);
    return;
  }
}
