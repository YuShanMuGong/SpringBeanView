package com.mu.sview;

import com.mu.sview.entry.BeanView;
import com.mu.sview.entry.BeanViewNode;
import com.mu.sview.operators.FieldEditOperator;
import com.mu.sview.operators.MethodInvoker;
import com.mu.sview.util.BeanViewUtil;
import com.mu.sview.util.CollectionUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.support.DefaultLifecycleProcessor;

import java.util.*;

public class ViewLifecycleProcessor extends DefaultLifecycleProcessor {

    private ConfigurableBeanFactory beanFactory;

    private Map<String, BeanViewNode> singletonBeanNodeMap = new HashMap<>();

    private FieldEditOperator fieldEditOperator;
    private MethodInvoker methodInvoker;

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

    public List<BeanView> getSingletonBeanNameList() {
        List<BeanViewNode> nodes = new ArrayList<>(singletonBeanNodeMap.values());
        return CollectionUtils.notNullMap(nodes, BeanViewNode::getData);
    }

    public BeanView getBeanView(String id, int deep) {
        BeanViewNode node = singletonBeanNodeMap.get(id);
        for (int i = 1; i <= deep; i++) {
            if (node.level == deep || !node.haveSuper) {
                return node.data;
            }
            Object data = node.data.getBeanObj();
            BeanView newLevelView = BeanViewUtil.buildBeanViewDto(id, data, i);
            BeanViewNode newLevelNode = BeanViewNode.build(newLevelView, i);
            node.setSuperNode(newLevelNode);
            node = newLevelNode;
        }
        return node.data;
    }

    public FieldEditOperator getFieldEditOperator() {
        if (fieldEditOperator == null) {
            this.fieldEditOperator = new FieldEditOperator(beanFactory);
        }
        return fieldEditOperator;
    }

    public MethodInvoker getMethodInvoker() {
        if (methodInvoker == null) {
            this.methodInvoker = new MethodInvoker(beanFactory);
        }
        return methodInvoker;
    }


    private void initSingletonIdList(ConfigurableBeanFactory beanFactory) {
        String[] names = beanFactory.getSingletonNames();
        for (String id : names) {
            Object obj = beanFactory.getSingleton(id);
            BeanView dto = BeanViewUtil.buildBeanViewDto(id, obj, 0);
            singletonBeanNodeMap.put(id, BeanViewNode.build(dto, 0));
        }
    }

}
