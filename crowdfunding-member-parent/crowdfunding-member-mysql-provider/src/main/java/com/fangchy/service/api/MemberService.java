package com.fangchy.service.api;


import com.fangchy.entity.po.MemberPO;

public interface MemberService {

	MemberPO getMemberPOByLoginAcct(String loginacct);

}
