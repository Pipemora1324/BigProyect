import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlantListComponent } from './components/plant-list/plant-list.component';
import { PlantDetailComponent } from './components/plant-detail/plant-detail.component';
import { PlantFormComponent } from './components/plant-form/plant-form.component';
import { SimulatorComponent } from './components/simulador/simulator.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  // CAMBIOS: /plantas → /plants para coincidir con los HTML
  { path: 'plants', component: PlantListComponent },
  { path: 'plants/new', component: PlantFormComponent },
  { path: 'plants/edit/:id', component: PlantFormComponent },
  { path: 'plants/:id', component: PlantDetailComponent },
  { path: 'plants/:id/simulate', component: SimulatorComponent },
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }