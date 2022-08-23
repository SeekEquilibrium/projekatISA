import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCottageComponent } from './register-cottage.component';

describe('RegisterCottageComponent', () => {
  let component: RegisterCottageComponent;
  let fixture: ComponentFixture<RegisterCottageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterCottageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCottageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
