package com.arakamitech.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arakamitech.auth.entities.UserEntity;

public interface IRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByNombreUsuario(String nombre);

}
