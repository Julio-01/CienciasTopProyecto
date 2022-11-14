package com.cienciasTop.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cienciasTop.models.service.IProductoService;
import com.cienciasTop.models.service.IUsuarioService;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {
	@Autowired
	private IProductoService productoService;
	@Autowired 
	private IUsuarioService usuarioService;
	@GetMapping("/productos")
	public List<Producto> index(){
		return productoService.findAll();
	}
	@GetMapping("/productos/{id}")
	public Producto show(@PathVariable Long id) {
		return productoService.findById(id);
	}
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable long id) {
		Producto currentProducto = this.productoService.findById(id);
		currentProducto.setNombre(producto.getNombre());
		currentProducto.setCodigo(producto.getCodigo());
		currentProducto.setStock(producto.getStock());
		currentProducto.setPrecio(producto.getPrecio());
		currentProducto.setDescripcion(producto.getDescripcion());
		currentProducto.setTiempo(producto.getTiempo());
		this.productoService.save(currentProducto);
		return currentProducto;
	}
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id){
		productoService.delete(id);
	}
	
	@GetMapping("rentar/producto/{idP}/usuario/{idU}")
	public ResponseEntity<?> rentProduct(@PathVariable Long idP, @PathVariable Long idU  ) {
		Producto producto = this.productoService.findById(idP);
		Usuario usuario  =  usuarioService.findById(idU);
		Map<String, Object> response = new HashMap<>(); 
		if (producto.getStock()>= 1 ) {
			if (usuario.getPumaPuntos() >= producto.getPrecio() ) {
				//TODO:productosRentadosPorDi... si es mayor o iual a 3 no se puede rentar
				usuario.setPumaPuntos(usuario.getPumaPuntos() - producto.getPrecio());
				producto.setStock(producto.getStock()-1);
				productoService.save(producto);
				usuarioService.save(usuario);


			}else {
				response.put("mensaje", "No cuentas con los puntos necesarios para rentar este producto");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		}else {
			response.put("mensaje", "Este producto no está disponible");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("mensaje", "Se ha rentado exitósamente el producto");
		response.put("producto",producto);
		response.put("usuario",usuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}

