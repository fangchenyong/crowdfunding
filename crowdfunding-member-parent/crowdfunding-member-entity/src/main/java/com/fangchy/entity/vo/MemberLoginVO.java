package com.fangchy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO {
	
	private Integer id;
	
    private String username;
	
	private String email;
	
}