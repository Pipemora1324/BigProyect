import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnvironmentalConditions } from '../models/environmental-conditions';
import { SimulationResult } from '../models/simulation-result';
import { DataStructuresService } from './data-structures.service';
import { Plant } from '../models/plant';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  private apiUrl = 'http://localhost:8082/api/plants';

  constructor(
    private http: HttpClient,
    private dataStructuresService: DataStructuresService
  ) {}

  // Simulates plant growth
  simulateGrowth(plantId: number, conditions: EnvironmentalConditions): Observable<SimulationResult> {
    const url = `${this.apiUrl}/${plantId}/simulate`;
    return this.http.post<SimulationResult>(url, conditions);
  }

  // Adds a plant to the list of recommended plants
  addToRecommendedPlants(plant: Plant): void {
    this.dataStructuresService.addToRecommendedPlants(plant);
  }

  // Gets the list of recommended plants
  getRecommendedPlants(): Plant[] {
    return this.dataStructuresService.getRecommendedPlants();
  }

  // Adds a care task
  addCareTask(plantName: string, careTask: string): void {
    const task = `${plantName}: ${careTask}`;
    this.dataStructuresService.addCareTask(task);
  }

  // Gets the list of care tasks
  getCareTasksList(): string[] {
    return this.dataStructuresService.getCareTasksList();
  }
}
