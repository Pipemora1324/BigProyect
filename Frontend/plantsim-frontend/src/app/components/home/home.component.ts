import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Plant } from '../../models/plant';
import { SimulationService } from '../../services/simulation.service';

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

  constructor(private simulationService: SimulationService) { }

  ngOnInit(): void {
    // Get recommended plants from our circular array
    this.recommendedPlants = this.simulationService.getRecommendedPlants();
    
    // Get care tasks from our linked list
    this.careTasksList = this.simulationService.getCareTasksList();
  }
}
