package javaConfig;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.web.context.annotation.SessionScope;
import instanceBean.noScanBean.Foo;
import instanceBean.noScanBean.UserPreferences;
import instanceBean.noScanBean.UserService;

/*
 * All @Configuration classes are subclassed at startup-time with CGLIB. In the subclass, the child
 * method checks the container first for any cached(scoped) beans before it calls the parent method
 * and creates a new instance. @Configuration classes are processed quite early during the
 * initialization of the context
 */

@Configuration
@PropertySource("classpath:instanceBean/basicApp.properties")
@ComponentScan(basePackages = {"instanceBean"},
    excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".noScanBean..*"))
// get all the matching resources in the class loader hierarchy
// classpath*:*.xml might not retrieve files from the root of jar files but rather only from the
// root of expanded directories
@ImportResource(locations = {"classpath*:basicApp/spring_beans.xml"})
public class BeanConfig {

  @Autowired
  private Environment env;

  @Value("${test.age}")
  private String age;

  @Bean("first")
  public List<String> firstList() {
    return Arrays.asList(new String[] {env.getProperty("test.name")});
  }

  @Bean("second")
  public List<String> secondList() {
    return Arrays.asList(new String[] {age});
  }

  @Bean(name = "f", initMethod = "init", destroyMethod = "clean")
  @Scope("prototype")
  @Lazy(true)
  @Description("Provides a basic example of a bean")
  public Foo foo(@Value("default value") String value) {
    return new Foo(value);
  }

  @Bean
  @SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
  public UserPreferences userPreferences() {
    return new UserPreferences();
  }

  @Bean
  public UserService userService(UserPreferences up) {
    UserService service = new UserService();
    // a reference to the proxied userPreferences bean
    service.setUserPreferences(up);
    return service;
  }
}
