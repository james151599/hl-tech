package instanceAOP;

import org.springframework.stereotype.Component;

@Component
public class BusinessImpl implements Business {

  @Override
  public void businessOne() {
    System.out.println("call businessOne");
  }

  @Override
  public void businessTwo() {
    System.out.println("call businessTwo");
    throw new RuntimeException("businessTwo throws RuntimeException");
  }
}
