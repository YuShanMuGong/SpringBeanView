package com.mu.sview.feature;

import com.mu.sview.BaseTest;
import com.mu.sview.BeanContextHolder;
import com.mu.sview.CusClassLoader;
import com.mu.sview.service.IUserService;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class BeanInfoTest extends BaseTest {

    @Autowired
    private BeanContextHolder holder;

    @Test
    public void getBeanNames() {
        ApplicationContext context = holder.getContext();
        String[] names = context.getBeanDefinitionNames();
    }

    @Test
    public void getUserService() throws Exception {
        ApplicationContext context = holder.getContext();

        Class<?> targetClass = Class.forName("com.mu.sview.service.IUserService");
        String[] names = context.getBeanNamesForType(targetClass);

        Object obj = context.getBean(names[0]);

        Class<?> beanClass = obj.getClass();

        Field[] fields = beanClass.getDeclaredFields();
        Method[] methods = beanClass.getDeclaredMethods();

        Field nameField = fields[0];
        nameField.setAccessible(true);
        nameField.set(obj, "this is mock value");

        IUserService userService = (IUserService) obj;
        System.out.println(userService.getName());
    }

    @Test
    public void mock_method() throws Exception {

        ApplicationContext context = holder.getContext();
        Class<?> targetClass = Class.forName("com.mu.sview.service.IUserService");
        String[] names = context.getBeanNamesForType(targetClass);
        Object obj = context.getBean(names[0]);

        Class<?> beanClass = obj.getClass();
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.getCtClass(beanClass.getName());

        CtMethod method = cc.getDeclaredMethod("getName");
        method.setBody("return 'hello world';");

        CusClassLoader cusClassLoader = new CusClassLoader();
        Class editedClass = cc.toClass(cusClassLoader, beanClass.getProtectionDomain());

        System.out.println(beanClass == editedClass);
    }


}
