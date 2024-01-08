import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvisTableComponent } from './avis-table.component';

describe('AvisTableComponent', () => {
  let component: AvisTableComponent;
  let fixture: ComponentFixture<AvisTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvisTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvisTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
