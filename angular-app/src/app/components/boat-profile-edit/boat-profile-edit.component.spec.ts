import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatProfileEditComponent } from './boat-profile-edit.component';

describe('BoatProfileEditComponent', () => {
  let component: BoatProfileEditComponent;
  let fixture: ComponentFixture<BoatProfileEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatProfileEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatProfileEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
