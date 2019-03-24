package middleTierRelated.javaConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import shiro.ShiroConfig;

@Configuration
@ComponentScan(basePackages = {"middleTierRelated"},
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {Configuration.class}))
@Import({MybatisConfig.class, ShiroConfig.class})
@EnableTransactionManagement
public class TierConfig {

}
