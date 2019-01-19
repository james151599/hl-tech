package javaConfig;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import instanceLambda.Flyable;
import instanceLambda.UseThread;

@Configuration
public class LambdaConfig {

  @Bean
  public Flyable flyObject() {
    Flyable fa = (t) -> {
      System.out.println("can fly");
    };
    return fa;
  }

  // 代码尝试使用Lambda表达式替代抽象类的匿名内部类的写法会报错,提示必须继承函数式接口
//  @Bean
//  public AbstractBird birdObject() {
//    AbstractBird ab = () -> {
//      System.out.println("bird fly");
//    };
//  }

  @Bean
  public UseThread useThread() {
    return new UseThread();
  }

  @Bean
  public BlockingQueue<Runnable> blockingQueue() {
    return new ArrayBlockingQueue<>(3);
  }

  @Bean
  public ThreadFactory threadFactory() {
    return Executors.defaultThreadFactory();
  }

  @Bean
  public RejectedExecutionHandler rejectedExecutionHandler() {
    return new ThreadPoolExecutor.DiscardPolicy();
  }
}
