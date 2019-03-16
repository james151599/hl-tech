package shiro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import shiro.shiroItem.ShiroUtil;


// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = {ShiroConfig.class, WebConfig.class})
// @WebAppConfiguration
public class ShiroTest {

  // @Autowired
  private WebApplicationContext wac;

  private MockMvc mvc;

  // @Before
  public void initEnv() {
    DelegatingFilterProxy dfp = new DelegatingFilterProxy("shiroFilter", wac);
    dfp.setTargetFilterLifecycle(true);
    mvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(dfp, "/*").build();
  }

  // @Test
  public void testLogin() throws Exception {
    String result = mvc
        .perform(post("/testShiro/login").contentType(MediaType.ALL).param("username", "abc")
            .param("password", "123"))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    System.out.println(result);
  }

  // @Test
  public void testEncryption() {
    String publicSalt = ShiroUtil.generatePublicSalt("abc");
    System.out.println(publicSalt);
    String secret = ShiroUtil.encryption("password1", publicSalt);
    System.out.println(secret + "   " + secret.length());
  }
}
