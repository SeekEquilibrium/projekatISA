import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatHorizontalCardComponent } from './boat-horizontal-card.component';

describe('BoatHorizontalCardComponent', () => {
  let component: BoatHorizontalCardComponent;
  let fixture: ComponentFixture<BoatHorizontalCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoatHorizontalCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatHorizontalCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
