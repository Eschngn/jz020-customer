package com.jzo2o.customer.mapper;

import com.jzo2o.customer.model.domain.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.customer.model.dto.response.AddressResDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 地址薄 Mapper 接口
 * </p>
 *
 * @author itcast
 * @since 2023-07-06
 */
public interface AddressBookMapper extends BaseMapper<AddressBook> {

    List<AddressResDTO> queryAddressListByUserId(@Param("userId")Long userId);
}
