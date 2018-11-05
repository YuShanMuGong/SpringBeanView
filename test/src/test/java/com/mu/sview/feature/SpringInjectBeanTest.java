package com.mu.sview.feature;

import com.mu.sview.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;

public class SpringInjectBeanTest extends BaseTest {



    @Test
    public void remove_bean() {

//        ApplicationContext context = contextHolder.getContext();
//
//        String[] names = context.getBeanDefinitionNames();
//
//        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context.getAutowireCapableBeanFactory();
//
//        Object obj = context.getBean(names[0]);
//
//        BeanDefinition definition = registry.getBeanDefinition(names[0]);
//
//        String[] denpends = definition.getDependsOn();
//
//        registry.removeBeanDefinition(names[0]);
//
//        System.out.println(obj == null);
    }

    @Test
    public void inject() {

    }

}
