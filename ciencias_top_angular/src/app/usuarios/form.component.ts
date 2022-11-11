import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class UsuariosFormComponent implements OnInit {

  titulo: string = "Añadir Usuario"
  usuario: Usuario = new Usuario()

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarUsuario()
  }

  cargarUsuario(): void{
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.usuarioService.getUsuario(id).subscribe((usuario)=>this.usuario = usuario)
      }
    })
  }

  public create():void{
    this.usuarioService.create(this.usuario).subscribe(usuario =>
      {
        this.router.navigate(['/usuarios'])
        swal.fire('Nuevo Usuario', `Usuario ${usuario.nombre} creado con éxito`, 'success')
      }
    )
  }

  public update():void{
    this.usuarioService.update(this.usuario).subscribe(usuario => 
      {
      this.router.navigate(['/usuarios'])
      swal.fire('Usuario Actualizado', `El Usuario con el numero de cuenta ${usuario.numeroDeCuenta} fue actualizado con éxito`, 'success')
      }
    )
  }

}
