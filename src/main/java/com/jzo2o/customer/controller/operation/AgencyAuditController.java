package com.jzo2o.customer.controller.operation;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.dto.request.AgencyCertificationAuditPageQueryReqDTO;
import com.jzo2o.customer.model.dto.response.AgencyCertificationAuditResDTO;
import com.jzo2o.customer.service.IAgencyCertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @Description: TODO
 * @date 2024/11/30 16:40
 */
@RestController("operationAgencyAuditController")
@RequestMapping("/operation/agency-certification-audit")
@Api(tags = "运营端 - 机构审核信息相关接口")
public class AgencyAuditController {
    @Resource
    private IAgencyCertificationService agencyCertificationService;
    @GetMapping("/page")
    @ApiOperation("分页查询认证审核列表")
    public PageResult<AgencyCertificationAuditResDTO> page(AgencyCertificationAuditPageQueryReqDTO agencyCertificationAuditPageQueryReqDTO) {
        return agencyCertificationService.page(agencyCertificationAuditPageQueryReqDTO);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation("审核机构认证信息")
    public void audit(@PathVariable("id") Long id, @RequestParam("certificationStatus") Integer certificationStatus, @RequestParam("rejectReason") String rejectReason){
        agencyCertificationService.audit(id,certificationStatus,rejectReason);
    }

}
