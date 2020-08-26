package com.fangchy.service.impl;

import com.fangchy.entity.po.MemberPO;
import com.fangchy.entity.po.MemberPOExample;
import com.fangchy.mapper.MemberPOMapper;
import com.fangchy.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 在类上使用@Transactional(readOnly = true)针对查询操作设置事务属性
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberPOMapper memberPOMapper;

	@Override
	public MemberPO getMemberPOByLoginAcct(String loginacct) {
		
		// 1.创建Example对象
		MemberPOExample example = new MemberPOExample();
		
		// 2.创建Criteria对象
		MemberPOExample.Criteria criteria = example.createCriteria();
		
		// 3.封装查询条件
		criteria.andLoginacctEqualTo(loginacct);
		
		// 4.执行查询
		List<MemberPO> list = memberPOMapper.selectByExample(example);
		
		// 5.获取结果
		return list.get(0);
	}


	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			rollbackFor = Exception.class,
			readOnly = false)
	@Override
	public void saveMember(MemberPO memberPO) {

		memberPOMapper.insertSelective(memberPO);

	}
}
