import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageHorizontalCardComponent } from './cottage-horizontal-card.component';

describe('CottageHorizontalCardComponent', () => {
  let component: CottageHorizontalCardComponent;
  let fixture: ComponentFixture<CottageHorizontalCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CottageHorizontalCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageHorizontalCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
