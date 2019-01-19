package instanceLambda;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javaConfig.LambdaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LambdaConfig.class})
public class LambdaTest {

  @Autowired
  private UseThread ut;

  @Autowired
  private BlockingQueue<Runnable> queue;

  @Autowired
  private ThreadFactory tf;

  @Autowired
  private RejectedExecutionHandler handler;

  @Autowired
  private Flyable fa;

  @Test
  public void testLambda() {
    fa.fly(0L);
  }

  @Test
  public void testThread() {
    final CountDownLatch cdl = new CountDownLatch(3);
    ExecutorService es =
        new ThreadPoolExecutor(5, 10, 3000, TimeUnit.MILLISECONDS, queue, tf, handler);

    final String add1 = "add1";
    final String add2 = "add2";
    final String add3 = "add3";

    Runnable addRun1 = () -> {
      try {
        ut.addTicket(add1);
      } finally {
        cdl.countDown();
      }
    };
    Runnable addRun2 = () -> {
      try {
        ut.addTicket(add2);
      } finally {
        cdl.countDown();
      }
    };
    Runnable addRun3 = () -> {
      try {
        ut.addTicket(add3);
      } finally {
        cdl.countDown();
      }
    };

    Thread t1 = new Thread(addRun1, "thread addRun1");
    Thread t2 = new Thread(addRun2, "thread addRun2");
    Thread t3 = new Thread(addRun3, "thread addRun3");
    es.execute(t1);
    es.execute(t2);
    es.execute(t3);
    try {
      cdl.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    final CountDownLatch cdl2 = new CountDownLatch(3);

    final String sell1 = "sell1";
    final String sell2 = "sell2";
    final String sell3 = "sell3";

    Runnable sellRun1 = () -> {
      try {
        ut.sellTicket(sell1);
      } finally {
        cdl2.countDown();
      }
    };
    Runnable sellRun2 = () -> {
      try {
        ut.sellTicket(sell2);
      } finally {
        cdl2.countDown();
      }
    };
    Runnable sellRun3 = () -> {
      try {
        ut.sellTicket(sell3);
      } finally {
        cdl2.countDown();
      }
    };

    t1 = new Thread(sellRun1, "thread sellRun1");
    t2 = new Thread(sellRun2, "thread sellRun2");
    t3 = new Thread(sellRun3, "thread sellRun3");
    es.execute(t1);
    es.execute(t2);
    es.execute(t3);
    try {
      cdl2.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    es.shutdown();
  }
}
