import { Component, OnInit } from '@angular/core';
import { NgIf } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { PlantService } from '../../services/plant.service';
import { SimulacionService } from '../../services/simulation.service';

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
    private simulacionService: SimulacionService
  ) { }

  ngOnInit(): void {
    this.getPlant();
  }

  getPlant(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.plantService.getPlantById(id).subscribe({
      next: (plant) => {
        this.plant = plant;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'No se pudo cargar la planta. Por favor, inténtalo de nuevo.';
        this.isLoading = false;
        console.error('Error al cargar la planta:', error);
      }
    });
  }

  irSimulador(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plantas', this.plant.id, 'simular']);
    }
  }

  addToRecommended(): void {
    if (this.plant) {
      this.simulacionService.addToRecommendedPlants(this.plant);
      alert('Planta añadida a recomendadas');
    }
  }

  addCareTask(task: string): void {
    if (this.plant) {
      this.simulacionService.addCareTask(this.plant.nombre, task);
      alert('Tarea de cuidado añadida');
    }
  }

  goBack(): void {
    this.router.navigate(['/plantas']);
  }

  editPlant(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plantas/editar', this.plant.id]);
    }
  }

  deletePlant(): void {
    if (this.plant && this.plant.id && confirm('¿Estás seguro de que quieres eliminar esta planta?')) {
      this.plantService.deletePlant(this.plant.id).subscribe({
        next: () => {
          this.router.navigate(['/plantas']);
        },
        error: (error) => {
          console.error('Error al eliminar la planta:', error);
          alert('No se pudo eliminar la planta. Por favor, inténtalo de nuevo.');
        }
      });
    }
  }
}