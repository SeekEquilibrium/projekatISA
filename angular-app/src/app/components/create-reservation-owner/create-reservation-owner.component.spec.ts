import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateReservationOwnerComponent } from './create-reservation-owner.component';

describe('CreateReservationOwnerComponent', () => {
  let component: CreateReservationOwnerComponent;
  let fixture: ComponentFixture<CreateReservationOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateReservationOwnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateReservationOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
