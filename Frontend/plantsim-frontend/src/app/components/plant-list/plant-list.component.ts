import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf, JsonPipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { PlantService } from '../../services/plant.service';
import { DataStructuresService } from '../../services/data-structures.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plant-list',
  standalone: true,
  imports: [NgIf, NgFor, RouterLink, JsonPipe],
  templateUrl: './plant-list.component.html',
  styleUrls: ['./plant-list.component.scss']
})
export class PlantListComponent implements OnInit {
  plantas: Plant[] = [];
  historialPlantas: Plant | undefined;
  operacionPendiente: { operation: string, data: any } | undefined;

  constructor(
    private plantService: PlantService,
    private dataStructuresService: DataStructuresService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarPlantas();
    this.historialPlantas = this.plantService.getLastViewedPlant();
    this.operacionPendiente = this.dataStructuresService.peekOperationQueue();
  }

  cargarPlantas(): void {
    this.plantService.getPlants().subscribe({
      next: (plantas) => {
        this.plantas = plantas;
      },
      error: (error) => {
        console.error('Error al cargar plantas', error);
      }
    });
  }

  eliminarPlanta(id: number | undefined): void {
    if (id !== undefined && confirm('¿Estás seguro de que deseas eliminar esta planta?')) {
      this.plantService.deletePlant(id).subscribe({
        next: () => {
          this.cargarPlantas();
        },
        error: (error) => {
          console.error('Error al eliminar planta', error);
        }
      });
    }
  }

  volverAlAnterior(): void {
    const plantaAnterior = this.plantService.goBackInHistory();
    if (plantaAnterior && plantaAnterior.id) {
      this.router.navigate(['/plantas', plantaAnterior.id]);
    } else {
      this.historialPlantas = undefined;
    }
  }
}