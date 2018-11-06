package com.mu.sview.dtos;

import com.mu.sview.util.ReflectionUtil;

public class BeanViewNode {

    private BeanViewNode superNode;
    public final BeanView data;
    public final boolean haveSuper;
    public final int level;

    public static BeanViewNode build(BeanView data, int level) {
        boolean superFlag = ReflectionUtil.haveSuperClass(data.getBeanClass());
        return new BeanViewNode(data,superFlag,level);
    }

    public BeanViewNode getSuperNode() {
        return superNode;
    }

    public void setSuperNode(BeanViewNode superNode) {
        this.superNode = superNode;
    }

    public BeanView getData() {
        return data;
    }

    public boolean isHaveSuper() {
        return haveSuper;
    }

    public int getLevel() {
        return level;
    }

    private BeanViewNode(BeanView data, boolean haveSuper, int level) {
        this.data = data;
        this.haveSuper = haveSuper;
        this.level = level;
    }
}
