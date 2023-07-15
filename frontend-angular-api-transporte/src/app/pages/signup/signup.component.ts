import { Component,OnInit } from '@angular/core';
import { UserService } from './../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';


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

  constructor(private userService: UserService, private snack:MatSnackBar){

  }
  
  ngOnInit(): void {

  }

  formSubmit(){
  
    console.log(this.user);
    if (this.user.nombre.trim() === '') {
      this.snack.open('Su nombre es requerido!!','Aceptar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      return;
    }
    
    if (!this.user.nombre.match(/^[a-zA-Z\s]+$/)) {

      this.snack.open('El nombre solo debe contener letras y espacios','Aceptar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      
      return;
    }
    
    if (!this.user.correo.match(/^[a-zA-Z]+@[a-zA-Z]+\.com$/)) {
      this.snack.open('El correo no tiene un formato válido','Aceptar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
    
      return;
    }
    
    if (this.user.contrasena.length < 5 || !/\d/.test(this.user.contrasena)) {
      this.snack.open('La contraseña debe tener al menos 5 caracteres y al menos 1 número','Aceptar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      
      return;
    }
    
    if (!this.user.celular.match(/^\d{9}$/)) {
      this.snack.open('El número de celular debe contener solo números y tener 9 dígitos','Aceptar',{
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
      
      return;
    }
    this.userService.registrarUsuario(this.user).subscribe(
      (data) => {
        console.log(data);
        Swal.fire('Usuario guardado','Usuario registrado con exito en el sistema','success');
      },
      (error) => {
        console.log(error);
        if (error instanceof ErrorEvent) {
          
          this.snack.open('Ha ocurrido un error en el sistema','Aceptar',{
            duration: 3000,
            verticalPosition: 'top',
            horizontalPosition: 'right'
          });

        } else {
          
          console.log(error.error.text);

          this.snack.open('Ha ocurrido un error al procesar la respuesta del servidor','Aceptar',{
            duration: 3000,
            verticalPosition: 'top',
            horizontalPosition: 'right'
          });
         
        }
      }
    );

  }
}
