package com.fangchy.service.api;


import com.fangchy.entity.vo.AddressVO;
import com.fangchy.entity.vo.OrderProjectVO;
import com.fangchy.entity.vo.OrderVO;

import java.util.List;

public interface OrderService {

	OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

	List<AddressVO> getAddressVOList(Integer memberId);

	void saveAddress(AddressVO addressVO);

	void saveOrder(OrderVO orderVO);

}
