import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { EnvironmentalConditions } from '../../models/environmental-conditions';
import { SimulationResult } from '../../models/simulation-result';
import { PlantService } from '../../services/plant.service';
import { SimulationService } from '../../services/simulation.service';

@Component({
  selector: 'app-simulator',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule, RouterLink],
  templateUrl: './simulator.component.html',
  styleUrls: ['./simulator.component.scss']
})
export class SimulatorComponent implements OnInit {
  plant: Plant | undefined;
  conditions: EnvironmentalConditions = {
    temperature: 20,
    humidity: 50,
    soilType: 'Franco',
    daylightHours: 10 // CAMBIO: de lightHours a daylightHours
  };
  result: SimulationResult | undefined;
  isLoading = true;
  isSimulating = false;
  errorMessage = '';
  simulationDone = false;
  soilTypes: string[] = ['Arenoso', 'Franco', 'Arcilloso', 'Limoso'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private plantService: PlantService,
    private simulationService: SimulationService
  ) { }

  ngOnInit(): void {
    this.loadPlant();
  }

  loadPlant(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.plantService.getPlantById(id).subscribe({
      next: (plant) => {
        this.plant = plant;
        if (plant && plant.soilType) {
          this.conditions.soilType = plant.soilType;
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading plant:', error);
        this.errorMessage = 'No se pudo cargar la información de la planta.';
        this.isLoading = false;
      }
    });
  }

  simulateGrowth(): void {
    if (!this.plant || !this.plant.id) {
      console.error('Plant or plant ID is missing:', this.plant);
      return;
    }

    console.log('Starting simulation for plant:', this.plant.id, 'with conditions:', this.conditions);
    
    this.isSimulating = true;
    this.errorMessage = ''; // Limpiar errores previos
    
    this.simulationService.simulateGrowth(this.plant.id, this.conditions).subscribe({
      next: (result) => {
        console.log('Simulation successful:', result);
        this.result = result;
        this.simulationDone = true;
        this.isSimulating = false;

        if (result.growth > 0.7 && this.plant) {
          this.simulationService.addToRecommendedPlants(this.plant);
        }

        if (result.healthStatus === 'Malo' && this.plant) {
          this.simulationService.addCareTask(this.plant.name, 'Revisar urgentemente: ' + result.recommendation);
        }
      },
      error: (error) => {
        console.error('Detailed simulation error:', error);
        console.log('Error status:', error.status);
        console.log('Error message:', error.message);
        console.log('Full error object:', error);
        
        // Mensaje de error más específico
        if (error.status === 404) {
          this.errorMessage = 'No se encontró la planta para simular.';
        } else if (error.status === 0) {
          this.errorMessage = 'No se puede conectar con el servidor. Verifica que esté ejecutándose.';
        } else {
          this.errorMessage = `Error en la simulación: ${error.status} - ${error.message || 'Error desconocido'}`;
        }
        
        this.isSimulating = false;
      }
    });
  }

  getHealthColorClass(): string {
    if (!this.result) return '';
    if (this.result.healthStatus === 'Excelente') return 'text-success';
    if (this.result.healthStatus === 'Bueno') return 'text-primary';
    if (this.result.healthStatus === 'Regular') return 'text-warning';
    if (this.result.healthStatus === 'Malo') return 'text-danger';
    return '';
  }

  getProgressBarClass(): string {
    if (!this.result) return 'bg-secondary';
    if (this.result.growth > 0.7) return 'bg-success';
    if (this.result.growth > 0.4) return 'bg-warning';
    return 'bg-danger';
  }

  getProgressBarWidth(): string {
    if (!this.result) return '0%';
    return `${this.result.growth * 100}%`;
  }

  goBack(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plants', this.plant.id]);
    } else {
      this.router.navigate(['/plants']);
    }
  }
}