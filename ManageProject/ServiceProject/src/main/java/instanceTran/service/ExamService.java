package instanceTran.service;

import java.util.List;
import instanceTran.Exam;

public interface ExamService {

  void saveExam(String name, String course, int score);

  void saveExamException(String name, String course, int score);

  void removeExam(long id);

  void removeExamException(long id);

  void alterExam(long id, int score);

  void alterExamException(long id, int score);

  int getExamCount();

  Exam getExam(long id);

  List<Exam> getExams(String course);
}
