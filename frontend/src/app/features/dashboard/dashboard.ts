import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';
import { AnalyticsService, DashboardResponse } from '../../core/services/analytics.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, BaseChartDirective],
  templateUrl: './dashboard.html',   // Atualizado para o seu nome de arquivo
  styleUrl: './dashboard.scss'       // Atualizado para o seu nome de arquivo
})
export class DashboardComponent {
  private analyticsService = inject(AnalyticsService);

  username: string = '';
  dashboardData: DashboardResponse | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  public pieChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: true,
  };
  public pieChartLabels = [ 'Vitórias', 'Outros Resultados' ];
  public pieChartDatasets = [ { 
    data: [ 0, 0 ], 
    backgroundColor: ['#006437', '#e5e5e5'] 
  } ];

  buscarDados() {
    if (!this.username.trim()) return;

    this.isLoading = true;
    this.errorMessage = '';
    this.dashboardData = null;

    this.analyticsService.getPlayerDashboard(this.username).subscribe({
      next: (data) => {
        this.dashboardData = data;
        this.pieChartDatasets[0].data = [
          data.winRatePercentage, 
          100 - data.winRatePercentage
        ];
        this.isLoading = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Jogador não encontrado ou erro na API.';
        this.isLoading = false;
      }
    });
  }
}