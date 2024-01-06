import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AidesTableComponent } from './aides-table.component';

describe('AidesTableComponent', () => {
  let component: AidesTableComponent;
  let fixture: ComponentFixture<AidesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AidesTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AidesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
