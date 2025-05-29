import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SimulatorComponent } from './simulator.component';

describe('SimulatorComponent', () => {
  let component: SimulatorComponent;
  let fixture: ComponentFixture<SimulatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SimulatorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SimulatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  // Additional suggestions you can include:
  // it('should initialize default environment conditions', () => {
  //   expect(component.conditions.temperature).toBeDefined();
  //   expect(component.conditions.humidity).toBeDefined();
  //   expect(component.conditions.soilType).toBeDefined();
  //   expect(component.conditions.lightHours).toBeDefined();
  // });
});
