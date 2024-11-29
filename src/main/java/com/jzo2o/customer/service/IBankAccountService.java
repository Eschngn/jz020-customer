package com.jzo2o.customer.service;

import com.jzo2o.customer.model.domain.BankAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;
import com.jzo2o.customer.model.dto.response.BankAccountResDTO;

/**
 * <p>
 * 个人银行账户 服务类
 * </p>
 *
 * @author wilson
 * @since 2024-11-29
 */
public interface IBankAccountService extends IService<BankAccount> {

    /**
     * 服务端 - 新增或更新银行账户
     * @param bankAccountUpsertReqDTO
     * @param type
     */
    BankAccount saveOrUpdate(BankAccountUpsertReqDTO bankAccountUpsertReqDTO, Integer type);

    /**
     * 服务端 - 获取当前用户银行账户
     * @return
     */
    BankAccountResDTO currentUserBankAccount(Integer type);
}
