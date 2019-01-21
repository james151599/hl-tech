package shiro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import webRelated.javaConfig.WebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShiroConfig.class, WebConfig.class})
@WebAppConfiguration
public class ShiroTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  @Before
  public void initEnv() {
    DelegatingFilterProxy dfp = new DelegatingFilterProxy("shiroFilter", wac);
    dfp.setTargetFilterLifecycle(true);
    mvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(dfp, "/*").build();
  }

  @Test
  public void testLogin() throws Exception {
    String result = mvc
        .perform(post("/testShiro/login").contentType(MediaType.ALL).param("username", "abc")
            .param("password", "123"))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    System.out.println(result);
  }
}
