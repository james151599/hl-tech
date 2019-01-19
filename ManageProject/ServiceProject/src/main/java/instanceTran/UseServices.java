package instanceTran;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UseServices {

  @Resource(name = "esi")
  private ExamService es;

  @Autowired
  private ExamDao ed;

  @Transactional(propagation = Propagation.SUPPORTS)
  public void noTran() {
    ed.insertExam("noTran", "无事务", 60);
    es.insertExam("requireTran", "要求事务", 65);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void noTran2() {
    ed.insertExam("noTran2", "无事务2", 70);
    es.insertExamException("requireTranException", "要求事务异常", 75);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void haveTran() {
    ed.insertExam("haveTran", "有事务", 80);
    // Spring代理捕获了异常并标记回滚
    try {
      es.insertExamException("requireTranException", "要求事务异常", 85);
    } catch (Exception e) {
      System.out.println("haveTran catches " + e.getMessage());
    }
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void newTran() {
    ed.insertExam("newTran", "新建事务", 90);
    // 异常会导致外部事物回滚
    try {
      es.updateExamException(1, 77);
    } catch (Exception e) {
      System.out.println("newTran catches " + e.getMessage());
    }
  }

  // 嵌套事务执行失败将回滚到方法执行前的保存点,并不会将整个事务回滚
  @Transactional(propagation = Propagation.REQUIRED)
  public void nestedTran() {
    ed.insertExam("nestedTran", "嵌套事务", 95);
    // 异常会导致外部事物回滚
    try {
      es.deleteExamException(1);
    } catch (Exception e) {
      System.out.println("nestedTran catches " + e.getMessage());
    }
  }

  // 嵌套事务不能够提交必须通过外层事务来完成提交的动作,外层事务的回滚也会造成内部事务的回滚
  @Transactional(propagation = Propagation.REQUIRED)
  public void nestedTran2() {
    ed.insertExam("nestedTran2", "嵌套事务2", 100);
    es.deleteExam(1);
    throw new RuntimeException("外部事务异常");
  }
}
