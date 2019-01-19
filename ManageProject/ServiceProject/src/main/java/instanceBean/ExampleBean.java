package instanceBean;

public class ExampleBean {

  private BeanA beanA;

  private int value;

  private ExampleBean(BeanA beanA, int value) {
    this.beanA = beanA;
    this.value = value;
  }

  public static ExampleBean createInstance(BeanA arg1, int arg2) {
    return new ExampleBean(arg1, arg2);
  }

  public BeanA getBeanA() {
    return beanA;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "ExampleBean: value=" + value + "    " + beanA.getBeanE().getBeanC().toString();
  }
}
