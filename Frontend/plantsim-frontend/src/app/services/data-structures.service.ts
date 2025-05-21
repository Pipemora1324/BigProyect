import { Injectable } from '@angular/core';
import { Plant } from '../models/plant';

// Definiendo la interfaz Node fuera de la clase
interface TaskNode {
  value: string;
  next: TaskNode | null;
}

@Injectable({
  providedIn: 'root'
})
export class DataStructuresService {

  constructor() { }

  // Implementación de pila (Stack) para historial de plantas visualizadas
  private plantStack: Plant[] = [];

  pushToPlantStack(plant: Plant): void {
    this.plantStack.push(plant);
    console.log('Planta agregada a la pila de historial', this.plantStack);
  }

  popFromPlantStack(): Plant | undefined {
    const plant = this.plantStack.pop();
    console.log('Planta removida de la pila de historial', this.plantStack);
    return plant;
  }

  peekPlantStack(): Plant | undefined {
    return this.plantStack.length > 0 ? this.plantStack[this.plantStack.length - 1] : undefined;
  }

  getPlantStackSize(): number {
    return this.plantStack.length;
  }

  // Implementación de cola (Queue) para operaciones pendientes
  private operationQueue: { operation: string, data: any }[] = [];

  enqueueOperation(operation: string, data: any): void {
    this.operationQueue.push({ operation, data });
    console.log('Operación agregada a la cola', this.operationQueue);
  }

  dequeueOperation(): { operation: string, data: any } | undefined {
    const operation = this.operationQueue.shift();
    console.log('Operación removida de la cola', this.operationQueue);
    return operation;
  }

  peekOperationQueue(): { operation: string, data: any } | undefined {
    return this.operationQueue.length > 0 ? this.operationQueue[0] : undefined;
  }

  getOperationQueueSize(): number {
    return this.operationQueue.length;
  }

  // Implementación de array circular para rotación de plantas recomendadas
  private recommendedPlantsArray: Plant[] = [];
  private currentIndex: number = 0;
  private readonly maxSize: number = 5;

  addToRecommendedPlants(plant: Plant): void {
    if (this.recommendedPlantsArray.length < this.maxSize) {
      this.recommendedPlantsArray.push(plant);
    } else {
      this.recommendedPlantsArray[this.currentIndex] = plant;
      this.currentIndex = (this.currentIndex + 1) % this.maxSize;
    }
    console.log('Planta agregada al array circular', this.recommendedPlantsArray);
  }

  getRecommendedPlants(): Plant[] {
    return [...this.recommendedPlantsArray];
  }

  // Implementación de lista enlazada simple para tareas de cuidado
  private careTasksHead: TaskNode | null = null;

  addCareTask(task: string): void {
    const newNode: TaskNode = { value: task, next: null };
    if (!this.careTasksHead) {
      this.careTasksHead = newNode;
    } else {
      let current = this.careTasksHead;
      while (current.next) {
        current = current.next;
      }
      current.next = newNode;
    }
    console.log('Tarea de cuidado agregada a la lista enlazada');
  }

  getCareTasksList(): string[] {
    const tasks: string[] = [];
    let current = this.careTasksHead;
    while (current) {
      tasks.push(current.value);
      current = current.next;
    }
    return tasks;
  }
}