package javaConfig;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import net.sf.ehcache.CacheManager;

@Configuration
@EnableCaching
public class EhCacheConfig {

  @Bean
  public EhCacheCacheManager springEhCacheCacheManager(CacheManager cm) {
    return new EhCacheCacheManager(cm);
  }

  @Bean
  public EhCacheManagerFactoryBean springEhCacheManagerFactory() {
    EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
    emfb.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
    return emfb;
  }
}
