import { Component } from '@angular/core';
// Importamos apenas o componente que vamos usar
import { DashboardComponent } from './features/dashboard/dashboard';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DashboardComponent], // RouterOutlet removido daqui
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App { // <-- O segredo estava aqui: a classe se chama apenas "App"
  title = 'frontend';
}