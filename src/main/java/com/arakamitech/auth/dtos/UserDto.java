package com.arakamitech.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

	private Long id;
	private String nombreUsuario;
	private String identificacionUsuario;
	private String telefonoUsuario;
	private String correoUsuario;
	private String password;

}
