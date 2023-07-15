import { Component,OnInit } from '@angular/core';
import { UserService } from './../../services/user.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user = {
    nombre : '',
    correo : '',
    contrasena : '',
    celular : ''
  }

  constructor(private userService: UserService){

  }
  
  ngOnInit(): void {

  }

  formSubmit(){
  
    console.log(this.user);
    if (this.user.nombre.trim() === '') {
      alert('El nombre es requerido');
      return;
    }
    
    if (!this.user.nombre.match(/^[a-zA-Z\s]+$/)) {
      alert('El nombre solo debe contener letras y espacios');
      return;
    }
    
    if (!this.user.correo.match(/^[a-zA-Z]+@[a-zA-Z]+\.com$/)) {
      alert('El correo no tiene un formato válido');
      return;
    }
    
    if (this.user.contrasena.length < 5 || !/\d/.test(this.user.contrasena)) {
      alert('La contraseña debe tener al menos 5 caracteres y al menos 1 número');
      return;
    }
    
    if (!this.user.celular.match(/^\d{9}$/)) {
      alert('El número de celular debe contener solo números y tener 9 dígitos');
      return;
    }
    this.userService.registrarUsuario(this.user).subscribe(
      (data) => {
        console.log(data);
        alert('Usuario guardado con éxito');
      },
      (error) => {
        console.log(error);
        if (error instanceof ErrorEvent) {
          // Error de red o de cliente
          alert('Ha ocurrido un error en el sistema');
        } else {
          // Error de análisis de respuesta del servidor
          console.log(error.error.text);
          alert('Ha ocurrido un error al procesar la respuesta del servidor');
        }
      }
    );

  }
}
