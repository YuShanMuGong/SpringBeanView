package com.mu.sview.controller;

import com.mu.sview.ViewLifecycleProcessor;
import com.mu.sview.dtos.BeanViewDto;
import com.mu.sview.entry.BeanView;
import com.mu.sview.dtos.MethodInvokeDto;
import com.mu.sview.dtos.Response;
import com.mu.sview.util.BeanViewUtil;
import com.mu.sview.util.CollectionUtils;
import com.mu.sview.vo.BeanListVo;
import com.mu.sview.vo.BeanVo;
import com.mu.sview.vo.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beanView")
public class BeanViewController {

    private static final String SPRING_BEAN_PREFIX = "org.springframework";

    @Autowired
    private ViewLifecycleProcessor lifecycleProcessor;

    @RequestMapping("/getBeanList")
    public String getBeanList(String prefix) {
        List<BeanView> dtos = lifecycleProcessor.getSingletonBeanNameList();
        List<BeanView> viewDtos = CollectionUtils.notNullFilter(dtos, dto -> filterCondition(prefix, dto.getId()));
        List<BeanListVo> beanListVos = CollectionUtils.notNullMap(viewDtos, BeanViewUtil::convertListVo);
        return JsonResponse.success(beanListVos).toJson();
    }

    @RequestMapping("/getBeanDetail")
    public String getBeanDetail(String beanId, int deep) {
        BeanViewDto viewDto = lifecycleProcessor.getBeanView(beanId, deep);
        BeanVo vo = BeanViewUtil.convert(viewDto);
        return JsonResponse.success(vo).toJson();
    }

    @RequestMapping("/editFiledValue")
    public String editFiledValue(String beanId, int deep, String fieldName, String valueJson) {
        Response<String> response = lifecycleProcessor.getFieldEditOperator().edit(beanId, deep, fieldName, valueJson);
        if (response.isSuccess()) return JsonResponse.success("更新成功").toJson();
        return JsonResponse.error(response.msg).toJson();
    }

    @RequestMapping("/methodInvoke")
    public String methodInvoke(String beanId, int deep, String desc, String methodName, String paramsJson) {

        MethodInvokeDto invokeDto = new MethodInvokeDto();
        invokeDto.setBeanId(beanId);
        invokeDto.setDeep(deep);
        invokeDto.setDesc(desc);
        invokeDto.setMethodName(methodName);
        invokeDto.setParams(paramsJson);

        Response<String> response = lifecycleProcessor.getMethodInvoker().invoke(invokeDto);
        if (response.isSuccess()) return JsonResponse.success(response.content).toJson();
        return JsonResponse.error(response.msg).toJson();
    }

    private boolean filterCondition(String prefixName, String id) {
        if (id.startsWith(SPRING_BEAN_PREFIX)) {
            return false;
        }
        if (StringUtils.isEmpty(prefixName)) {
            return true;
        }
        return id.startsWith(prefixName);
    }

}
