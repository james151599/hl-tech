package javaConfig;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"instanceTran"})
@ImportResource({"classpath:spring_aop.xml"})
@Import({EhCacheConfig.class})
@EnableTransactionManagement
public class TranConfig {

  @Bean
  public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public NamedParameterJdbcTemplate createJdbcTemplate2(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }
}
