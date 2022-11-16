import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { Usuario } from '../usuarios/usuario';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Añadir Producto"
  producto: Producto = new Producto()
  usuario: Usuario = new Usuario()

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarProducto()
  }

  cargarProducto(): void{
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.productoService.getProducto(id).subscribe((producto)=>this.producto = producto)
      }
    })
  }

  public create():void{
    this.productoService.create(this.producto).subscribe(response =>
      {
        this.router.navigate(['/productos'])
        swal.fire('Nuevo Producto', `Producto ${response.producto.nombre} creado con éxito`, 'success')
      }
    )
  }

  public update():void{
    this.productoService.update(this.producto).subscribe(response => 
      {
      this.router.navigate(['/productos'])
      swal.fire('Producto Actualizado', `${response.producto.nombre} actualizado con éxito`, 'success')
      }
    )
  }

  public rentar():void{

    // this.productoService.rentarProducto(this.producto.id,this.usuario.id).subscribe(response =>
    //   {
    //     this.router.navigate(['/productos/rentar'])
    //     swal.fire('Rentado', `Producto ${response.nombre} rentado con éxito`, 'success')
    //   }
    // )
    this.activateRoute.params.subscribe(params => {
      let idP = params['idP']
      let idU = params['idU']
      if(idP && idU){

        
        this.productoService.rentarProducto(idP,idU).subscribe((resp)=> 
          swal.fire('Nuevo Producto', `Producto ${this.producto.nombre } creado con éxito`, 'success'))


        {
          this.router.navigate(['/productos'])
        }
      }
    })

    // this.productoService.rentarProducto(this.producto.id,this.usuario.id).subscribe(response => 
    //   {
    //   this.router.navigate(['/productos/rentar'])
    //   swal.fire('Producto Rentado', `${response.nombre} Rentado con éxito`, 'success')
    //   }
    // )
  }




}
