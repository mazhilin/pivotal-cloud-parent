package com.pivotal.cloud.boot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.boot.pojo.PageRequest
 * @projectName: 封装PivotalCloud项目-PageRequest类
 * @module: PivotalCloud项目-PageRequest类，主要位于PageRequest模块的业务场景
 * @content: PageRequest类，主要用于完成PageRequest的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-25 23:50
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
@ApiModel(value = "PageRequest", description = "分页Request请求参数基础实体类")
public abstract class PageRequest implements Serializable {
    private static final long serialVersionUID = -5806027488468638495L;

    /**
     * 每页页码数
     */
    @ApiModelProperty(value = "pageNumber", name = "pageNumber", notes = "每页页码数", example = "1", required = true)
    private Integer pageNumber;

    /**
     * 每页条目数
     */
    @ApiModelProperty(value = "pageSize", name = "pageSize", notes = "每页条目数", example = "1", required = true)
    private Integer pageSize;
}
