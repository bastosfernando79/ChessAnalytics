import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// O contrato de dados espelhando nosso backend em Java
export interface DashboardResponse {
  username: string;
  currentRating: number;
  targetRating: number;
  pointsToGoal: number;
  winRatePercentage: number;
  totalGames: number;
}

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor(private http: HttpClient) { }

  // A chamada HTTP que passa pelo proxy e chega na porta 8080
  getPlayerDashboard(username: string): Observable<DashboardResponse> {
    return this.http.get<DashboardResponse>(`/api/v1/analytics/dashboard/${username}`);
  }
}