package com.jzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.domain.AgencyCertification;
import com.jzo2o.customer.model.dto.AgencyCertificationUpdateDTO;
import com.jzo2o.customer.model.dto.request.AgencyCertificationAuditAddReqDTO;
import com.jzo2o.customer.model.dto.request.AgencyCertificationAuditPageQueryReqDTO;
import com.jzo2o.customer.model.dto.response.AgencyCertificationAuditResDTO;
import com.jzo2o.customer.model.dto.response.AgencyCertificationResDTO;

/**
 * <p>
 * 机构认证信息表 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-09-06
 */
public interface IAgencyCertificationService extends IService<AgencyCertification> {


    /**
     * 根据机构id更新
     *
     * @param agencyCertificationUpdateDTO 机构认证更新模型
     */
    void updateByServeProviderId(AgencyCertificationUpdateDTO agencyCertificationUpdateDTO);


    /**
     * 机构端 - 提交认证申请
     * @param agencyCertificationAuditAddReqDTO
     */
    AgencyCertification submitAuth(AgencyCertificationAuditAddReqDTO agencyCertificationAuditAddReqDTO);

    /**
     * 运营端 - 机构认证信息分页查询
     * @param agencyCertificationAuditPageQueryReqDTO
     * @return
     */
    PageResult<AgencyCertificationAuditResDTO> page(AgencyCertificationAuditPageQueryReqDTO agencyCertificationAuditPageQueryReqDTO);

    /**
     * 运营端 - 审核机构认证信息
     * @param id
     * @param certificationStatus
     * @param rejectReason
     */
    void audit(Long id, Integer certificationStatus, String rejectReason);
}
