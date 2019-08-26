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

/*
 * in packages com.xyz.someapp.abc.service and com.xyz.someapp.def.service then the pointcut
 * expression "execution(* com.xyz.someapp..service.*.*(..))" could be used instead
 */

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

  @Pointcut("execution(public * instanceAOP.BaseBusiness.*(..))")
  public void businessDefine() {}

  @Pointcut("execution(String instanceAOP..*.common*(..))")
  public void str_commonDefine() {}

  @Pointcut("within(instanceAOP..*)")
  public void allDefine() {}

  // can not replace "&&" to "and"
  @Pointcut("execution(* instanceAOP.CommonBusiness.*(..)) && args(num)")
  public void commonDefineNum(int num) {}

  @Pointcut("businessDefine() && allDefine()")
  public void businessDefineAnd() {}

  @After("businessDefine()")
  public void afterMethod() {
    System.out.println("weave afterMethod");
  }

  @AfterReturning(pointcut = "businessDefineAnd() or str_commonDefine()", returning = "retVal")
  public void afterSuccessMethod(Object retVal) {
    System.out.println("weave afterSuccessMethod: " + retVal);
  }

  @AfterThrowing(pointcut = "businessDefine()", throwing = "ex")
  public void afterFailedMethod(RuntimeException ex) {
    System.out.println("weave afterFailedMethod: " + ex.getMessage());
  }

  // a simple caching aspect could return a value from a cache
  // if it has one, and invoke proceed() if it does not
  @Around(value = "target(instanceAOP.BaseBusiness) && args(obj,c)")
  public Object aroundMethod(ProceedingJoinPoint pjp, Object obj, char c) {
    System.out.println("weave aroundMethod");
    int numAttempts = 0;
    Object retVal = null;
    Throwable ta = null;

    while (numAttempts < this.maxRetries) {
      try {
        retVal = pjp.proceed(new Object[] {obj, c});
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

  @Before("bean(bi)")
  public void beforeMethod() {
    System.out.println("weave beforeMethod");
  }

  @Before("str_commonDefine() && args(str)")
  public void beforeMethod(String str) {
    System.out.println("weave beforeMethod: " + str);
  }

  @Before("commonDefineNum(num)")
  public void beforeMethod(int num) {
    System.out.println("weave beforeMethod: " + num);
  }
}
