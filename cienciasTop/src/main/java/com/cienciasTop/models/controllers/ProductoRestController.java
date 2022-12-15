package com.cienciasTop.models.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;

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

import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.entity.RentarProducto;
import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IProductoService;
import com.cienciasTop.models.service.IRentarProductoService;
import com.cienciasTop.models.service.IUsuarioService;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {
	@Autowired
	private IProductoService productoService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IRentarProductoService rentarProductoService;
	@GetMapping("/productos")
	public List<Producto> index(){
		 return productoService.findAll();
	}

	@Secured({"ROLE_ADMIN","ROL_USER","ROL_PROV"})
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {


		Producto producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			producto = productoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		if(producto == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN","ROL_PROV"})
	@PostMapping("/productos")
	public ResponseEntity<?> create(@RequestBody Producto producto) {
		Producto productoNuevo= null;
		Map<String,Object> response = new HashMap<>();
		try {
			productoNuevo = productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido creado con éxito :3");
		response.put("producto", productoNuevo);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}



	@Secured({"ROLE_ADMIN","ROL_PROV"})
	@PutMapping("/productos/{id}")
	public 	ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto currentProducto = this.productoService.findById(id);
		Producto productoUpdate = null;
		Map<String,Object> response = new HashMap<>();
		//Error con el id ingresado.
		if(currentProducto == null) {
			response.put("mensaje", "Error: no se puede editar el producto ID:".concat(id.toString().concat(" no existe en la base de datos :(.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentProducto.setNombre(producto.getNombre());
			currentProducto.setCodigo(producto.getCodigo());
			currentProducto.setStock(producto.getStock());
			currentProducto.setPrecio(producto.getPrecio());
			currentProducto.setDescripcion(producto.getDescripcion());
			productoUpdate = productoService.save(currentProducto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el producto en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido actualizado con éxito :3");
		response.put("producto", productoUpdate);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}


	@Secured({"ROLE_ADMIN","ROL_PROV"})
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();
		try {
			productoService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto en la base de datos.");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

	@GetMapping("productos/rentar/{idP}/usuario/{idU}")
	public ResponseEntity<?> rentProduct(@PathVariable Long idP, @PathVariable Long idU  ) {
		Producto producto = this.productoService.findById(idP);
		Usuario usuario  =  usuarioService.findById(idU);
		Map<String, Object> response = new HashMap<>();
		if (producto.getStock()>= 1 ) {
			if (usuario.getPumaPuntos() >= producto.getPrecio() ) {
				Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
				String strDate = formatter.format(date);  
				System.out.println("Date Format with MM/dd/yyyy : "+strDate);  
				List<RentarProducto> listProductosRentado = rentarProductoService.findAll();
				int i = 0;
				for(RentarProducto rentarProducto: listProductosRentado ){
					System.out.print(rentarProducto.toString()+"\n");
					System.out.println(rentarProducto.getFecha_inicio());
					System.out.println(strDate);
					System.err.println("usuario.getId() "+usuario.getId() );
					if(rentarProducto.getIdUsuario()==usuario.getId() )
						System.out.println("userId el iual");
					if(rentarProducto.getFecha_inicio().trim()==strDate.trim())
						System.out.println("date el iual");
					if(rentarProducto.getIdUsuario()==usuario.getId() && rentarProducto.getFecha_inicio().equals(strDate)){
						i = i+1;
						System.out.println(i+"Productos rentados anteriormente");
					}
				}
				if(i<3){
				// if(usuario.getProductosRentadosTotales()<3){
					// RentarProducto rentarProducto = new RentarProducto();
				// TODO:productosRentadosPorDi... si es mayor o iual a 3 no se puede rentar
					RentarProducto rentarP = new RentarProducto();

					int total = usuario.getPumaPuntos() + (Integer) (producto.getPrecio() / 2);

					if (total > 500)
						usuario.setPumaPuntos(500);
					else
						usuario.setPumaPuntos(total);

					usuario.setProductosRentadosTotales(usuario.getProductosRentadosTotales()+1);
					producto.setStock(producto.getStock()-1);
					// TODO: ProductoRentado pr = new ProductoRentado(idU)
					// TODO: productoRentdoService.save(pr)
					rentarP.setIdProducto(producto.getId());
					rentarP.setIdUsuario(usuario.getId());
					rentarP.setFecha_inicio(strDate);
					rentarP.setFecha_fianl("");
					rentarProductoService.save(rentarP);
					productoService.save(producto);
					usuarioService.save(usuario);
					System.out.println(rentarP.getIdProducto());
				}else{
					response.put("mensaje", "Ya has rentado 3 productos");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}

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

	@GetMapping("productos/devolver/{idP}/usuario/{idU}")
	public ResponseEntity<?> devolverProduct(@PathVariable Long idP, @PathVariable Long idU  ) {
		Producto producto = this.productoService.findById(idP);
		Usuario usuario  =  usuarioService.findById(idU);
		Map<String, Object> response = new HashMap<>();

		RentarProducto renta = this.findByIdUsuarioAndIdProducto(usuario.getId(), producto.getId());
		Date date_final = new SimpleDateFormat("MM/dd/yyyy").parse(renta.getFecha_fianl());

		Calendar today = Calendar.getInstance();
		Calendar.set(Calendar.HOUR_OF_DAY, 23);
		Calendar.set(Calendar.MINUTE, 59);
		if (date_final < today)
			usuario.setPumaPuntos(usuario.getPumaPuntos - 20);


		response.put("usuario",usuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
