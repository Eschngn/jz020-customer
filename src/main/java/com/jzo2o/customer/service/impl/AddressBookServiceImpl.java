package com.jzo2o.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.api.customer.dto.response.AddressBookResDTO;
import com.jzo2o.api.publics.MapApi;
import com.jzo2o.api.publics.dto.response.LocationResDTO;
import com.jzo2o.common.expcetions.BadRequestException;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.common.utils.*;
import com.jzo2o.customer.mapper.AddressBookMapper;
import com.jzo2o.customer.model.domain.AddressBook;
import com.jzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.jzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;
import com.jzo2o.customer.model.dto.response.AddressResDTO;
import com.jzo2o.customer.service.IAddressBookService;
import com.jzo2o.mvc.utils.UserContext;
import com.jzo2o.mysql.utils.PageHelperUtils;
import com.jzo2o.mysql.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地址薄 服务实现类
 * </p>
 *
 * @author itcast
 * @since 2023-07-06
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

    @Resource
    private MapApi mapApi;
    @Override
    public List<AddressBookResDTO> getByUserIdAndCity(Long userId, String city) {

        List<AddressBook> addressBooks = lambdaQuery()
                .eq(AddressBook::getUserId, userId)
                .eq(AddressBook::getCity, city)
                .list();
        if(CollUtils.isEmpty(addressBooks)) {
            return new ArrayList<>();
        }
        return BeanUtils.copyToList(addressBooks, AddressBookResDTO.class);
    }

    /**
     * 新增地址簿
     * @param addressBookUpsertReqDTO
     * @return
     */
    @Override
    public AddressBook addAddress(AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        // 获取经纬度
        String address = addressBookUpsertReqDTO.getAddress();
        LocationResDTO locationByAddress = mapApi.getLocationByAddress(address);
        String location = locationByAddress.getLocation();
        Double lon=Double.valueOf(location.split(",")[0]);
        Double lat=Double.valueOf(location.split(",")[1]);

        AddressBook addressBook = BeanUtils.toBean(addressBookUpsertReqDTO, AddressBook.class);
        addressBook.setLon(lon);
        addressBook.setLat(lat);
        Long userId = UserContext.currentUserId();
        addressBook.setUserId(userId);
        // 默认地址处理
        if(addressBook.getIsDefault().equals(1)){
            AddressBook defaultAddress = lambdaQuery().eq(AddressBook::getUserId, userId)
                    .eq(AddressBook::getIsDefault, 1)
                    .one();
            // 存在默认地址,将其设置为非默认
            if(ObjectUtils.isNotNull(defaultAddress)){
                defaultAddress.setIsDefault(0);
                updateById(defaultAddress);
            }
        }
        boolean saved = save(addressBook);
        if(!saved){
            throw new BadRequestException("地址添加失败!");
        }
        return addressBook;
    }

    /**
     * 地址簿分页查询
     * @param addressBookPageQueryReqDTO
     * @return
     */
    @Override
    public PageResult<AddressResDTO> pageAddress(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO) {
        return PageHelperUtils.selectPage(addressBookPageQueryReqDTO,
                ()->baseMapper.queryAddressListByUserId(UserContext.currentUserId()));
    }

    /**
     * 编辑地址簿
     * @param id
     * @param addressBookUpsertReqDTO
     * @return
     */
    @Override
    public AddressBook update(Long id, AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        // 经纬度设置
        String address = addressBookUpsertReqDTO.getAddress();
        String location = mapApi.getLocationByAddress(address).getLocation();
        Double lon=Double.valueOf(location.split(",")[0]);
        Double lat=Double.valueOf(location.split(",")[1]);
        AddressBook addressBook = BeanUtils.toBean(addressBookUpsertReqDTO, AddressBook.class);
        addressBook.setLon(lon);
        addressBook.setLat(lat);
        Long userId = UserContext.currentUserId();
        // 默认地址处理
        if(addressBook.getIsDefault().equals(1)){
            AddressBook defaultAddress = lambdaQuery()
                    .eq(AddressBook::getUserId, userId)
                    .eq(AddressBook::getIsDefault, 1)
                    .ne(AddressBook::getId,id)
                    .one();
            // 存在默认地址,将其设置为非默认
            if(ObjectUtils.isNotNull(defaultAddress)){
                defaultAddress.setIsDefault(0);
                updateById(defaultAddress);
            }
        }
        // 更新地址
        addressBook.setId(id);
        boolean updated = updateById(addressBook);
        if(!updated){
            throw new BadRequestException("编辑地址簿失败");
        }
        return addressBook;
    }

    /**
     * 设置默认地址
     * @param id
     * @param flag
     * @return
     */
    @Override
    public AddressBook setDefault(Long id, Integer flag) {
        AddressBook defaultAddress = lambdaQuery()
                .eq(AddressBook::getUserId, UserContext.currentUserId())
                .eq(AddressBook::getIsDefault, 1)
                .ne(AddressBook::getId,id)
                .one();
        // 存在默认地址,将其设置为非默认
        if(ObjectUtils.isNotNull(defaultAddress)){
            defaultAddress.setIsDefault(0);
            updateById(defaultAddress);
        }
        AddressBook addressBook = getById(id);
        addressBook.setIsDefault(flag);
        boolean updated = updateById(addressBook);
        if(!updated){
            throw new BadRequestException("编辑地址簿失败");
        }
        return addressBook;
    }
}
