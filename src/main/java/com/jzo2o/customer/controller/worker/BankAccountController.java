package com.jzo2o.customer.controller.worker;


import com.jzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;
import com.jzo2o.customer.model.dto.response.BankAccountResDTO;
import com.jzo2o.customer.service.IBankAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 个人银行账户 前端控制器
 * </p>
 *
 * @author wilson
 * @since 2024-11-29
 */
@RestController("workerBankAccountController")
@RequestMapping("/worker/bank-account")
@Api(tags = "服务端 - 银行账户相关接口")
public class BankAccountController {
    @Resource
    private IBankAccountService bankAccountService;

    @PostMapping
    @ApiOperation("新增或更新银行账户")
    public void saveOrUpdate(@RequestBody BankAccountUpsertReqDTO bankAccountUpsertReqDTO){
        // 服务端
        Integer type=2;
        bankAccountService.saveOrUpdate(bankAccountUpsertReqDTO,type);
    }

    @GetMapping("/currentUserBankAccount")
    @ApiOperation("获取当前用户银行账户")
    public BankAccountResDTO currentUserBankAccount(){
        // 服务端
        Integer type=2;
        return bankAccountService.currentUserBankAccount(type);
    }
}
