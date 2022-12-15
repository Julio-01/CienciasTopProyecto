package com.cienciasTop.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.entity.Role;
import com.cienciasTop.models.service.IUsuarioService;
import com.cienciasTop.models.service.IRoleService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IRoleService roleService;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROL_PROV"})
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(usuario == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		Usuario usuarioNuevo = null;
		Map<String,Object> response = new HashMap<>();
		List<Role> rolesExistentes = new ArrayList<>();

		try {
			for (Role role : usuario.getRoles()) {
				Role rolExistente = roleService.findByNombre(role.getNombre());
				rolesExistentes.add(rolExistente);
			}

			usuario.setRoles(rolesExistentes);
		} catch(Exception e) {
			response.put("mensaje", "Error al asignar roles");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			usuarioNuevo = usuarioService.save(usuario);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con éxito :3");
		response.put("producto", usuarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}


	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarios/reportes")
	public ResponseEntity<?> reportes() {
		int activos, inactivos = 0;
		try {
			for(Usuario u : usuarioService.findAll()){
				if(u.getEnabled())
					activos++;
				
					inactivos++;

			}
			
		} catch (Exception e) {
			response.put("mensaje", "Error al cargar reporte");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);


		}
	}


	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario currentUsuario = this.usuarioService.findById(id);
		Usuario usuarioUpdate = null;
		Map<String,Object> response = new HashMap<>();
		//Error con el id ingresado.
		if(currentUsuario == null) {
			response.put("mensaje", "Error: no se puede editar el usuario ID:".concat(id.toString().concat(" no existe en la base de datos :(.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		List<Role> rolesExistentes = new ArrayList<>();
		
		try {
			currentUsuario.setNombre(usuario.getNombre());
			currentUsuario.setNumeroDeCelular(usuario.getNumeroDeCelular());
			currentUsuario.setCorreoElectronico(usuario.getCorreoElectronico());
			currentUsuario.setCarrera(usuario.getCarrera());
			currentUsuario.setContrasena(usuario.getContrasena());
			currentUsuario.setEnabled(usuario.getEnabled());
			currentUsuario.setPumaPuntos(usuario.getPumaPuntos());

			for (Role role : usuario.getRoles()) {
				Role rolExistente = roleService.findByNombre(role.getNombre());
				rolesExistentes.add(rolExistente);
			}

			currentUsuario.setRoles(rolesExistentes);
			usuarioUpdate = usuarioService.save(currentUsuario);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con éxito :3");
		response.put("producto", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String,Object> response = new HashMap<>();
		try {
			usuarioService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}