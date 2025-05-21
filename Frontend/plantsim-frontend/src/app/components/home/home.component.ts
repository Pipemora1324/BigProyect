import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { SimulacionService } from '../../services/simulacion.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NgIf, NgFor, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  recommendedPlants: Plant[] = [];
  careTasksList: string[] = [];

  constructor(private simulacionService: SimulacionService) { }

  ngOnInit(): void {
    // Obtener plantas recomendadas de nuestro array circular
    this.recommendedPlants = this.simulacionService.getRecommendedPlants();
    
    // Obtener tareas de cuidado de nuestra lista enlazada
    this.careTasksList = this.simulacionService.getCareTasksList();
  }
}