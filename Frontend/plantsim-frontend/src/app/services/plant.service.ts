import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Plant } from '../models/plant';
import { DataStructuresService } from './data-structures.service';

@Injectable({
  providedIn: 'root'
})
export class PlantService {
  private apiUrl = 'http://localhost:8082/api/plants';

  constructor(
    private http: HttpClient,
    private dataStructuresService: DataStructuresService
  ) { }

  // Método para obtener todas las plantas
  getPlants(): Observable<Plant[]> {
    return this.http.get<Plant[]>(this.apiUrl);
  }

  // Método para obtener una planta por ID
  getPlantById(id: number): Observable<Plant> {
    const url = `${this.apiUrl}/${id}`;
    // Utilizamos la pila para registrar el historial de plantas vistas
    return new Observable<Plant>(observer => {
      this.http.get<Plant>(url).subscribe({
        next: (plant) => {
          // Agregar a la pila de historial
          this.dataStructuresService.pushToPlantStack(plant);
          observer.next(plant);
          observer.complete();
        },
        error: (error) => observer.error(error)
      });
    });
  }

  // Método para crear una planta
  createPlant(plant: Plant): Observable<Plant> {
    // Utilizamos la cola para registrar operaciones pendientes
    this.dataStructuresService.enqueueOperation('create', plant);
    return this.http.post<Plant>(this.apiUrl, plant);
  }

  // Método para actualizar una planta
  updatePlant(id: number, plant: Plant): Observable<Plant> {
    const url = `${this.apiUrl}/${id}`;
    this.dataStructuresService.enqueueOperation('update', plant);
    return this.http.put<Plant>(url, plant);
  }

  // Método para eliminar una planta
  deletePlant(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    this.dataStructuresService.enqueueOperation('delete', id);
    return this.http.delete<void>(url);
  }

  // Método para obtener el último historial de plantas vistas
  getLastViewedPlant(): Plant | undefined {
    return this.dataStructuresService.peekPlantStack();
  }

  // Método para retroceder en el historial
  goBackInHistory(): Plant | undefined {
    this.dataStructuresService.popFromPlantStack(); // Descartar la actual
    return this.dataStructuresService.peekPlantStack(); // Obtener la anterior
  }
}