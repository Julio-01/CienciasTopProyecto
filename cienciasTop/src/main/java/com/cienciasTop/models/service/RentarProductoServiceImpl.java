package com.cienciasTop.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cienciasTop.models.dao.IProductoDao;
import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.entity.RentarProducto;
import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.dao.IRentarProductoDao;

@Service
public class RentarProductoServiceImpl implements IRentarProductoService{
	
	@Autowired
	private IRentarProductoDao rentarProductoDao ;

	@Override
	@Transactional(readOnly=true)
	public List<RentarProducto> findAll() {
		// TODO Auto-generated method stub
		return (List<RentarProducto>) rentarProductoDao.findAll();
	}

	// @Override
	// @Transactional(readOnly=true)
	// public List<RentarProducto> findAllByInitialDate(String strDate) {
	// 	return rentarProductoDao.findAllByInitialDate(strDate);
	// }


	@Override
	@Transactional(readOnly=true)
	public RentarProducto findById(Long id) {
		// TODO Auto-generated method stub
		return rentarProductoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public RentarProducto save(RentarProducto producto) {
		// TODO Auto-generated method stub
		return rentarProductoDao.save(producto);
	}
	@Transactional()
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rentarProductoDao.deleteById(id);
		
	}

	

}
