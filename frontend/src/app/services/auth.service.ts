import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { jwtDecode } from "jwt-decode";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private apiUrl = "http://localhost:8080/api/auth"; // Asegúrate que coincida con tu backend

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, password });
  }

  register(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, { email, password });
  }

  setToken(token: string): void {
    localStorage.setItem("token", token);
  }

  getToken(): string | null {
    return localStorage.getItem("token");
  }

  decodeToken(token: string): any {
    return jwtDecode(token);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    try {
      const decoded: any = this.decodeToken(token);
      return decoded.exp > Date.now() / 1000;
    } catch (error) {
      console.error("Token inválido:", error);
      return false;
    }
  }

  logout(): void {
    localStorage.removeItem("token");
  }
}
