export class Usuario {

    id:number;
    //username
    numeroDeCuenta:string;
    nombre:string;
    numeroDeCelular:string;
    correoElectronico:string;
    carrera:string;
    //password
    contrasena:string;
    pumaPuntos:number = 100;
    enabled: boolean;
    roles:string[]=[]

}
