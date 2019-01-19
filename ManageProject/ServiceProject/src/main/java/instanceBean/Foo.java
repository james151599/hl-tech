package instanceBean;

public class Foo {

  private String value;

  public Foo(String value) {
    this.value = value;
  }

  public void init() {
    System.out.println("init Foo");
  }

  // Spring does not manage the complete lifecycle of a prototype bean
  public void clean() {
    System.out.println("clean Foo");
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
