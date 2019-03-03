package middleTierRelated.javaConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import middleTierRelated.mybatis.MybatisConfig;

@Configuration
@ComponentScan(basePackages = {"middleTierRelated"})
@Import({MybatisConfig.class})
public class TierConfig {

}
