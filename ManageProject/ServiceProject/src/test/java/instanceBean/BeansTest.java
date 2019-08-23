package instanceBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import instanceBean.noScanBean.ExampleBean;
import instanceBean.noScanBean.UserService;
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
    System.out.println(eb.toString());
    request.setAttribute("requestValue", "requestValue");
    session.setAttribute("sessionValue", "sessionValue");
    us.getUserPreferences().desc();
  }
}
