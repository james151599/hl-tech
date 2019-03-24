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

  private static final long SESSION_TIMEOUT = 60000L;

  private static final int COOKIE_TIME = 60;

  private static final int REMEMBERME_TIME = 600;

  private static final long SESSION_SCHEDULE = 1800000L;

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager sm) {
    ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
    sffb.setSecurityManager(sm);
    Map<String, String> urlPermission = new HashMap<>();
    urlPermission.put("/login/index", "anon");
    urlPermission.put("/login/doLogin", "anon");
    urlPermission.put("/login/logout", "logout");
    urlPermission.put("/**", "user");
    urlPermission.put("/pay/**", "authc");
    sffb.setLoginUrl("/login/index");
    sffb.setSuccessUrl("/login/main");
    sffb.setUnauthorizedUrl("/login/unauthorized");
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
    // mr.setCachingEnabled(true); 默认是true
    // mr.setAuthorizationCachingEnabled(true); 默认是true
    // mr.setAuthenticationCachingEnabled(false); 默认是false

    return mr;
  }

  @Bean
  public HashedCredentialsMatcher credential() {
    HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
    hcm.setHashAlgorithmName(ShiroUtil.ALGORITHM_NAME);
    hcm.setHashIterations(ShiroUtil.HASH_ITERATIONS);

    return hcm;
  }

  @Bean
  public SessionManager sessionManager() {
    DefaultWebSessionManager dwsm = new DefaultWebSessionManager();
    dwsm.setGlobalSessionTimeout(SESSION_TIMEOUT);
    dwsm.setDeleteInvalidSessions(true);
    SimpleCookie sc = new SimpleCookie();
    sc.setName("shiroSessionId");
    // 设置 Cookie 的过期时间,秒为单位
    sc.setMaxAge(COOKIE_TIME);
    // sc.setHttpOnly(true); 不会暴露给客户端脚本代码,有助于减少某些类型的跨站点脚本攻击
    // dwsm.setSessionIdCookieEnabled(true); 默认是true
    dwsm.setSessionIdCookie(sc);

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
    sc.setMaxAge(REMEMBERME_TIME);
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
    essvs.setInterval(SESSION_SCHEDULE);
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
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public AuthorizationAttributeSourceAdvisor shiroAdvisor(SecurityManager sm) {
    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
    aasa.setSecurityManager(sm);

    return aasa;
  }
}
