import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from './credito.model';

@Injectable({ providedIn: 'root' })
export class CreditoService {
  private baseUrl = 'http://localhost:8080/api/creditos';

  constructor(private http: HttpClient) {}

  buscarPorNfse(nfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.baseUrl}/${nfse}`);
  }

  buscarPorNumeroCredito(numero: string): Observable<Credito> {
    return this.http.get<Credito>(`${this.baseUrl}/credito/${numero}`);
  }
}
