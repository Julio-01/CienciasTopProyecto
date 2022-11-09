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
import { ProductosFormComponent } from './productos/form.component';
import { UsuariosFormComponent } from './usuarios/form.component';
import { FormsModule } from '@angular/forms';


const routes: Routes = [
  {path: '', redirectTo: '/productos', pathMatch: 'full'},
  {path: 'productos', component: ProductosComponent},
  {path: 'productos/form', component: ProductosFormComponent},
  {path: 'productos/form/:id', component: ProductosFormComponent},

  {path: 'usuarios', component: UsuariosComponent},
  {path: 'usuarios/form', component: UsuariosFormComponent},
  {path: 'usuarios/form/:id', component: UsuariosFormComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductosComponent,
    UsuariosComponent,
    ProductosFormComponent,
    UsuariosFormComponent
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
