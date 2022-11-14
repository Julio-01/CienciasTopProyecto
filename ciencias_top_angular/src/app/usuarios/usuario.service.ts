import { Injectable } from '@angular/core';
import { USUARIOS } from './usuarios.json';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint:string = 'http://localhost:8080/api/usuarios';

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPoint);
  }

  create(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.urlEndPoint, usuario, {headers: this.httpHeaders} )
  }

  getUsuario(id): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`)
  }

  update(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, {headers: this.httpHeaders})
  }

  delete(id: number): Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders})
  }
}
