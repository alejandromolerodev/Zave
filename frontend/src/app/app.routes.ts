import { Routes } from "@angular/router";
import { ResumenComponent } from "./dashboard/overview/resumen/resumen.component";
import { LoginComponent } from "./login/login.component";
import { authGuard } from "./services/auth.guard"; // asegúrate de que la ruta sea correcta

export const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "resumen", component: ResumenComponent, canActivate: [authGuard] },
  { path: "", redirectTo: "login", pathMatch: "full" },
];
