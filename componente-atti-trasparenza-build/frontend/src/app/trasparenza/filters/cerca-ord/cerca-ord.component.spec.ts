import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CercaOrdComponent } from './cerca-ord.component';

describe('CercaOrdComponent', () => {
  let component: CercaOrdComponent;
  let fixture: ComponentFixture<CercaOrdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CercaOrdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CercaOrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
