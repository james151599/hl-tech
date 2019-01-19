package instanceBean;

import instanceBean.scanBean.BeanE;

public class BeanA {

  private String property;

  private BeanE beanE;

  public void init() {
    System.out.println("init BeanA");
  }

  public void clean() {
    System.out.println("clean BeanA");
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public BeanE getBeanE() {
    return beanE;
  }

  public void setBeanE(BeanE beanE) {
    this.beanE = beanE;
  }
}
