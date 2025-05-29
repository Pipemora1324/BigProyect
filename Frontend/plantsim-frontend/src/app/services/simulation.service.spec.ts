import { TestBed } from '@angular/core/testing';

import { SimulacionService } from './simulation.service';

describe('SimulacionService', () => {
  let service: SimulacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SimulacionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
 