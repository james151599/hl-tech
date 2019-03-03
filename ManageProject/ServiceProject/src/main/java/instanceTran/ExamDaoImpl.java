package instanceTran;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class ExamDaoImpl implements ExamDao {

  private ExamUtils eu;

  @Autowired
  public ExamDaoImpl(ExamUtils eu) {
    this.eu = eu;
  }

  @Override
  public int insertExam(String name, String course, int score) {
    ClassPathResource cpr = new ClassPathResource("instanceTran/transactional.png");
    try {
      return eu.insertExam(name, course, score, cpr.getFile());
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }

  @Override
  public int deleteExam(long id) {
    return eu.deleteExamById(id);
  }

  @Override
  public int updateExam(long id, int score) {
    return eu.updateExamScore(id, score);
  }

  @Override
  public int selectExamCount() {
    return eu.getExamRowCount();
  }

  @Override
  public Exam selectExam(long id) {
    System.out.println("测试缓存");
    return eu.getExamById(id);
  }

  @Override
  public List<Exam> selectExams(String course) {
    return eu.getExamsByCourse(course);
  }
}
