package instanceTran;

import java.util.List;

public interface ExamService {

  void insertExam(String name, String course, int score);

  void insertExamException(String name, String course, int score);

  void deleteExam(long id);

  void deleteExamException(long id);

  void updateExam(long id, int score);

  void updateExamException(long id, int score);

  int getExamCount();

  Exam getExam(long id);

  List<Exam> getExams(String course);
}
