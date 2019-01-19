package javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import instanceAOP.AspectClass;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"instanceAOP"})
public class AOPConfig {

  @Bean
  public AspectClass aspectClass() {
    AspectClass ac = new AspectClass();
    // execute before the transactional advice on the way in
    // and after the transactional advice on the way out
    // hence the lower order number
    ac.setOrder(1);
    return ac;
  }
}
