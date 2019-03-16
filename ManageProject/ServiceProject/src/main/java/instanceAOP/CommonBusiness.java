package instanceAOP;

import org.springframework.stereotype.Component;

@Component
public class CommonBusiness {

  public String commonBusinessOne(String arg) {
    System.out.println("call commonBusinessOne");
    return arg;
  }

  public String commonBusinessTwo() {
    System.out.println("call commonBusinessTwo");
    return "123";
  }

  public int commonBusinessThree(int count) {
    System.out.println("call commonBusinessThree");
    return count;
  }
}
