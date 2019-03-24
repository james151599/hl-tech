package middleTierRelated.javaConfig;

import java.io.IOException;
import javax.sql.DataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@ImportResource(locations = {"classpath:spring_db.xml"})
public class MybatisConfig {

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(DataSource ds) throws IOException {
    ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
    SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
    ssfb.setConfigLocation(rpr.getResource("classpath:mybatis-config.xml"));
    ssfb.setDataSource(ds);
    ssfb.setMapperLocations(rpr.getResources("classpath*:mybatis_mappers/**/*.xml"));

    return ssfb;
  }

  @Bean
  public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer msc = new MapperScannerConfigurer();
    msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
    msc.setBasePackage("middleTierRelated.mapper");

    return msc;
  }
}
