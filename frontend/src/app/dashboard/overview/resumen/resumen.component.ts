import { Component, OnInit } from "@angular/core";
import { CommonModule } from "@angular/common";
import Chart from "chart.js/auto";

@Component({
  selector: "app-resumen",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./resumen.component.html",
  styleUrl: "./resumen.component.css",
})
export class ResumenComponent implements OnInit {
  ingresosTotales: number = 0;
  gastosTotales: number = 0;
  ahorroMensual: number = 0;
  porcentajeAhorro: number = 0;

  alertas: string[] = [];

  constructor() {}

  ngOnInit(): void {
    // Aquí luego llamaremos a la API para obtener los datos
    this.cargarResumenMensual();
    this.dibujarGrafica();
  }

  dibujarGrafica(): void {
    const ctx = document.getElementById("graficaResumen") as HTMLCanvasElement;
    new Chart(ctx, {
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

  cargarResumenMensual(): void {
    // Simulación temporal hasta conectar con la API

    this.ingresosTotales = 2000;
    this.gastosTotales = 1500;
    this.ahorroMensual = this.ingresosTotales - this.gastosTotales;
    this.porcentajeAhorro = (this.ahorroMensual / this.ingresosTotales) * 100;

    if (this.porcentajeAhorro < 20) {
      this.alertas.push(
        "Cuidado, Tu porcentaje de ahorro está por debajo del 20% recomendado.",
      );
    }
  }
}
