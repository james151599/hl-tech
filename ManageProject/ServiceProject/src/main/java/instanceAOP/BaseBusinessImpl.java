package instanceAOP;

import org.springframework.stereotype.Service;

@Service
public class BaseBusinessImpl implements BaseBusiness {
  @Override
  public void businessOne(Object p1, char p2) {
    System.out.println("call businessOne: " + p2);
  }

  @Override
  public void businessTwo(Object p1, char p2) {
    System.out.println("call businessTwo: " + p2);
    throw new RuntimeException("businessTwo throws RuntimeException");
  }
}
