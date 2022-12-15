package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.RentarProducto;
public interface IRentarProductoService {
    public List<RentarProducto> findAll();

	public RentarProducto findByUsuarioProducto(long idUsuario, long idProducto);
	
	public RentarProducto findById(Long id);
	
	public RentarProducto save(RentarProducto producto);
	
	public void delete(Long id);
    
}
