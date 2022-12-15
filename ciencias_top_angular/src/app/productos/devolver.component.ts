import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-form',
  templateUrl: './devolver.component.html',
  styleUrls: ['./devolver.component.css']
})
export class DevolverComponent implements OnInit {

  titulo: string = "Devolver Producto"
  numeroCuenta: string = ""
  codigoProducto: string;

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(params => {
      this.codigoProducto = params['id'];
    })
  }

  public devolver():void{
    this.productoService.devolver(this.codigoProducto).subscribe(
      Response => {
        this.router.navigate(['/productos']);
        swal.fire('Producto Devuelto', `El producto con código ${this.codigoProducto} fue devuelto con éxito`, 'success');
      }
    )

  }


}
