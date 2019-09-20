package instanceBean.noScanBean.simulateMybatis;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import instanceBean.noScanBean.simulateMybatis.mappers.MyMapper;

public class MyScannerRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {
    BeanDefinitionBuilder bdb =
        BeanDefinitionBuilder.genericBeanDefinition(MyMapperFactoryBean.class);
    BeanDefinition bd = bdb.getBeanDefinition();
    bd.getConstructorArgumentValues().addGenericArgumentValue(MyMapper.class);
    registry.registerBeanDefinition(MyMapper.class.getName(), bd);
  }
}
