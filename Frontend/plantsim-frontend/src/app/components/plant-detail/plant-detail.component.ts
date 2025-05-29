import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { PlantService } from '../../services/plant.service';
import { SimulationService } from '../../services/simulation.service';

@Component({
  selector: 'app-plant-detail',
  standalone: true,
  imports: [NgIf, RouterLink],
  templateUrl: './plant-detail.component.html',
  styleUrls: ['./plant-detail.component.scss']
})
export class PlantDetailComponent implements OnInit {
  plant: Plant | undefined;
  isLoading = true;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private plantService: PlantService,
    private simulationService: SimulationService
  ) {}

  ngOnInit(): void {
    this.loadPlant();
  }

  loadPlant(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.plantService.getPlantById(id).subscribe({
      next: (plant) => {
        this.plant = plant;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'No se pudo cargar la planta. Por favor, inténtalo de nuevo.';
        this.isLoading = false;
        console.error('Error loading plant:', error);
      }
    });
  }

  goToSimulator(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plants', this.plant.id, 'simulate']);
    }
  }

  addToRecommended(): void {
    if (this.plant) {
      this.simulationService.addToRecommendedPlants(this.plant);
      alert('Planta añadida a recomendadas');
    }
  }

  addCareTask(task: string): void {
    if (this.plant) {
      this.simulationService.addCareTask(this.plant.name, task);
      alert('Tarea de cuidado añadida');
    }
  }

  goBack(): void {
    this.router.navigate(['/plants']);
  }

  editPlant(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plants/edit', this.plant.id]);
    }
  }

  deletePlant(): void {
    if (this.plant && this.plant.id && confirm('¿Estás seguro de que quieres eliminar esta planta?')) {
      this.plantService.deletePlant(this.plant.id).subscribe({
        next: () => {
          this.router.navigate(['/plants']);
        },
        error: (error) => {
          console.error('Error deleting plant:', error);
          alert('No se pudo eliminar la planta. Por favor, inténtalo de nuevo.');
        }
      });
    }
  }
}
