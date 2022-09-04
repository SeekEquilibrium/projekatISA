import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatDefineAvailabilityComponent } from './boat-define-availability.component';

describe('BoatDefineAvailabilityComponent', () => {
  let component: BoatDefineAvailabilityComponent;
  let fixture: ComponentFixture<BoatDefineAvailabilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatDefineAvailabilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatDefineAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
