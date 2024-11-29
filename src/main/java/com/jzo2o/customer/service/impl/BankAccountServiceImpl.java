package com.jzo2o.customer.service.impl;

import com.jzo2o.common.expcetions.BadRequestException;
import com.jzo2o.common.utils.BeanUtils;
import com.jzo2o.common.utils.ObjectUtils;
import com.jzo2o.customer.model.domain.BankAccount;
import com.jzo2o.customer.mapper.BankAccountMapper;
import com.jzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;
import com.jzo2o.customer.model.dto.response.BankAccountResDTO;
import com.jzo2o.customer.service.IBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.mvc.utils.UserContext;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人银行账户 服务实现类
 * </p>
 *
 * @author wilson
 * @since 2024-11-29
 */
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements IBankAccountService {

    /**
     * 服务端 - 新增或更新银行账户
     * @param bankAccountUpsertReqDTO
     * @param type
     */
    @Override
    public BankAccount saveOrUpdate(BankAccountUpsertReqDTO bankAccountUpsertReqDTO, Integer type) {
        Long userId = UserContext.currentUserId();
        BankAccount bankAccount = BeanUtils.toBean(bankAccountUpsertReqDTO, BankAccount.class);
        bankAccount.setUserType(type);
        bankAccount.setUserId(userId);
        boolean saveOrUpdate = saveOrUpdate(bankAccount);
        if(!saveOrUpdate){
            throw new BadRequestException("新增或更新银行账户失败!");
        }
        return bankAccount;
    }

    /**
     * 服务端 - 获取当前用户银行账户
     * @param type
     * @return
     */
    @Override
    public BankAccountResDTO currentUserBankAccount(Integer type) {
        // 获取当前登录用户 id
        Long userId = UserContext.currentUserId();
        BankAccount bankAccount = lambdaQuery().eq(BankAccount::getUserId, userId).one();
        if(ObjectUtils.isNull(bankAccount)){
            throw new BadRequestException("当前用户还未开户");
        }
        BankAccountResDTO bankAccountResDTO = BeanUtils.toBean(bankAccount, BankAccountResDTO.class);
        bankAccountResDTO.setId(userId);
        bankAccountResDTO.setType(type);
        return bankAccountResDTO;
    }
}
