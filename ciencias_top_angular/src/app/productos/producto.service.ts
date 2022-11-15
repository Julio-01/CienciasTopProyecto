import { Injectable } from '@angular/core';
import { PRODUCTOS } from './productos.json';
import { Producto } from './producto';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { catchError , throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';

import { Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {



  private urlEndPoint:string = 'http://localhost:8080/api/productos';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

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
      this.router.navigate(['/productos'])
      return true;
    }
    return false;
  }


  getProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.urlEndPoint);
  }

  create(producto: Producto): Observable<any>{
    return this.http.post<any>(this.urlEndPoint, producto, {headers: this.agregarAuthorizationHeader()} ).pipe(
      
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )
  }

  getProducto(id): Observable<Producto>{
    return this.http.get<Producto>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }


        this.router.navigate(['/productos']);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    )
  }

  update(producto: Producto): Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint}/${producto.id}`, producto, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )
  }

  delete(id: number): Observable<Producto>{
    return this.http.delete<Producto>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {

        if(this.isNoAutorizado(e)){
          return throwError( () => e );
        }

        
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError( () => e );
      })
    )
  }
}
