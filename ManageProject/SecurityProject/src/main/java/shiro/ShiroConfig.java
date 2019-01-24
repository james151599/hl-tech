package shiro;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import javaConfig.EhCacheConfig;
import shiro.shiroApp.ShiroService;
import shiro.shiroItem.MyRealm;

@Configuration
@ComponentScan
@Import({EhCacheConfig.class})
public class ShiroConfig {

  @Bean
  public SecurityManager securityManager(Realm oneRealm, SessionManager sm, CacheManager cm) {
    DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager(oneRealm);
    dwsm.setSessionManager(sm);
    dwsm.setCacheManager(cm);

    return dwsm;
  }

  @Bean
  public Realm myRealm(ShiroService ss, HashedCredentialsMatcher hcm) {
    MyRealm mr = new MyRealm(ss);
    mr.setCredentialsMatcher(hcm);

    return mr;
  }

  @Bean
  public HashedCredentialsMatcher credential() {
    HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
    hcm.setHashAlgorithmName("SHA-512");
    hcm.setHashIterations(3);

    return hcm;
  }

  @Bean
  public SessionManager sessionManager() {
    DefaultWebSessionManager dwsm = new DefaultWebSessionManager();
    dwsm.setGlobalSessionTimeout(600000);
    SimpleCookie sc = new SimpleCookie();
    sc.setName("shiroCookie");
    // 设置 Cookie 的过期时间,秒为单位
    sc.setMaxAge(360000);
    // 客户端不会暴露给客户端脚本代码,有助于减少某些类型的跨站点脚本攻击
    sc.setHttpOnly(true);
    dwsm.setSessionIdCookie(sc);
    dwsm.setSessionIdCookieEnabled(true);

    EnterpriseCacheSessionDAO ecsDao = new EnterpriseCacheSessionDAO();
    ecsDao.setActiveSessionsCacheName("shiro-activeSessionCache");
    dwsm.setSessionDAO(ecsDao);

    return dwsm;
  }

  @Bean
  public CacheManager shiroCacheManager(net.sf.ehcache.CacheManager cm) {
    EhCacheManager ehCache = new EhCacheManager();
    ehCache.setCacheManager(cm);

    return ehCache;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager sm) {
    ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
    sffb.setSecurityManager(sm);
    Map<String, String> urlPermission = new HashMap<>();
    urlPermission.put("/testShiro/index", "anon");
    urlPermission.put("/testShiro/login", "anon");
    urlPermission.put("/testShiro/logout", "logout");
    urlPermission.put("/testShiro/doInsert", "authc");
    urlPermission.put("/testShiro/doDelete", "authc");
    urlPermission.put("/testShiro/doUpdate", "authc");
    urlPermission.put("/testShiro/doView", "authc");
    sffb.setLoginUrl("/testShiro/index");
    sffb.setFilterChainDefinitionMap(urlPermission);

    return sffb;
  }

  // @Bean
  // public MethodInvokingFactoryBean getMethodInvokingFactoryBean(SecurityManager sm) {
  // MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
  // factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
  // factoryBean.setArguments(new Object[] {sm});
  // return factoryBean;
  // }

  @Bean
  public LifecycleBeanPostProcessor shiroProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  @DependsOn("shiroProcessor")
  public AuthorizationAttributeSourceAdvisor shiroAdvisor(SecurityManager sm) {
    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
    aasa.setSecurityManager(sm);

    return aasa;
  }
}
