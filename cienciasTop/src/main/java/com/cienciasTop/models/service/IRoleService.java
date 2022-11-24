package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Role;

public interface IRoleService {
	
	public List<Role> findAll();
	
	public Role findById(Long id);

	public Role findByNombre(String nombre);
}
