package com.mu.sview;

import com.mu.sview.dtos.BeanViewDto;
import com.mu.sview.operators.FieldEditOperator;
import com.mu.sview.util.BeanViewUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.support.DefaultLifecycleProcessor;

import java.util.*;

public class ViewLifecycleProcessor extends DefaultLifecycleProcessor {

    private ConfigurableBeanFactory beanFactory;

    private Map<String, BeanViewDto> singletonBeanViewDtoMap = new HashMap<>();

    private FieldEditOperator fieldEditOperator;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        beanCreateComplete();
    }

    private void beanCreateComplete() {
        initSingletonIdList(beanFactory);
    }

    public List<String> getBeanDependObjNames(String beanName) {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) beanFactory;
        return Arrays.asList(registry.getDependenciesForBean(beanName));
    }

    public List<BeanViewDto> getSingletonBeanNameList() {
        return new ArrayList<>(singletonBeanViewDtoMap.values());
    }

    public BeanViewDto getBeanViewDto(String id) {
        return singletonBeanViewDtoMap.get(id);
    }

    public FieldEditOperator getFieldEditOperator() {
        if (fieldEditOperator == null) {
            this.fieldEditOperator = new FieldEditOperator(beanFactory);
        }
        return fieldEditOperator;
    }

    private void initSingletonIdList(ConfigurableBeanFactory beanFactory) {
        String[] names = beanFactory.getSingletonNames();
        for (String id : names) {
            Object obj = beanFactory.getSingleton(id);
            singletonBeanViewDtoMap.put(id, BeanViewUtil.buildBeanViewDto(id, obj));
        }
    }

}
