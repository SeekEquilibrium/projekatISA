import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottagesReservationsComponent } from './cottages-reservations.component';

describe('CottagesReservationsComponent', () => {
  let component: CottagesReservationsComponent;
  let fixture: ComponentFixture<CottagesReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottagesReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottagesReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
