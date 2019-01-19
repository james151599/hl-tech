package instanceBean;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BeanC extends BeanD {

  private String age;

  @Autowired
  public BeanC(@Qualifier("first") List<String> list1, @Qualifier("second") List<String> list2) {
    super(list1, list2);
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return super.age + "   " + super.list1.get(0) + "   " + super.list2.get(0) + "   " + super.ms
        + "   " + this.age;
  }
}
