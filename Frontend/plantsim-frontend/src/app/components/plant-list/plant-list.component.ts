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
  plants: Plant[] = [];
  recentlyViewedPlant: Plant | undefined;
  pendingOperation: { operation: string, data: any } | undefined;

  constructor(
    private plantService: PlantService,
    private dataStructuresService: DataStructuresService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadPlants();
    this.recentlyViewedPlant = this.plantService.getLastViewedPlant();
    this.pendingOperation = this.dataStructuresService.peekOperationQueue();
  }

  loadPlants(): void {
    this.plantService.getPlants().subscribe({
      next: (plants) => {
        this.plants = plants;
      },
      error: (error) => {
        console.error('Error al cargar plantas', error);
      }
    });
  }

  deletePlant(id: number | undefined): void {
    if (id !== undefined && confirm('¿Estás seguro de que deseas eliminar esta planta?')) {
      this.plantService.deletePlant(id).subscribe({
        next: () => {
          this.loadPlants();
        },
        error: (error) => {
          console.error('Error al eliminar planta', error);
        }
      });
    }
  }

  goBackToPrevious(): void {
    const previousPlant = this.plantService.goBackInHistory();
    if (previousPlant && previousPlant.id) {
      this.router.navigate(['/plantas', previousPlant.id]);
    } else {
      this.recentlyViewedPlant = undefined;
    }
  }
}
