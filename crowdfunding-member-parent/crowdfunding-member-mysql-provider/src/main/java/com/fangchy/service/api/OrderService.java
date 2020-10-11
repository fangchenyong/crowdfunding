package com.fangchy.service.api;


import com.fangchy.entity.vo.AddressVO;
import com.fangchy.entity.vo.OrderProjectVO;

import java.util.List;

public interface OrderService {

	OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

	List<AddressVO> getAddressVOList(Integer memberId);

	void saveAddress(AddressVO addressVO);

}
