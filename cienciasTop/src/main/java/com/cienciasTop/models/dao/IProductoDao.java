package com.cienciasTop.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto,Long>{
    @Query(
			value= "SELECT nombre FROM productos ORDER BY precio asc LIMIT 5", 
			nativeQuery = true)
}
