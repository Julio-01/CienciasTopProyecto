package com.cienciasTop.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cienciasTop.models.dao.IRoleDao;
import com.cienciasTop.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Role findById(Long id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Role findByNombre(String nombre) {
		return roleDao.findByNombre(nombre);
	}


}
