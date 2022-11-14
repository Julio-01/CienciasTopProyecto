import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService ) { }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe(
      usuarios => this.usuarios = usuarios
    );
  }

  delete(usuario: Usuario): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que desea eliminar el usuario ${usuario.numeroDeCuenta}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'si, Eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.delete(usuario.id).subscribe(
          Response => {
            this.usuarios =  this.usuarios.filter(usua => usua !== usuario)
            swalWithBootstrapButtons.fire(
              'Usuario Elimindo!',
              'Usuario elminado con éxito.',
              'success'
            )
          }
        )

      }
    })
    
  }

}
