package instanceBean.noScanBean.simulateMybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;

public class MyMapperFactoryBean<T> implements FactoryBean<T> {

  private Class<T> mapperClass;

  public MyMapperFactoryBean(Class<T> mapperClass) {
    this.mapperClass = mapperClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getObject() throws Exception {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {mapperClass},
        new MyInvocationHandler());
  }

  @Override
  public Class<?> getObjectType() {
    return mapperClass;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Select select = method.getDeclaredAnnotation(Select.class);
      if (select != null) {
        String sql = select.value()[0];
        System.err.println("execute sql: " + sql);
        return 3;
      }

      return method.getDeclaringClass().getName();
    }
  }
}
