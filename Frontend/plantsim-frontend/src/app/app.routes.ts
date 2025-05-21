import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { PlantDetailComponent } from './components/plant-detail/plant-detail.component';
import { PlantFormComponent } from './components/plant-form/plant-form.component';
import { SimuladorComponent } from './components/simulador/simulador.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'plantas', component: PlantListComponent },
  { path: 'plantas/nueva', component: PlantFormComponent },
  { path: 'plantas/editar/:id', component: PlantFormComponent },
  { path: 'plantas/:id', component: PlantDetailComponent },
  { path: 'plantas/:id/simular', component: SimuladorComponent },
  { path: '**', redirectTo: '/home' }
];