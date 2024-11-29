package com.jzo2o.customer.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机构审核信息表
 * </p>
 *
 * @author wilson
 * @since 2024-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("agency_audit")
@ApiModel(value="AgencyAudit对象", description="机构审核信息表")
public class AgencyAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "审核状态，0：未审核，1：已审核")
    private Integer auditStatus;

    @ApiModelProperty(value = "拒绝原因")
    private String rejectReason;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核人id")
    private Long auditorId;

    @ApiModelProperty(value = "审核人姓名")
    private String auditorName;


}
