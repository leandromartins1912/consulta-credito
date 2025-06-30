import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CreditoService } from './credito.service';
import { Credito } from './credito.model';

@Component({
  selector: 'app-credito',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './credito.component.html',
  styleUrls: ['./credito.component.css']
})
export class CreditoComponent {
  termoBusca = '';
  creditos: Credito[] = [];
  creditoUnico?: Credito;
  buscando = false;
  buscaFinalizada = false;

  constructor(private service: CreditoService) {}

  buscar() {
  this.resetarBusca();

  if (this.termoBusca.length > 6) {
    this.service.buscarPorNfse(this.termoBusca).subscribe({
      next: (data: Credito[]) => {
        this.creditos = data;
        this.finalizarBusca();
      },
      error: () => this.finalizarBusca()
    });
  } else {
    this.service.buscarPorNumeroCredito(this.termoBusca).subscribe({
      next: (data: Credito) => {
        this.creditoUnico = data;
        this.finalizarBusca();
      },
      error: () => this.finalizarBusca()
    });
  }
}

private resetarBusca() {
  this.buscando = true;
  this.buscaFinalizada = false;
  this.creditos = [];
  this.creditoUnico = undefined;
}

private finalizarBusca() {
  this.buscando = false;
  this.buscaFinalizada = true;
}

}
