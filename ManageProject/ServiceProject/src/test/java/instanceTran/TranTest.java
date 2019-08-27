package instanceTran;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.UnexpectedRollbackException;
import instanceTran.service.ExamService;
import javaConfig.TranConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TranConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranTest {

  @Autowired
  @Qualifier("esi2")
  private ExamService es2;

  @Autowired
  private UseServices uss;

  @Test
  public void aTestTran() {
    uss.noTran();
    uss.newTran();
    uss.nestedTran();
    int total = es2.getExamCount();
    System.out.println(total);
    Exam em = es2.getExam(1L);
    em = es2.getExam(1L);
    System.out.println(em.toString());
  }

  @Test(expected = RuntimeException.class)
  public void bTestTranException() {
    uss.noTran2();
  }

  @Test(expected = UnexpectedRollbackException.class)
  public void cTestTranException() {
    uss.haveTran();
  }

  @Test(expected = RuntimeException.class)
  public void dTestTranException() {
    uss.nestedTran2();
  }
}
