import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeneficiairesTableComponent } from './beneficiaires-table.component';

describe('BeneficiairesTableComponent', () => {
  let component: BeneficiairesTableComponent;
  let fixture: ComponentFixture<BeneficiairesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeneficiairesTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BeneficiairesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
