import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CuentaService {
  private apiUrl = "/api/zave/usuario";

  constructor(private http: HttpClient) {}

  // Obtener todas las cuentas de un usuario
  getCuentasUsuario(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/cuentas/${userId}`);
  }

  // Obtener los ingresos de una cuenta específica
  getIngresosDeCuenta(cuentaId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/ingresos/${cuentaId}`);
  }

  // Obtener los gastos de una cuenta específica
  getGastosDeCuenta(cuentaId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/gastos/${cuentaId}`);
  }

  // Agregar un nuevo ingreso
  agregarIngreso(cuentaId: number, ingreso: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/ingresos/${cuentaId}`, {
      importe: ingreso,
    });
  }

  // Agregar un nuevo gasto
  agregarGasto(cuentaId: number, gasto: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/gastos/${cuentaId}`, {
      importe: gasto,
    });
  }
}
