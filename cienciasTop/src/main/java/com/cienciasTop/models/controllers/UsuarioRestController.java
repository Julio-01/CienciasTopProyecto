package com.cienciasTop.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IUsuarioService;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	@Autowired
	private IUsuarioService usuarioService;
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	@GetMapping("/usuarios/{id}")
	public Usuario show(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable long id) {
		Usuario currentUsuario = this.usuarioService.findById(id);
		currentUsuario.setNombre(usuario.getNombre());
		currentUsuario.setNumeroDeCuenta(usuario.getNumeroDeCuenta());
		currentUsuario.setNumeroDeCelular(usuario.getNumeroDeCelular());
		currentUsuario.setCorreoElectronico(usuario.getCorreoElectronico());
		currentUsuario.setCarrera(usuario.getCarrera());
		currentUsuario.setPumaPuntos(usuario.getPumaPuntos());
		
		this.usuarioService.save(currentUsuario);
		return currentUsuario;
	}
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id){
		usuarioService.delete(id);
	}
}
