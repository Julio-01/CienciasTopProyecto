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
        this.usuarioService.getUsuario(id).subscribe((usuario)=> {
          this.usuario = usuario;
          this.usuario.roles = ((this.usuario.roles.map(r => r["nombre"]).join(", ") as unknown) as string[])
        })
      }
    })
  }

  public create():void{
    this.usuario.roles = [(({nombre: this.usuario.roles} as unknown) as string)];
    this.usuarioService.create(this.usuario).subscribe(usuario =>
      {
        this.router.navigate(['/usuarios'])
        swal.fire('Nuevo Usuario', `Usuario ${this.usuario.nombre} creado con éxito`, 'success')
      }
    )
  }

  public update():void{
    this.usuario.roles = [(({nombre: this.usuario.roles} as unknown) as string)];
    this.usuarioService.update(this.usuario).subscribe(usuario => 
      {
      this.router.navigate(['/usuarios'])
      swal.fire('Usuario Actualizado', `El Usuario con el numero de cuenta ${this.usuario.numeroDeCuenta} fue actualizado con éxito`, 'success')
      }
    )
  }

}
