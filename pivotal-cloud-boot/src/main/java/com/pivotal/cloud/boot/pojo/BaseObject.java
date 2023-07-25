package com.pivotal.cloud.boot.pojo;

import com.pivotal.cloud.boot.constant.Primitives;
import com.pivotal.cloud.boot.utils.BeanCopierUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * @className: com.pivotal.cloud.boot.pojo.BaseObject
 * @projectName: 封装PivotalCloud项目-BaseObject类
 * @module: PivotalCloud项目-BaseObject类，主要位于BaseObject模块的业务场景
 * @content: BaseObject类，主要用于完成BaseObject的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-25 22:16
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public abstract class BaseObject {
    /**
     * 浅度克隆
     *
     * @param targetClazz
     *         目标对象的Class类型
     *
     * @return 目标对象实例
     */
    public <T> T clone(Class<T> targetClazz) {
        try {
            T target = targetClazz.newInstance();
            BeanCopierUtil.copyProperties(this, target);

            return getTarget(target);
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }

    }

    /**
     * 浅度克隆
     *
     * @param target
     *         目标对象实例
     *
     * @return 目标对象实例
     */
    public <T> T clone(T target) {
        try {
            BeanCopierUtil.copyProperties(this, target);
            return getTarget(target);
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }

    /**
     * 深度克隆
     *
     * @param targetClass
     *         目标对象的Class类型
     * @param cloneDirection
     *         深入克隆的方向，具体赋值参见CloneDirection.java常量类
     *
     * @return 目标对象实例
     */
    public <T> T clone(Class<T> targetClass, Integer cloneDirection) {
        try {

            // 先完成基本字段的浅克隆
            T target = targetClass.newInstance();
            BeanCopierUtil.copyProperties(this, target);

            // 完成内部的AbstractObject、List<AbstractObject>类型字段的深度克隆
            Class<?> thisClass = this.getClass();
            List<Field> thisFields = this.builderInstanceList(null, thisClass);

            for (Field thisField : thisFields) {
                thisField.setAccessible(true);

                // 如果判断某个字段是List类型的
                if (!Collection.class.isAssignableFrom(thisField.getType())) {
                    Class<?> sourceClass = thisField.getType();
                    if (       sourceClass == String.class
                            || sourceClass == Long.class
                            || Primitives.Long.equals(sourceClass.toString())
                            || thisField.getType() == Integer.class
                            || Primitives.Integer.equals(sourceClass.toString())
                            || sourceClass == Short.class
                            || Primitives.Short.equals(sourceClass.toString())
                            || sourceClass == Double.class
                            || Primitives.Double.equals(sourceClass.toString())
                            || sourceClass == Float.class
                            || Primitives.Float.equals(sourceClass.toString())
                            || sourceClass == BigDecimal.class
                            || sourceClass == Boolean.class
                            || Primitives.Boolean.equals(sourceClass.toString())
                            || sourceClass == Date.class
                            || sourceClass == Character.class
                            || Primitives.Character.equals(sourceClass.toString())
                            || sourceClass == Byte.class
                            || Primitives.Byte.equals(sourceClass.toString())
                            || sourceClass == java.sql.Date.class) {
                        continue;
                    }
                    // 判断某个字段是否AbstractObject类型的
                    try {
                        if (!(thisField.getType().newInstance() instanceof BaseObject)) {
                            continue;
                        }
                    } catch (Exception e) {
                        if (e instanceof InstantiationException) {
                            continue;
                        }
                        throw new RuntimeException("error", e);
                    }
                    BaseObject sourceObject = (BaseObject) (thisField.get(this));
                    if (sourceObject == null) {
                        continue;
                    }

                    // 获取要克隆的目标类型
                    Field targetField = null;
                    try {
                        targetField = getTargetClassField(thisField, targetClass);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    if (targetField != null) {
                        Class<?> cloneTargetClazz = targetField.getType();
                        BaseObject clonedObj = (BaseObject) sourceObject.clone(cloneTargetClazz, cloneDirection);
                        // 获取设置克隆好的对象的方法名称
                        String name = thisField.getName();
                        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                        // getMethod()方法可以获取当前类与父类中所有的public方法
                        Method setFieldMethod = targetClass.getMethod(setMethodName, targetField.getType());
                        setFieldMethod.invoke(target, clonedObj);
                    }
                } else {
                    Collection<?> list = (Collection<?>) thisField.get(this);
                    if (list == null || list.size() == 0) {
                        continue;
                    }

                    // 获取List集合中的泛型类型
                    Field targetField = null;
                    try {
                        targetField = getTargetClassField(thisField, targetClass);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    if (targetField != null) {
                        Class<?> cloneTarget = getTargetListGenericType(targetField);
                        // 获取要克隆的目标类型
                        // 将list集合克隆到目标list集合中去
                        Collection clonedList = (Collection) thisField.get(this).getClass().newInstance();
                        cloneList(list, clonedList, cloneTarget, cloneDirection);

                        // 获取设置克隆好的list的方法名称
                        String name = thisField.getName();
                        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                        // getMethod()方法可以获取当前类与父类中所有的public方法
                        Method setFieldMethod = targetClass.getMethod(setMethodName, targetField.getType());
                        setFieldMethod.invoke(target, clonedList);
                    }
                }

            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }

    /**
     * 递归获取当前类以及父类中的字段
     *
     * @param thisFields
     *         当前类以及父类中的所有字段
     * @param thisClass
     *         原始类Class对象
     *
     * @return
     */
    public List<Field> builderInstanceList(List<Field> thisFields, Class<?> thisClass) {
        if (thisFields == null) {
            thisFields = new ArrayList<>(Arrays.asList(thisClass.getDeclaredFields()));
        } else {
            thisFields.addAll(Arrays.asList(thisClass.getDeclaredFields()));
        }

        if (!thisClass.getSuperclass().getTypeName().equals(BaseObject.class.getTypeName())) {
            this.builderInstanceList(thisFields, thisClass.getSuperclass());
        }
        return thisFields;
    }

    /**
     * 如果目标有继承父类需要递归获取目标字段
     *
     * @param thisField
     *         源对象中的某个字段
     * @param targetClass
     *         目标对象的class类型
     *
     * @return
     *
     * @throws NoSuchFieldException
     */
    private Field getTargetClassField(Field thisField, Class<?> targetClass) throws NoSuchFieldException {
        Field targetField = null;
        try {
            targetField = targetClass.getDeclaredField(thisField.getName());
        } catch (NoSuchFieldException e) {
            // 目标类有可能没有继承AbstractObject类
            if (targetClass.getSuperclass() != null) {
                String targetSuperClazzTypeName = targetClass.getSuperclass().getTypeName();
                if (!targetSuperClazzTypeName.equals(Object.class.getTypeName()) && !targetSuperClazzTypeName.equals(BaseObject.class.getTypeName())) {
                    // 递归
                    targetField = getTargetClassField(thisField, targetClass.getSuperclass());
                }
            }

            if (targetField == null) {
                throw e;
            }

        }
        return targetField;

    }

    /**
     * 将一个List克隆到另外一个List
     *
     * @param sourceList
     * @param targetList
     * @param cloneTargetClass
     * @param cloneDirection
     */
    private void cloneList(Collection sourceList, Collection targetList, Class cloneTargetClass, Integer cloneDirection) {
        for (Object object : sourceList) {
            if (object instanceof BaseObject) {
                BaseObject targetObject = (BaseObject) object;
                BaseObject clonedObject = (BaseObject) targetObject.clone(cloneTargetClass, cloneDirection);
                targetList.add(clonedObject);
            } else {
                // 非List<? extends AbstractObject>类型的集合字段，直接复用原对象的字段值
                targetList.add(object);
            }
        }
    }

    /**
     * 获取List集合的泛型类型
     *
     * @param targetField
     *
     * @return
     */
    private Class<?> getTargetListGenericType(Field targetField) {
        Type genericType = targetField.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameter = (ParameterizedType) genericType;
            return (Class<?>) parameter.getActualTypeArguments()[0];
        }
        return null;
    }

    /**
     * 浅度克隆时原对象List属性的处理
     *
     * @param target
     *
     * @return
     */
    private <T> T getTarget(T target) throws Exception {
        Class<?> targetClazz = target.getClass();
        Field[] fields = targetClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            // 如果判断某个字段是List类型的
            if (field.getType() != List.class) {
                continue;
            }
            List<?> list = (List<?>) field.get(target);
            if (list == null || list.size() == 0) {
                continue;
            }

            Class<?> targetListGenericTypeClass = getTargetListGenericType(field);

            if (!checkedObjectClass(targetListGenericTypeClass)) {
                continue;
            }

            String name = field.getName();
            String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

            Method setFieldMethod = targetClazz.getMethod(setMethodName, field.getType());
            setFieldMethod.invoke(target, new ArrayList());
        }

        return target;
    }

    /**
     * 判断某个Class的对象类是否继承了AbstractObject
     *
     * @param objectClass
     *
     * @return
     */
    private boolean checkedObjectClass(Class objectClass) {
        // 目标类有可能没有继承AbstractObject类
        if (objectClass.getSuperclass() != null) {
            String superTypeName = objectClass.getSuperclass().getTypeName();
            if (superTypeName.equals(Object.class.getTypeName())) {
                return false;
            }
            if (superTypeName.equals(BaseObject.class.getTypeName())) {
                return true;
            } else {
                return checkedObjectClass(objectClass.getSuperclass());
            }
        }
        return false;
    }
}
