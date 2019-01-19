package instanceBean.scanBean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import instanceBean.BeanC;

@Component("e")
@DependsOn({"c"})
public class BeanE {

  private BeanC beanC;

  @Autowired
  public BeanE(BeanC beanC) {
    this.beanC = beanC;
  }

  @Autowired
  @Qualifier("first")
  private List<String> list1;

  @Autowired
  @Qualifier("second")
  private List<String> list2;

  @PostConstruct
  public void init() {
    System.out.println("init BeanE " + list1.get(0));
  }

  @PreDestroy
  public void clean() {
    System.out.println("clean BeanE " + list2.get(0));
  }

  public BeanC getBeanC() {
    return beanC;
  }

  public void setBeanC(BeanC beanC) {
    this.beanC = beanC;
  }
}
