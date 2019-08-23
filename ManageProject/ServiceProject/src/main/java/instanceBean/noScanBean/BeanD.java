package instanceBean.noScanBean;

import java.util.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;

public abstract class BeanD {

  protected String age = "origin";

  protected List<String> list1;

  protected List<String> list2;

  @Autowired
  protected BeanFactory bf;

  @Autowired
  protected ApplicationContext ac;

  @Autowired
  protected ResourceLoader rl;

  @Autowired
  protected ApplicationEventPublisher aep;

  @Autowired
  protected MessageSource ms;

  public BeanD(List<String> list1, List<String> list2) {
    this.list1 = list1;
    this.list2 = list2;
  }
}
