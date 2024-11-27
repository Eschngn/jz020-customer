package com.jzo2o.customer.controller.consumer;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.customer.model.domain.AddressBook;
import com.jzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.jzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;
import com.jzo2o.customer.model.dto.response.AddressResDTO;
import com.jzo2o.customer.service.IAddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @Description: TODO
 * @date 2024/11/27 14:32
 */
@RestController("addressBookController")
@RequestMapping("/consumer/address-book")
@Api(tags = "用户端 - 地址薄相关接口")
public class AddressBookController {
    @Resource
    private IAddressBookService addressBookService;
    /**
     * 新增地址薄
     * @param addressBookUpsertReqDTO
     */
    @PostMapping
    @ApiOperation("新增地址薄")
    public AddressBook add(@RequestBody AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        return addressBookService.addAddress(addressBookUpsertReqDTO);
    }

    @GetMapping("/page")
    @ApiOperation("地址簿分页查询")
    public PageResult<AddressResDTO> page(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO){
        return addressBookService.pageAddress(addressBookPageQueryReqDTO);
    }
}
