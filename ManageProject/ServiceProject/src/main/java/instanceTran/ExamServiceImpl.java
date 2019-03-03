package instanceTran;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("esi")
public class ExamServiceImpl implements ExamService {

  private ExamDao ed;

  @Autowired
  public ExamServiceImpl(ExamDao ed) {
    this.ed = ed;
  }

  @Override
  public void saveExam(String name, String course, int score) {
    int num = ed.insertExam(name, course, score);
    System.out.println("insert row:" + num);
  }

  @Override
  public void removeExam(long id) {
    int num = ed.deleteExam(id);
    System.out.println("delete row:" + num);
  }

  @Override
  public void alterExam(long id, int score) {
    int num = ed.updateExam(id, score);
    System.out.println("update row:" + num);
  }

  @Override
  public void saveExamException(String name, String course, int score) {
    ed.insertExam(name, course, score);
    throw new RuntimeException("insert exception");
  }

  @Override
  public void removeExamException(long id) {
    ed.deleteExam(id);
    throw new RuntimeException("delete exception");
  }

  @Override
  public void alterExamException(long id, int score) {
    ed.updateExam(id, score);
    throw new RuntimeException("update exception");
  }

  @Override
  public int getExamCount() {
    return ed.selectExamCount();
  }

  @Override
  public Exam getExam(long id) {
    return ed.selectExam(id);
  }

  @Override
  public List<Exam> getExams(String course) {
    return ed.selectExams(course);
  }
}
