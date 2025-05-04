import { Component, OnInit } from "@angular/core";
import { CuentaService } from "../../../services/cuenta.service";
import { UserService } from "../../../services/user.service";
import { CommonModule } from "@angular/common";
import { Router } from "@angular/router";

import Chart from "chart.js/auto";

import { FormsModule } from "@angular/forms"; // Add this import

import { AuthService } from "../../../services/auth.service";
import { forkJoin } from "rxjs";

@Component({
  selector: "app-resumen",
  standalone: true,
  imports: [CommonModule, FormsModule], // Add FormsModule here
  templateUrl: "./resumen.component.html",
  styleUrls: ["./resumen.component.css"],
})
export class ResumenComponent implements OnInit {
  usuarioNombre: string = ""; // Variable para almacenar el nombre del usuario
  ingresosTotales: number = 0;
  gastosTotales: number = 0;
  ahorroMensual: number = 0;
  porcentajeAhorro: number = 0;
  alertas: string[] = [];

  cuentas: any[] = [];
  selectedCuenta: any = null;
  chart: any = null;
  nuevoIngreso: number = 0;
  nuevoGasto: number = 0;
  mostrarFormularioIngreso: boolean = false;
  mostrarFormularioGasto: boolean = false;

  constructor(
    private cuentaService: CuentaService,
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    const userId = parseInt(localStorage.getItem("userId") || "0", 10);

    if (userId) {
      this.cargarCuentasUsuario(userId);

      this.userService.getUserNameFromBackend(userId).subscribe(
        (username) => {
          this.usuarioNombre = username;
          console.log("Usuario Nombre: ", this.usuarioNombre); // Verifica si el nombre es correcto
        },
        (error) => {
          console.error("Error al obtener el nombre de usuario", error);
        },
      );
    } else {
      this.router.navigate(["/login"]);
    }
  }

  cargarCuentasUsuario(userId: number): void {
    this.cuentaService.getCuentasUsuario(userId).subscribe(
      (data) => {
        this.cuentas = data;
        if (this.cuentas.length > 0) {
          this.selectedCuenta = this.cuentas[0];
          this.cargarIngresosYGastos(this.selectedCuenta.id);
        }
      },
      (error) => {
        console.error("Error al obtener las cuentas", error);
      },
    );
  }

  cargarIngresosYGastos(cuentaId: number): void {
    forkJoin({
      ingresos: this.cuentaService.getIngresosDeCuenta(cuentaId),
      gastos: this.cuentaService.getGastosDeCuenta(cuentaId),
    }).subscribe(
      ({ ingresos, gastos }) => {
        this.ingresosTotales = ingresos.reduce(
          (sum: number, ingreso: any) => sum + ingreso.importe,
          0,
        );

        this.gastosTotales = gastos.reduce(
          (sum: number, gasto: any) => sum + gasto.importe,
          0,
        );

        this.ahorroMensual = this.ingresosTotales - this.gastosTotales;
        this.porcentajeAhorro =
          this.ingresosTotales > 0
            ? (this.ahorroMensual / this.ingresosTotales) * 100
            : 0;

        this.alertas = [];
        if (this.porcentajeAhorro < 20) {
          this.alertas.push(
            "Cuidado, tu porcentaje de ahorro está por debajo del 20% recomendado.",
          );
        }

        this.dibujarGrafica();
      },
      (error) => {
        console.error("Error al obtener ingresos o gastos", error);
      },
    );
  }

  dibujarGrafica(): void {
    const ctx = document.getElementById("graficaResumen") as HTMLCanvasElement;

    if (this.chart) {
      this.chart.destroy();
    }

    this.chart = new Chart(ctx, {
      type: "doughnut",
      data: {
        labels: ["Ingresos", "Gastos", "Ahorro"],
        datasets: [
          {
            label: "Resumen Financiero",
            data: [
              this.ingresosTotales,
              this.gastosTotales,
              this.ahorroMensual,
            ],
            backgroundColor: ["#4CAF50", "#F44336", "#2196F3"],
          },
        ],
      },
    });
  }

  onCuentaSeleccionada(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const cuentaId = selectElement.value;
    this.cargarIngresosYGastos(Number(cuentaId)); // Convierte el cuentaId a número
  }

  toggleFormularioIngreso(): void {
    this.mostrarFormularioIngreso = !this.mostrarFormularioIngreso;
  }

  toggleFormularioGasto(): void {
    this.mostrarFormularioGasto = !this.mostrarFormularioGasto;
  }

  agregarIngreso(): void {
    if (this.selectedCuenta && this.nuevoIngreso > 0) {
      this.cuentaService
        .agregarIngreso(this.selectedCuenta.id, this.nuevoIngreso)
        .subscribe(
          (response) => {
            this.nuevoIngreso = 0;
            this.toggleFormularioIngreso();
            this.cargarIngresosYGastos(this.selectedCuenta.id); // Actualizar los datos
          },
          (error) => console.error("Error al agregar ingreso", error),
        );
    }
  }

  agregarGasto(): void {
    if (this.selectedCuenta && this.nuevoGasto > 0) {
      this.cuentaService
        .agregarGasto(this.selectedCuenta.id, this.nuevoGasto)
        .subscribe(
          (response) => {
            this.nuevoGasto = 0;
            this.toggleFormularioGasto();
            this.cargarIngresosYGastos(this.selectedCuenta.id); // Actualizar los datos
          },
          (error) => console.error("Error al agregar gasto", error),
        );
    }
  }
}
