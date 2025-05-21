import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Plant } from '../../models/plant';
import { PlantService } from '../../services/plant.service';

@Component({
  selector: 'app-plant-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, NgFor],
  templateUrl: './plant-form.component.html',
  styleUrls: ['./plant-form.component.scss']
})
export class PlantFormComponent implements OnInit {
  plantForm: FormGroup;
  isEditMode = false;
  plantId: number | null = null;
  isSubmitting = false;
  errorMessage = '';
  tiposSuelo: string[] = ['arenoso', 'franco', 'arcilloso', 'limoso'];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private plantService: PlantService
  ) {
    this.plantForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(2)]],
      especie: ['', Validators.required],
      edad: [1, [Validators.required, Validators.min(0)]],
      tipoSuelo: ['franco', Validators.required]
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      this.plantId = +idParam;
      this.loadPlantData();
    }
  }

  loadPlantData(): void {
    if (this.plantId) {
      this.plantService.getPlantById(this.plantId).subscribe({
        next: (plant) => {
          this.plantForm.patchValue({
            nombre: plant.nombre,
            especie: plant.especie,
            edad: plant.edad,
            tipoSuelo: plant.tipoSuelo
          });
        },
        error: (error) => {
          console.error('Error al cargar la planta:', error);
          this.errorMessage = 'No se pudo cargar la información de la planta.';
        }
      });
    }
  }

  onSubmit(): void {
    if (this.plantForm.valid) {
      this.isSubmitting = true;
      
      const plant: Plant = {
        ...this.plantForm.value
      };

      if (this.isEditMode && this.plantId) {
        // Actualizar planta existente
        this.plantService.updatePlant(this.plantId, plant).subscribe({
          next: () => {
            this.router.navigate(['/plantas', this.plantId]);
          },
          error: (error) => {
            console.error('Error al actualizar la planta:', error);
            this.errorMessage = 'No se pudo actualizar la planta. Por favor, inténtalo de nuevo.';
            this.isSubmitting = false;
          }
        });
      } else {
        // Crear nueva planta
        this.plantService.createPlant(plant).subscribe({
          next: (newPlant) => {
            this.router.navigate(['/plantas', newPlant.id]);
          },
          error: (error) => {
            console.error('Error al crear la planta:', error);
            this.errorMessage = 'No se pudo crear la planta. Por favor, inténtalo de nuevo.';
            this.isSubmitting = false;
          }
        });
      }
    } else {
      // Marcar todos los campos como tocados para mostrar errores
      Object.keys(this.plantForm.controls).forEach(key => {
        const control = this.plantForm.get(key);
        control?.markAsTouched();
      });
    }
  }

  cancel(): void {
    if (this.isEditMode && this.plantId) {
      this.router.navigate(['/plantas', this.plantId]);
    } else {
      this.router.navigate(['/plantas']);
    }
  }
}