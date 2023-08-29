package com.pivotal.cloud.common.domain.object.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pivotal.cloud.boot.constant.Formats;
import com.pivotal.cloud.boot.pojo.BaseObject;
import com.pivotal.cloud.common.domain.object.Entity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @className: com.pivotal.cloud.common.domain.object.entity.BaseEntity
 * @projectName: 封装PivotalCloud项目-BaseEntity类
 * @module: PivotalCloud项目-BaseEntity类，主要位于BaseEntity模块的业务场景
 * @content: BaseEntity类，主要用于完成BaseEntity的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-11 00:21
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity extends BaseObject implements Entity {

    /**
     * 版本号
     */
    @Getter
    @Setter
    @TableField(value = "`version`")
    private Integer version;

    /**
     * 创建人
     */
    @Getter
    @Setter
    @TableField(value = "`creator_id`", fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    @TableField(value = "`created_time`", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = Formats.DATETIME)
    private Date createdTime;


    /**
     * 更新人
     */
    @Getter
    @Setter
    @TableField(value = "`updator_id`", fill = FieldFill.INSERT_UPDATE)
    private Long updatorId;


    /**
     * 更新时间
     */
    @Getter
    @Setter
    @TableField(value = "`updated_time`", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = Formats.DATETIME)
    private Date updatedTime;

    /**
     * 逻辑删除
     */
    @Getter
    @Setter
    @TableField(value = "`deleted`")
    private Integer deleted;

    /**
     * 版本号
     */
    @Getter
    @Setter
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Getter
    @Setter
    @TableField(value = "`remark`")
    private String remark;
}
