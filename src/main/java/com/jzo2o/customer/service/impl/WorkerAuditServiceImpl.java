package com.jzo2o.customer.service.impl;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.mapper.WorkerCertificationMapper;
import com.jzo2o.customer.model.domain.WorkerAudit;
import com.jzo2o.customer.mapper.WorkerAuditMapper;
import com.jzo2o.customer.model.dto.request.WorkerCertificationAuditPageQueryReqDTO;
import com.jzo2o.customer.model.dto.response.WorkerCertificationAuditResDTO;
import com.jzo2o.customer.service.IWorkerAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.mysql.utils.PageHelperUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务人员审核信息表 服务实现类
 * </p>
 *
 * @author wilson
 * @since 2024-11-29
 */
@Service
public class WorkerAuditServiceImpl extends ServiceImpl<WorkerAuditMapper, WorkerAudit> implements IWorkerAuditService {
}
