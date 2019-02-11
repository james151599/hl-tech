package shiro;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.sonatype.plexus.components.cipher.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import javaConfig.EhCacheConfig;
import shiro.shiroItem.MyRealm;
import shiro.shiroItem.ShiroUtil;
import shiro.shiroService.ShiroService;

@Configuration
@ComponentScan
@Import({EhCacheConfig.class})
public class ShiroConfig {

  private static final long SESSIONTIMEOUT = 60000L;

  private static final int COOKIETIME = 60;

  private static final int REMEMBERMETIME = 600;

  private static final long SESSIONSCHEDULE = 1800000L;

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager sm) {
    ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
    sffb.setSecurityManager(sm);
    Map<String, String> urlPermission = new HashMap<>();
    urlPermission.put("/user/index", "anon");
    urlPermission.put("/user/login", "anon");
    urlPermission.put("/user/logout", "logout");
    urlPermission.put("/**", "user");
    urlPermission.put("/user/pay/**", "authc");
    sffb.setLoginUrl("/user/index");
    sffb.setSuccessUrl("/user/main");
    sffb.setFilterChainDefinitionMap(urlPermission);

    return sffb;
  }

  @Bean
  public SecurityManager securityManager(Realm oneRealm, SessionManager sm, RememberMeManager rmm,
      CacheManager cm) {
    DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager(oneRealm);
    dwsm.setSessionManager(sm);
    dwsm.setRememberMeManager(rmm);
    dwsm.setCacheManager(cm);

    return dwsm;
  }

  @Bean
  public Realm myRealm(ShiroService ss, HashedCredentialsMatcher hcm) {
    MyRealm mr = new MyRealm(ss);
    mr.setCredentialsMatcher(hcm);
    mr.setCachingEnabled(true);

    return mr;
  }

  @Bean
  public HashedCredentialsMatcher credential() {
    HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
    hcm.setHashAlgorithmName(ShiroUtil.ALGORITHMNAME);
    hcm.setHashIterations(ShiroUtil.HASHITERATIONS);

    return hcm;
  }

  @Bean
  public SessionManager sessionManager() {
    DefaultWebSessionManager dwsm = new DefaultWebSessionManager();
    dwsm.setGlobalSessionTimeout(SESSIONTIMEOUT);
    dwsm.setDeleteInvalidSessions(true);
    SimpleCookie sc = new SimpleCookie();
    sc.setName("shiroSessionId");
    // 设置 Cookie 的过期时间,秒为单位
    sc.setMaxAge(COOKIETIME);
    // 不会暴露给客户端脚本代码,有助于减少某些类型的跨站点脚本攻击
    sc.setHttpOnly(true);
    dwsm.setSessionIdCookie(sc);
    dwsm.setSessionIdCookieEnabled(true);

    EnterpriseCacheSessionDAO ecsDao = new EnterpriseCacheSessionDAO();
    ecsDao.setActiveSessionsCacheName("shiro-activeSessionCache");
    ecsDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
    dwsm.setSessionDAO(ecsDao);

    return dwsm;
  }

  @Bean
  public RememberMeManager rememberMeManager() {
    CookieRememberMeManager crmm = new CookieRememberMeManager();
    crmm.setCipherKey(Base64.encodeBase64("2AvVhdsgUs0FSA3SDFAdag==".getBytes()));
    CipherService cs = new AesCipherService();
    crmm.setCipherService(cs);
    SimpleCookie sc = new SimpleCookie();
    sc.setName("shiroRememberMe");
    sc.setHttpOnly(true);
    sc.setMaxAge(REMEMBERMETIME);
    crmm.setCookie(sc);

    return crmm;
  }

  @Bean
  public CacheManager shiroCacheManager(net.sf.ehcache.CacheManager cm) {
    EhCacheManager ehCache = new EhCacheManager();
    ehCache.setCacheManager(cm);

    return ehCache;
  }

  @Bean
  public SessionValidationScheduler sessionValidationScheduler(
      @Qualifier("sessionManager") SessionManager sm) {
    ExecutorServiceSessionValidationScheduler essvs =
        new ExecutorServiceSessionValidationScheduler();
    essvs.setInterval(SESSIONSCHEDULE);
    essvs.setSessionManager((ValidatingSessionManager) sm);

    return essvs;
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
