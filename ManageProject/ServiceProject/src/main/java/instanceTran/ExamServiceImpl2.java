package instanceTran;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("esi2")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ExamServiceImpl2 implements ExamService {

  private ExamDao ed;

  @Autowired
  public ExamServiceImpl2(ExamDao ed) {
    this.ed = ed;
  }

  @Override
  public void insertExam(String name, String course, int score) {
    int num = ed.insertExam(name, course, score);
    System.out.println("insert row:" + num);
  }

  @Override
  public void deleteExam(long id) {
    int num = ed.deleteExam(id);
    System.out.println("delete row:" + num);
  }

  @Override
  public void updateExam(long id, int score) {
    int num = ed.updateExam(id, score);
    System.out.println("update row:" + num);
  }

  @Override
  public void insertExamException(String name, String course, int score) {
    ed.insertExam(name, course, score);
    throw new RuntimeException("insert exception");
  }

  @Override
  public void deleteExamException(long id) {
    ed.deleteExam(id);
    throw new RuntimeException("delete exception");
  }

  @Override
  public void updateExamException(long id, int score) {
    ed.updateExam(id, score);
    throw new RuntimeException("update exception");
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable("cacheObj")
  public int getExamCount() {
    return ed.getExamCount();
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable("cacheObj")
  public Exam getExam(long id) {
    return ed.getExam(id);
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable("cacheObj")
  public List<Exam> getExams(String course) {
    return ed.getExams(course);
  }
}
