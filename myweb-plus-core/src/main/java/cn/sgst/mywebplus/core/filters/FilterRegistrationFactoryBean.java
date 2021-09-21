package cn.sgst.mywebplus.core.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

/**
 * FilterRegistrationBean FactoryBean
 *
 * @author: fli
 * @email: fli@sstir.cn
 * @date: 2021/9/16 15:35
 */
public class FilterRegistrationFactoryBean<T extends Filter> implements FactoryBean<FilterRegistrationBean>,BeanNameAware {

    private final T filter;

    private String beanName;

    @Getter
    @Setter
    private String[] urlPatterns;
    @Getter
    @Setter
    private int order;

    public FilterRegistrationFactoryBean(T filter) {
        this.filter = filter;
    }

    @Override
    public FilterRegistrationBean getObject() {
        FilterRegistrationBean<T> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPatterns);
        registration.setName(beanName);
        registration.setOrder(order);
        return registration;
    }

    @Override
    public Class<?> getObjectType() {
        return FilterRegistrationBean.class;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
