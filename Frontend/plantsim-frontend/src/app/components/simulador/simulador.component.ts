import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { CondicionesAmbientales } from '../../models/environmental-conditions';
import { ResultadoSimulacion } from '../../models/simulation-result';
import { PlantService } from '../../services/plant.service';
import { SimulacionService } from '../../services/simulation.service';

@Component({
  selector: 'app-simulador',
  standalone: true,
  imports: [NgIf, NgFor, FormsModule, RouterLink],
  templateUrl: './simulador.component.html',
  styleUrls: ['./simulador.component.scss']
})
export class SimuladorComponent implements OnInit {
  plant: Plant | undefined;
  condiciones: CondicionesAmbientales = {
    temperatura: 20,
    humedad: 50,
    tipoSuelo: 'franco',
    horasLuz: 10
  };
  resultado: ResultadoSimulacion | undefined;
  isLoading = true;
  isSimulating = false;
  errorMessage = '';
  simulacionRealizada = false;
  tiposSuelo: string[] = ['arenoso', 'franco', 'arcilloso', 'limoso'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private plantService: PlantService,
    private simulacionService: SimulacionService
  ) { }

  ngOnInit(): void {
    this.loadPlant();
  }

  loadPlant(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.plantService.getPlantById(id).subscribe({
      next: (plant) => {
        this.plant = plant;
        if (plant && plant.tipoSuelo) {
          this.condiciones.tipoSuelo = plant.tipoSuelo;
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error al cargar la planta:', error);
        this.errorMessage = 'No se pudo cargar la información de la planta.';
        this.isLoading = false;
      }
    });
  }

  simularCrecimiento(): void {
    if (!this.plant || !this.plant.id) return;

    this.isSimulating = true;
    this.simulacionService.simularCrecimiento(this.plant.id, this.condiciones).subscribe({
      next: (resultado) => {
        this.resultado = resultado;
        this.simulacionRealizada = true;
        this.isSimulating = false;
        
        // Si el crecimiento es bueno, añadir a plantas recomendadas
        if (resultado.crecimiento > 0.7 && this.plant) {
          this.simulacionService.addToRecommendedPlants(this.plant);
        }
        
        // Si el estado de salud es malo, añadir tarea de cuidado
        if (resultado.estadoSalud === 'Malo' && this.plant) {
          this.simulacionService.addCareTask(this.plant.nombre, 'Revisar urgentemente: ' + resultado.recomendacion);
        }
      },
      error: (error) => {
        console.error('Error al simular el crecimiento:', error);
        this.errorMessage = 'No se pudo realizar la simulación. Por favor, inténtalo de nuevo.';
        this.isSimulating = false;
      }
    });
  }

  getColorClass(): string {
    if (!this.resultado) return '';
    
    if (this.resultado.estadoSalud === 'Excelente') return 'text-success';
    if (this.resultado.estadoSalud === 'Bueno') return 'text-primary';
    if (this.resultado.estadoSalud === 'Regular') return 'text-warning';
    if (this.resultado.estadoSalud === 'Malo') return 'text-danger';
    
    return '';
  }

  getProgressBarClass(): string {
    if (!this.resultado) return 'bg-secondary';
    
    if (this.resultado.crecimiento > 0.7) return 'bg-success';
    if (this.resultado.crecimiento > 0.4) return 'bg-warning';
    return 'bg-danger';
  }

  getProgressBarWidth(): string {
    if (!this.resultado) return '0%';
    return `${this.resultado.crecimiento * 100}%`;
  }

  volver(): void {
    if (this.plant && this.plant.id) {
      this.router.navigate(['/plantas', this.plant.id]);
    } else {
      this.router.navigate(['/plantas']);
    }
  }
}