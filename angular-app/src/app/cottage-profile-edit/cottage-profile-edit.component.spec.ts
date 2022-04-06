import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageProfileEditComponent } from './cottage-profile-edit.component';

describe('CottageProfileEditComponent', () => {
  let component: CottageProfileEditComponent;
  let fixture: ComponentFixture<CottageProfileEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageProfileEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageProfileEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
