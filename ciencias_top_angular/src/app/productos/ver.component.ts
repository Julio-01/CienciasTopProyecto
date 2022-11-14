import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-ver',
  templateUrl: './ver.component.html',
  styleUrls: ['./ver.component.css']
})

export class VerComponent implements OnInit {

  titulo: string = "Ver Producto"
  producto: Producto = new Producto()

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

  public ver():void{
    this.router.navigate(['/productos'])
  }

}
