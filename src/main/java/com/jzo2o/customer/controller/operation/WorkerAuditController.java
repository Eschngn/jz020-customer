package com.jzo2o.customer.controller.operation;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.dto.request.WorkerCertificationAuditPageQueryReqDTO;
import com.jzo2o.customer.model.dto.response.WorkerCertificationAuditResDTO;
import com.jzo2o.customer.service.IWorkerCertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource; /**
 * @author Wilson
 * @Description: TODO
 * @date 2024/11/29 18:45
 */
@RestController("operationWorkerAuditController")
@RequestMapping("/operation/worker-certification-audit")
@Api(tags = "运营端 - 服务人员审核信息相关接口")
public class WorkerAuditController {

    @Resource
    private IWorkerCertificationService workerCertificationService;

    /**
     * 运营端 - 分页查询认证审核列表
     * @param workerCertificationAuditPageQueryReqDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询认证审核列表")
    public PageResult<WorkerCertificationAuditResDTO> page(WorkerCertificationAuditPageQueryReqDTO workerCertificationAuditPageQueryReqDTO) {
        return workerCertificationService.page(workerCertificationAuditPageQueryReqDTO);
    }

    /**
     * 运营端 - 审核服务人员认证信息
     * @param id
     * @param certificationStatus
     * @param rejectReason
     */
    @PutMapping("/audit/{id}")
    @ApiOperation("审核服务人员认证信息")
    public void audit(@PathVariable("id") Long id,@RequestParam("certificationStatus") Integer certificationStatus, @RequestParam("rejectReason") String rejectReason){
        workerCertificationService.audit(id,certificationStatus,rejectReason);
    }
}
