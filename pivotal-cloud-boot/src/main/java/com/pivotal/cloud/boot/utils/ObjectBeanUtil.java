package com.pivotal.cloud.boot.utils;

import com.github.pagehelper.Page;
import com.pivotal.cloud.boot.pojo.BaseObject;
import com.pivotal.cloud.boot.pojo.PageResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @className: com.pivotal.cloud.boot.utils.ObjectBeanUtil
 * @projectName: 封装PivotalCloud项目-ObjectBeanUtil类
 * @module: PivotalCloud项目-ObjectBeanUtil类，主要位于ObjectBeanUtil模块的业务场景
 * @content: ObjectBeanUtil类，主要用于完成ObjectBeanUtil的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-27 14:31
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@SuppressWarnings("ALL")
public class ObjectBeanUtil {


    public static <T> List<T> convertList(List<? extends BaseObject> sourceList, Class<T> targetClazz) {
        if (sourceList == null) {
            return null;
        } else {
            Iterator var3;
            BaseObject sourceObject;
            if (sourceList instanceof Page) {
                Page<T> targetPage = getTargetPage(sourceList);
                var3 = sourceList.iterator();

                while(var3.hasNext()) {
                    sourceObject = (BaseObject)var3.next();
                    targetPage.add(sourceObject.clone(targetClazz));
                }

                return targetPage;
            } else {
                List<T> targetList = new ArrayList();
                var3 = sourceList.iterator();

                while(var3.hasNext()) {
                    sourceObject = (BaseObject)var3.next();
                    targetList.add(sourceObject.clone(targetClazz));
                }

                return targetList;
            }
        }
    }

    public static <T> List<T> convertList(List<? extends BaseObject> sourceList, Class<T> targetClazz, Integer cloneDirection) {
        if (sourceList == null) {
            return null;
        } else {
            Iterator var4;
            BaseObject sourceObject;
            if (sourceList instanceof Page) {
                Page<T> targetPage = getTargetPage(sourceList);
                var4 = sourceList.iterator();

                while(var4.hasNext()) {
                    sourceObject = (BaseObject)var4.next();
                    targetPage.add(sourceObject.clone(targetClazz, cloneDirection));
                }

                return targetPage;
            } else {
                List<T> targetList = new ArrayList();
                var4 = sourceList.iterator();

                while(var4.hasNext()) {
                    sourceObject = (BaseObject)var4.next();
                    targetList.add(sourceObject.clone(targetClazz, cloneDirection));
                }

                return targetList;
            }
        }
    }

    public static <T> PageResponse<T> convertPage(PageResponse<? extends BaseObject> source, Class<T> targetClazz) {
        if (source == null) {
            return null;
        } else {
            List<? extends BaseObject> sourceList = source.getElements();
            List<T> targetList = convertList(sourceList, targetClazz);
            PageResponse<T> target = new PageResponse(targetList);
            if (!(targetList instanceof Page)) {
                target.setPageNumber(source.getPageNumber());
                target.setPageSize(source.getPageSize());
                target.setPageCount(source.getPageCount());
                target.setPageTotal(source.getPageTotal());
                target.setPageCurrent(source.getPageCurrent());
            }

            return target;
        }
    }

    public static <T> PageResponse<T> convertPage(PageResponse<? extends BaseObject> source, Class<T> targetClazz, Integer cloneDirection) {
        if (source == null) {
            return null;
        } else {
            List<? extends BaseObject> sourceList = source.getElements();
            List<T> targetList = convertList(sourceList, targetClazz, cloneDirection);
            PageResponse<T> target = new PageResponse(targetList);
            if (!(targetList instanceof Page)) {
                target.setPageNumber(source.getPageNumber());
                target.setPageSize(source.getPageSize());
                target.setPageCount(source.getPageCount());
                target.setPageTotal(source.getPageTotal());
                target.setPageCurrent(source.getPageCurrent());
            }
            return target;
        }
    }

    /**
     *
     * @param sourceList
     * @param <T>
     * @return 返回结果
     */
    private static <T> Page<T> getTargetPage(List<? extends BaseObject> sourceList) {
        Page<? extends BaseObject> source = (Page)sourceList;
        Page<T> target = new Page(source.getPageNum(), source.getPageSize(), source.isCount());
        target.setStartRow(source.getStartRow());
        target.setEndRow(source.getEndRow());
        target.setTotal(source.getTotal());
        target.setPages(source.getPages());
        target.setReasonable(source.getReasonable());
        target.setPageSizeZero(source.getPageSizeZero());
        target.setCountColumn(source.getCountColumn());
        target.setOrderBy(source.getOrderBy());
        target.setOrderByOnly(source.isOrderByOnly());
        return target;
    }
}
