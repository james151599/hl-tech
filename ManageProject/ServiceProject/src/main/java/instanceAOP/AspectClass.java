package instanceAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class AspectClass implements Ordered {

  private static final int DEFAULT_MAX_RETRIES = 3;

  private int maxRetries = DEFAULT_MAX_RETRIES;

  public void setMaxRetries(int maxRetries) {
    this.maxRetries = maxRetries;
  }

  private int order;

  // allows us to control the ordering of advice
  public int getOrder() {
    return this.order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  @Pointcut("execution(public * instanceAOP.Business.*(..))")
  public void businessDefine() {}

  @Pointcut("execution(String instanceAOP..*.common*(..))")
  public void commonDefine() {}

  @Pointcut("within(instanceAOP..*)")
  public void allDefine() {}

  // can not replace "&&" to "and"
  @Pointcut("execution(* instanceAOP.CommonBusiness.*(int)) && args(num)")
  public void commonDefineNum(int num) {}

  @Pointcut("businessDefine() and allDefine()")
  public void businessDefineAnd() {}

  @After("businessDefine()")
  public void afterMethod() {
    System.out.println("weave afterMethod");
  }

  @AfterReturning(pointcut = "businessDefineAnd() or commonDefine()", returning = "retVal")
  public void afterSuccessMethod(Object retVal) {
    System.out.println("weave afterSuccessMethod: " + retVal);
  }

  @AfterThrowing(pointcut = "businessDefine()", throwing = "ex")
  public void afterFailedMethod(RuntimeException ex) {
    System.out.println("weave afterFailedMethod: " + ex.getMessage());
  }

  // a simple caching aspect could return a value from a cache
  // if it has one and invoke proceed() if it does not
  @Around("businessDefine()")
  public Object aroundMethod(ProceedingJoinPoint pjp) {
    System.out.println("weave aroundMethod");
    int numAttempts = 0;
    Object retVal = null;
    Throwable ta = null;

    while (numAttempts < this.maxRetries) {
      try {
        retVal = pjp.proceed();
        break;
      } catch (Throwable e) {
        numAttempts++;
        ta = e;
      }
    }
    if (numAttempts == this.maxRetries) {
      throw new RuntimeException("let afterFailedMethod execute", ta);
    }

    return retVal;
  }

  @Before("commonDefine() && args(str)")
  public void beforeMethod(String str) {
    System.out.println("weave beforeMethod: " + str);
  }

  @Before("commonDefineNum(num)")
  public void beforeMethod(int num) {
    System.out.println("weave beforeMethod: " + num);
  }
}
