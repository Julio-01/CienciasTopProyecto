import { Injectable } from '@angular/core';
import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';
import { catchError , throwError} from 'rxjs';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:8080/api/usuarios';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) { }

  private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  private isNoAutorizado(e): boolean{
    if(e.status==401){
      if(this.authService.isAuthenticated()){
        this.authService.logout();
      }
      this.router.navigate(['/login'])
      return true;
    }

    if(e.status==403){
      Swal.fire('Acceso denegado', `Hola ${this.authService.usuario.numeroDeCuenta} no tienes acceso a este recurso!`, 'warning');
      this.router.navigate(['/usuarios'])
      return true;
    }
    return false;
  }

  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPoint, {headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    );
  }

  create(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.urlEndPoint, usuario, {headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    );
  }

  getUsuario(id): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    );
  }

  update(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    );
  }

  delete(id: number): Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/usuarios']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    );
  }
}
