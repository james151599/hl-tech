package instanceBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import instanceBean.noScanBean.ExampleBean;
import instanceBean.noScanBean.UserService;
import instanceBean.noScanBean.simulateMybatis.MyMapperFactoryBean;
import instanceBean.noScanBean.simulateMybatis.mappers.MyMapper;
import javaConfig.BeanConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class})
@WebAppConfiguration
public class BeansTest {

  @Autowired
  private ExampleBean eb;

  @Autowired
  private MockHttpServletRequest request;

  @Autowired
  private MockHttpSession session;

  @Autowired
  private UserService us;

  @Test
  public void testBeans() {
    // System.out.println(eb.toString());
    // request.setAttribute("requestValue", "requestValue");
    // session.setAttribute("sessionValue", "sessionValue");
    // us.getUserPreferences().desc();
    ApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);
    System.err.println(ac.getBean(MyMapperFactoryBean.class));
    System.err.println(ac.getBean(MyMapper.class));
    System.err.println(ac.getBean(MyMapper.class).selectCount());
  }
}
