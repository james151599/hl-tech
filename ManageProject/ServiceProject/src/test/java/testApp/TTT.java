package testApp;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import instanceAOP.Business;
import instanceAOP.CommonBusiness;
import instanceBean.noScanBean.ExampleBean;
import instanceTran.Exam;
import instanceTran.ExamService;
import javaConfig.AOPConfig;
import javaConfig.TranConfig;

public class TTT {
  public static void main(String[] args) {
    String tt = "1,2,3,";
    for (int i = 0, j = tt.split(",").length; i < j; i++) {

    }
    // System.out.println(j);

    ConfigurableApplicationContext cac =
        new ClassPathXmlApplicationContext("basicApp/spring_beans.xml");
    System.out.println(cac.getBean(ExampleBean.class).toString());
    Resource template = cac.getResource("classpath:some/resource/path/myTemplate.txt");
    cac.registerShutdownHook();
    cac.close();

    ApplicationContext ac = new AnnotationConfigApplicationContext(AOPConfig.class);
    CommonBusiness cb = ac.getBean(CommonBusiness.class);
    cb.commonBusinessOne("abc");
    cb.commonBusinessTwo();
    Business bi = ac.getBean(Business.class);
    bi.businessOne(null, '1');
    bi.businessTwo(null, '2');

    ApplicationContext ac2 = new AnnotationConfigApplicationContext(TranConfig.class);
    ExamService es = (ExamService) ac2.getBean("esi");
    ExamService es2 = (ExamService) ac2.getBean("esi2");
    es.saveExam("aaa", "语文", 80);
    es2.saveExam("bbb", "语文", 90);
    es.alterExam(1, 85);
    es2.alterExam(2, 95);
    int total = es.getExamCount();
    System.out.println(total);
    Exam em = es.getExam(1);
    System.out.println(em.toString());
    List<Exam> ems = es2.getExams("语文");
    ems.size();

  }
}
