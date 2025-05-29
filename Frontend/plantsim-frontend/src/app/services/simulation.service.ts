import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CondicionesAmbientales } from '../models/condiciones-ambientales';
import { ResultadoSimulacion } from '../models/resultado-simulacion';
import { DataStructuresService } from './data-structures.service';
import { Plant } from '../models/plant';

@Injectable({
  providedIn: 'root'
})
export class SimulacionService {
  private apiUrl = 'http://localhost:8082/api/plantas';

  constructor(
    private http: HttpClient,
    private dataStructuresService: DataStructuresService
  ) { }

  // Método para simular el crecimiento de una planta
  simularCrecimiento(plantId: number, condiciones: CondicionesAmbientales): Observable<ResultadoSimulacion> {
    const url = `${this.apiUrl}/${plantId}/simulate`;
    return this.http.post<ResultadoSimulacion>(url, condiciones);
  }

  // Método para agregar una planta al conjunto de plantas recomendadas
  addToRecommendedPlants(plant: Plant): void {
    this.dataStructuresService.addToRecommendedPlants(plant);
  }

  // Método para obtener plantas recomendadas
  getRecommendedPlants(): Plant[] {
    return this.dataStructuresService.getRecommendedPlants();
  }

  // Método para agregar una tarea de cuidado
  addCareTask(plantName: string, careTask: string): void {
    const task = `${plantName}: ${careTask}`;
    this.dataStructuresService.addCareTask(task);
  }

  // Método para obtener la lista de tareas de cuidado
  getCareTasksList(): string[] {
    return this.dataStructuresService.getCareTasksList();
  }
}