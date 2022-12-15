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

  rentar(producto:Producto): void{
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea rentar el producto ${producto.nombre}?`,
      //icon: 'question',//question
      imageUrl: "https://unamenlinea.unam.mx/recursos/img/81761/repositorio-atenea-de-la-facultad-de-ciencias.jpg",
      imageWidth: 200,
      imageHeight: 200,
      imageAlt: 'Custom image',
      showCancelButton: true,
      confirmButtonText: 'Sí, Rentar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.rentar(producto.id).subscribe(
          Response => {
            // this.productos =  this.productos.filter(prod => prod !== producto)
            swalWithBootstrapButtons.fire(
              'Producto Rentado!',
              'Producto rentado con éxito.',
              'success'
            )
          }
        )

      }
    })
  }

}
