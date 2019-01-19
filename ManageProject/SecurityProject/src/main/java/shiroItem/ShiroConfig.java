package shiroItem;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfig {

  @Bean
  public RolesPermissions myRolesPermissions() {
    return new RolesPermissions();
  }

  @Bean
  public Realm myRealm() {
    return new MyRealm();
  }

  @Bean
  public SecurityManager securityManager(Realm oneRealm) {
    DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager(oneRealm);

    return dwsm;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager sm) {
    ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
    sffb.setSecurityManager(sm);
    Map<String, String> urlPermission = new HashMap<>();
    urlPermission.put("/testShiro/index", "anon");
    urlPermission.put("/testShiro/login", "anon");
    urlPermission.put("/testShiro/doInsert", "authc");
    urlPermission.put("/testShiro/doDelete", "authc");
    urlPermission.put("/testShiro/doUpdate", "authc");
    urlPermission.put("/testShiro/doView", "authc");
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
