import { Component, OnInit } from "@angular/core";
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";
import { CommonModule } from "@angular/common"; // Asegúrate de importar CommonModule
import { FormsModule } from "@angular/forms"; // Asegúrate de importar FormsModule

@Component({
  selector: "app-login",
  standalone: true, // Esto indica que el componente es autónomo y no requiere un módulo
  imports: [CommonModule, FormsModule], // Importa los módulos necesarios aquí
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  email: string = "";
  password: string = "";
  errorMessage: string = "";
  modoRegistro: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    // Si ya está autenticado, redirige al resumen
    if (this.authService.isAuthenticated()) {
      this.router.navigate(["/resumen"]);
    }
  }

  onLogin(): void {
    this.authService.login(this.email, this.password).subscribe(
      (response) => {
        this.authService.setToken(response.token);
        this.router.navigate(["/resumen"]);
      },
      (error) => {
        this.errorMessage =
          "Error de autenticación. Verifica tus credenciales.";
      },
    );
  }

  onRegister(): void {
    this.authService.register(this.email, this.password).subscribe(
      (response) => {
        this.authService.setToken(response.token);
        this.router.navigate(["/resumen"]);
      },
      (error) => {
        this.errorMessage = "Error al registrarse. Intenta de nuevo.";
      },
    );
  }
}
