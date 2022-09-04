import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatDefineActionsComponent } from './boat-define-actions.component';

describe('BoatDefineActionsComponent', () => {
  let component: BoatDefineActionsComponent;
  let fixture: ComponentFixture<BoatDefineActionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatDefineActionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatDefineActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
