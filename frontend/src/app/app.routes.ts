import { Routes } from "@angular/router";
import { ResumenComponent } from "./dashboard/overview/resumen/resumen.component";
import { LoginComponent } from "./login/login.component";
import { AuthGuard } from "./services/auth.guard"; // Asegúrate de tener el guard importado
import { CrearCuentaComponent } from "./crear-cuenta/crear-cuenta.component";

export const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "resumen", component: ResumenComponent, canActivate: [AuthGuard] }, // Ruta protegida
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "crear-cuenta", component: CrearCuentaComponent },
];
