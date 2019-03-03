package instanceAOP;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javaConfig.AOPConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AOPConfig.class})
public class AOPTest {

  @Rule
  public final ExpectedException expectedEx = ExpectedException.none();

  @Autowired
  private CommonBusiness cb;

  @Autowired
  private Business ib;

  @Test
  public void testAOP() {
    cb.exeMethodTest1("abc");
    cb.exeMethodTest2();
    cb.exeMethodTest3(7);
    ib.methodOne();
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("let afterFailedMethod execute");
    ib.methodTwo();
  }
}
