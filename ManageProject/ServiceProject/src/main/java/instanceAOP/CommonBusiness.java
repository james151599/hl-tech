package instanceAOP;

import org.springframework.stereotype.Component;

@Component
public class CommonBusiness {

  public String exeMethodTest1(String arg) {
    System.out.println("call exeMethodTest1");
    return arg;
  }

  public String exeMethodTest2() {
    System.out.println("call exeMethodTest2");
    return "123";
  }
}
