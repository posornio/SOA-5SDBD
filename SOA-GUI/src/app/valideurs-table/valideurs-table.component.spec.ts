import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValideursTableComponent } from './valideurs-table.component';

describe('ValideursTableComponent', () => {
  let component: ValideursTableComponent;
  let fixture: ComponentFixture<ValideursTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ValideursTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ValideursTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
