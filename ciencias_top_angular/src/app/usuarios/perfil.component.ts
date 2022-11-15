import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { AuthService } from './auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class UsuarioPerfilComponent implements OnInit {

  titulo: string = "Ver Perfil"
  usuario: Usuario = new Usuario()

  constructor(private usuarioService: UsuarioService, private authService: AuthService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarUsuario();
  }

  cargarUsuario(): void{
    let usuarioLocal = this.authService.usuario;
    this.usuarioService.getUsuario(usuarioLocal.id).subscribe(
      usuario => { 
        let roles = usuario.roles.map(r => r["nombre"]);
        this.usuario = usuario;
        this.usuario.roles = [...roles];
      }
    );
  }
}
