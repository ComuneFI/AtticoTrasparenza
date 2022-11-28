import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CercaPdComponent } from './cerca-pd.component';

describe('CercaPdComponent', () => {
  let component: CercaPdComponent;
  let fixture: ComponentFixture<CercaPdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CercaPdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CercaPdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
