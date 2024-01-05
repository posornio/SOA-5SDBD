import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenevolesTableComponent } from './benevoles-table.component';

describe('BenevolesTableComponent', () => {
  let component: BenevolesTableComponent;
  let fixture: ComponentFixture<BenevolesTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BenevolesTableComponent]
    });
    fixture = TestBed.createComponent(BenevolesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
