package com.cienciasTop.models.dao;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.cienciasTop.models.entity.RentarProducto;

public interface IRentarProductoDao extends CrudRepository<RentarProducto, Long>{

    public RentarProducto findByIdUsuarioAndIdProducto(long idUsuario, long idProducto);
    
}
