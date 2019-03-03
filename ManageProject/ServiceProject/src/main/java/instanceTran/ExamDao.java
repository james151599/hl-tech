package instanceTran;

import java.util.List;

public interface ExamDao {

  int insertExam(String name, String course, int score);

  int deleteExam(long id);

  int updateExam(long id, int score);

  int selectExamCount();

  Exam selectExam(long id);

  List<Exam> selectExams(String course);
}
