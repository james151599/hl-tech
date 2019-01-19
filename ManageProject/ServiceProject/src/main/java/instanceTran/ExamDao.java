package instanceTran;

import java.util.List;

public interface ExamDao {

  int insertExam(String name, String course, int score);

  int deleteExam(long id);

  int updateExam(long id, int score);

  int getExamCount();

  Exam getExam(long id);

  List<Exam> getExams(String course);
}
