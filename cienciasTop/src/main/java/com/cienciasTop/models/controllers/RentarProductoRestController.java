package com.cienciasTop.models.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.entity.RentarProducto;
import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IProductoService;
import com.cienciasTop.models.service.IRentarProductoService;
import com.cienciasTop.models.service.IUsuarioService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class RentarProductoRestController {

    @Autowired
	private IProductoService productoService;
    @Autowired
	private IRentarProductoService rentarProductoService;
	@Autowired
	private IUsuarioService usuarioService;


	@GetMapping("/rentarProducto")
	public List<RentarProducto> index(){
		 return rentarProductoService.findAll();
	}

//    @Secured({"ROLE_ADMIN","ROL_USER","ROL_PROV"})
	@GetMapping("/rentarProducto/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		RentarProducto rentarProducto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			rentarProducto = rentarProductoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		if(rentarProducto == null) {
			response.put("mensaje", "El ID de rentarProducto: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RentarProducto>(rentarProducto, HttpStatus.OK);
	}

//    @Secured({"ROLE_ADMIN","ROL_PROV"})
	@PostMapping("/rentarProducto")
	public ResponseEntity<?> create(@RequestBody RentarProducto rentarProducto) {
		RentarProducto rentarProductoNuevo = null;
		Map<String,Object> response = new HashMap<>();
		try {
			rentarProductoNuevo = rentarProductoService.save(rentarProducto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El rentarProducto ha sido creado con éxito :3");
		response.put("producto", rentarProductoNuevo);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

//    @Secured({"ROLE_ADMIN","ROL_PROV"})
	@PutMapping("/rentarProducto/{id}")
	public 	ResponseEntity<?> update(@RequestBody RentarProducto rentarProducto, @PathVariable Long id) {
		RentarProducto currentRentarProducto = this.rentarProductoService.findById(id);
		RentarProducto rentarProductoUpdate = null;
		Map<String,Object> response = new HashMap<>();
		//Error con el id ingresado.
		if(currentRentarProducto == null) {
			response.put("mensaje", "Error: no se puede editar  rentarProducto ID:".concat(id.toString().concat(" no existe en la base de datos :(.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentRentarProducto.setFecha_inicio(rentarProducto.getFecha_inicio());
			currentRentarProducto.setFecha_fianl(rentarProducto.getFecha_fianl());
			currentRentarProducto.setIdProducto(rentarProducto.getIdProducto());
			currentRentarProducto.setIdUsuario(rentarProducto.getIdUsuario());
			currentRentarProducto.setIdRentado(rentarProducto.getIdRentado());
			rentarProductoUpdate = rentarProductoService.save(currentRentarProducto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar  rentarProducto en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido actualizado con éxito :3");
		response.put("producto", rentarProductoUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}


//	@Secured({"ROLE_ADMIN","ROL_PROV"})
	@DeleteMapping("/rentarProducto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		try {
			rentarProductoService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar  prrentarProductooducto en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El objeto rentarProducto ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}


    
}