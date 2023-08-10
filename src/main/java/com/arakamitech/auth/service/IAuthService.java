package com.arakamitech.auth.service;

import com.arakamitech.auth.dtos.TokenDto;
import com.arakamitech.auth.dtos.UserDto;

public interface IAuthService {
	
	public UserDto save(UserDto userDto);
	
	public TokenDto login(UserDto userDto);
	
	public TokenDto validate(String token);

}
