package instanceAOP;

import org.springframework.stereotype.Component;

@Component
public class BusinessImpl implements Business {

  @Override
  public void methodOne() {
    System.out.println("call methodOne");
  }

  @Override
  public void methodTwo() {
    System.out.println("call methodTwo");
    throw new RuntimeException("methodTwo throws RuntimeException");
  }
}
