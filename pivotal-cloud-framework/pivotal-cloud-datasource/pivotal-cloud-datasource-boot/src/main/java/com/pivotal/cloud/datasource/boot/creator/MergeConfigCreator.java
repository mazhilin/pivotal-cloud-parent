package com.pivotal.cloud.datasource.boot.creator;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.boot.creator.MergeConfigCreator
 * @projectName: pivotalCloud
 * @className: MergeConfigCreator
 * @title: 封装pivotalCloud项目-MergeConfigCreator类
 * @content: MergeConfigCreator
 * @description: pivotalCloud项目-MergeConfigCreator类,主要用作MergeConfigCreator。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 14:07
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@AllArgsConstructor
public class MergeConfigCreator<C, T> {
    private final String configName;

    private final Class<C> configClazz;

    private final Class<T> targetClazz;

    @SneakyThrows
    public T create(C global, C item) {
        if (configClazz.equals(targetClazz) && global == null) {
            return (T) item;
        }
        T result = targetClazz.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(configClazz, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : propertyDescriptors) {
            Class<?> propertyType = pd.getPropertyType();
            if (Properties.class == propertyType) {
                mergeProperties(global, item, result, pd);
            } else {
                mergeBasic(global, item, result, pd);
            }
        }
        return result;
    }

    @SneakyThrows
    private void mergeProperties(C global, C item, T result, PropertyDescriptor pd) {
        String name = pd.getName();
        Method readMethod = pd.getReadMethod();
        Properties itemValue = (Properties) readMethod.invoke(item);
        Properties globalValue = (Properties) readMethod.invoke(global);
        Properties properties = new Properties();
        if (globalValue != null) {
            properties.putAll(globalValue);
        }
        if (itemValue != null) {
            properties.putAll(itemValue);
        }
        if (properties.size() > 0) {
            setField(result, name, properties);
        }
    }

    @SneakyThrows
    private void mergeBasic(C global, C item, T result, PropertyDescriptor pd) {
        String name = pd.getName();
        Method readMethod = pd.getReadMethod();
        Object value = readMethod.invoke(item);
        if (value == null) {
            value = readMethod.invoke(global);
        }
        if (value != null) {
            setField(result, name, value);
        }
    }

    private void setField(T result, String name, Object value) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, targetClazz);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            writeMethod.invoke(result, value);
        } catch (IntrospectionException | ReflectiveOperationException e) {
            Field field = null;
            try {
                field = targetClazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(result, value);
            } catch (ReflectiveOperationException e1) {
                log.warn("dynamic-datasource set {} [{}] failed,please check your config or update {}  to the latest version", configName, name, configName);
            } finally {
                if (field != null && field.isAccessible()) {
                    field.setAccessible(false);
                }
            }
        } catch (Exception ee) {
            log.warn("dynamic-datasource set {} [{}] failed,please check your config", configName, name, ee);
        }
    }

}
