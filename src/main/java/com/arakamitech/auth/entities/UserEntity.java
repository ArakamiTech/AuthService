package com.arakamitech.auth.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	@Column(name = "identificacion_usuario")
	private String identificacionUsuario;
	@Column(name = "telefono_usuario")
	private String telefonoUsuario;
	@Column(name = "correo_usuarios")
	private String correoUsuario;
	@Column(name = "password", nullable = false)
	private String password;
	
	public UserEntity(String nombreUsuario, String identificacionUsuario, String telefonoUsuario, String correoUsuario,
			String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.identificacionUsuario = identificacionUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.correoUsuario = correoUsuario;
		this.password = password;
	}

}
