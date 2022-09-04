import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatsReservationsComponent } from './boats-reservations.component';

describe('BoatsReservationsComponent', () => {
  let component: BoatsReservationsComponent;
  let fixture: ComponentFixture<BoatsReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatsReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatsReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
