package javaConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AOPConfig.class, BeanConfig.class, EhCacheConfig.class, LambdaConfig.class,
    TranConfig.class})
public class AllConfig {

}
