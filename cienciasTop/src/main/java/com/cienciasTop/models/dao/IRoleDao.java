package com.cienciasTop.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Role;

public interface IRoleDao extends CrudRepository<Role, Long>{
	
	public Role findByNombre(String nombre);

}
