// app.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CreditoComponent } from './credito/credito.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, CreditoComponent],
  template: '<app-credito></app-credito>'
})
export class AppComponent {}
