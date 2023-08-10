package com.arakamitech.auth.service;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.arakamitech.auth.config.JWTProvider;
import com.arakamitech.auth.dtos.TokenDto;
import com.arakamitech.auth.dtos.UserDto;
import com.arakamitech.auth.entities.UserEntity;
import com.arakamitech.auth.repository.IRepository;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private IRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTProvider jwtProvider;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto save(UserDto userDto) {
		UserEntity userEntity = repository.findByNombreUsuario(userDto.getNombreUsuario());
		if (Objects.nonNull(userEntity)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("User %s already exist", userDto.getNombreUsuario()));
		}
		userEntity = repository.save(new UserEntity(userDto.getNombreUsuario(), userDto.getIdentificacionUsuario(),
				userDto.getTelefonoUsuario(), userDto.getCorreoUsuario(),
				passwordEncoder.encode(userDto.getPassword())));
		return mapper.map(userEntity, UserDto.class);
	}

	@Override
	public TokenDto login(UserDto userDto) {
		UserEntity userEntity = repository.findByNombreUsuario(userDto.getNombreUsuario());
		if (Objects.isNull(userEntity)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		if (passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
			return new TokenDto(jwtProvider.createToken(userEntity));
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public TokenDto validate(String token) {
		jwtProvider.validate(token);
		String username = jwtProvider.getUsernameFromToken(token);
		repository.findByNombreUsuario(username);
		if (!StringUtils.hasLength(username)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		return new TokenDto(token);
	}

}
