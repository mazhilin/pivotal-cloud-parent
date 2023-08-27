package com.pivotal.cloud.boot.pojo;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: com.pivotal.cloud.boot.pojo.PageResponse
 * @projectName: 封装PivotalCloud项目-PageResponse类
 * @module: PivotalCloud项目-PageResponse类，主要位于PageResponse模块的业务场景
 * @content: PageResponse类，主要用于完成PageResponse的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-25 23:51
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
@ApiModel(value = "PageResponse<T>",description = "分页Response响应结果统一实体")
public final class PageResponse<T> extends AbstractList<T> implements Serializable {

    private static final long serialVersionUID = -1594861375090801138L;
    /**
     * 总记录数
     */
    private long pageTotal;

    /**
     * 数据集合
     */
    private List<T> elements;

    /**
     * 当前页码数量
     */
    private int pageNumber;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总页码数
     */
    private int pageCount;

    /**
     * 当前页的数量
     */
    private int pageCurrent;

    public PageResponse() {
        elements = Lists.newArrayList();
    }

    public PageResponse(List<T> list) {
        if (list != null) {
            if (list instanceof Page) {
                Page<T> page = (Page)list;
                this.pageNumber = page.getPageNum();
                this.pageSize = page.getPageSize();
                this.pageTotal = page.getTotal();
                this.pageTotal = page.getPages();
                this.elements = page;
                this.pageCurrent = page.size();
            } else {
                this.pageNumber = 1;
                this.pageSize = list.size();
                this.pageTotal = list.size();
                this.pageCount = 1;
                this.elements = list;
                this.pageCurrent = list.size();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param index
     *
     * @throws IndexOutOfBoundsException
     *         {@inheritDoc}
     */
    @Override
    public T get(int index) {
        return elements.get(index);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
