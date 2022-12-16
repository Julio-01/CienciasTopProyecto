import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductosComponent } from './productos/productos.component';
import { RouterModule, Routes } from '@angular/router';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { HttpClientModule} from '@angular/common/http';
// import { ProductosFormComponent } from './productos/form.component';
import { UsuariosFormComponent } from './usuarios/form.component';
import { FormsModule } from '@angular/forms';
import { VerComponent } from './productos/ver.component';
import { FormComponent } from './productos/form.component';
import { DevolverComponent } from './productos/devolver.component';
import { UsuarioPerfilComponent } from './usuarios/perfil.component';
import { LoginComponent } from './usuarios/login.component';
import { InicioComponent } from './inicio/inicio.component';

const routes: Routes = [
  {path: '', redirectTo: '/inicio', pathMatch: 'full'},
  {path: 'productos', component: ProductosComponent},
  {path: 'productos/form', component: FormComponent},
  {path: 'productos/form/:id', component: FormComponent},
  {path: 'productos/devolver/:id', component: DevolverComponent},
  {path: 'productos/ver', component: VerComponent},
  {path: 'productos/ver/:id', component: VerComponent},
  {path: 'login', component: LoginComponent},
  {path: 'usuarios', component: UsuariosComponent},
  {path: 'usuarios/form', component: UsuariosFormComponent},
  {path: 'usuarios/form/:id', component: UsuariosFormComponent},
  {path: 'usuarios/perfil', component: UsuarioPerfilComponent},
  {path: 'inicio', component:InicioComponent},
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductosComponent,
    DevolverComponent,
    // ProductosFormComponent,
    FormComponent,
    LoginComponent,
    UsuariosComponent,
    VerComponent,
    UsuariosFormComponent,
    UsuarioPerfilComponent,
    InicioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
