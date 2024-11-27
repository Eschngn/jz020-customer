package com.jzo2o.customer.controller.consumer;

import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
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
import java.util.List;

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

    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址簿")
    public AddressBook getById(@PathVariable("id")Long id){
        return addressBookService.getById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("编辑地址薄")
    public AddressBook update(@PathVariable("id") Long id, @RequestBody AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        return addressBookService.update(id, addressBookUpsertReqDTO);
    }

    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public AddressBook setDefault(@RequestParam("id") Long id, @RequestParam("flag") Integer flag){
        return addressBookService.setDefault(id, flag);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除地址")
    public void deleteBatch(@RequestBody List<Long> ids){
        addressBookService.removeByIds(ids);
    }
    @GetMapping("/defaultAddress")
    @ApiOperation("获取默认地址")
    public AddressBookResDTO getDefaultAddress(){
        return addressBookService.getDefaultAddress();
    }
}
