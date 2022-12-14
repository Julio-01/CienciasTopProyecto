import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  titulo:string = 'Acceder';
  usuario: Usuario;

  constructor(private authService: AuthService, private router: Router) { 
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    if(this.authService.isAuthenticated()){
      Swal.fire('Login', `Hola ${this.authService.usuario.nombre} ya estás autenticado!`, 'info');
      this.router.navigate(['/productos']);
    }
  }

  login(){
      console.log(this.usuario);
      if(this.usuario.numeroDeCuenta == null || this.usuario.contrasena == null){
        Swal.fire('Error Login', 'Numero de Cuenta o contrasena vacías!', 'error');
        return;
      }
      
      this.authService.login(this.usuario).subscribe(response => {
        console.log(response);

        this.authService.guardarUsuario(response.access_token);

        this.authService.guardarToken(response.access_token);

        let usuario = this.authService.usuario;

        this.router.navigate(['/productos']);
        Swal.fire('Login', `Hola ${usuario.nombre}, has iniciado sesión correctamente`, 'success');
      },
      err => {
        if(err.status == 400){
          Swal.fire('Error Login', 'Numero de cuenta o clave incorrectas!', 'error');
        }
      }
      );
  }
  

}
