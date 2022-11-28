import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CercaAcComponent } from './cerca-ac.component';

describe('CercaAcComponent', () => {
  let component: CercaAcComponent;
  let fixture: ComponentFixture<CercaAcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CercaAcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CercaAcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
