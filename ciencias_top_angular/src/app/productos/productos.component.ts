import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Usuario } from '../usuarios/usuario';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[];

  constructor(private productoService: ProductoService, public authService: AuthService ) { }

  ngOnInit(): void {
    this.productoService.getProductos().subscribe(
      productos => this.productos = productos
    );
  }

  public rentar(producto: Producto, usuario: Usuario):void{
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea Rentar el producto ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'si, Rentar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.rentarProducto(producto.id,usuario.id).subscribe(
          Response => {
            console.log(Response);
            
            if (Response.set){
              this.productos =  this.productos.filter(prod => prod !== producto)
            swalWithBootstrapButtons.fire(
              'Rentar!',
              'Producto Rentado con éxito.',
              'success'
            )
            }

            // if (usuario.pumaPuntos >= Response.)
            // this.productos =  this.productos.filter(prod => prod !== producto)
            // swalWithBootstrapButtons.fire(
            //   'Rentar!',
            //   'Producto Rentado con éxito.',
            //   'success'
            // )
          }
        )

      }
    })
    // this.productoService.rentarProducto(producto.id,usuario.id).subscribe(response =>
    //   {
    //     this.router.navigate(['/productos/rentar'])
    //     swal.fire('Rentado', `Producto ${response.nombre} rentado con éxito`, 'success')
    //   }
    // )
  }
  delete(producto: Producto): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea elimiar el producto ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, Eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.delete(producto.id).subscribe(
          Response => {
            this.productos =  this.productos.filter(prod => prod !== producto)
            swalWithBootstrapButtons.fire(
              'Producto Elimindo!',
              'Producto elminado con éxito.',
              'success'
            )
          }
        )

      }
    })
    
  }

}
