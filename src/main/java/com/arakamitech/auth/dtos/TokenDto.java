package com.arakamitech.auth.dtos;

import lombok.Getter;

@Getter
public class TokenDto {

	private String token;

	public TokenDto(String token) {
		super();
		this.token = token;
	}
	
	
	
}
