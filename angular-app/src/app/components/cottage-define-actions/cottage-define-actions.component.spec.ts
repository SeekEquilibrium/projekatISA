import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageDefineActionsComponent } from './cottage-define-actions.component';

describe('CottageDefineActionsComponent', () => {
  let component: CottageDefineActionsComponent;
  let fixture: ComponentFixture<CottageDefineActionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageDefineActionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageDefineActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
