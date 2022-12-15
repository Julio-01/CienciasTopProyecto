package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findById(Long id);

	public Usuario findByNumeroDeCuenta(String numeroDeCuenta);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);

	public void cuentasInactivas(Long id);

}
