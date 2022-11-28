import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CercaDecComponent } from './cerca-dec.component';

describe('CercaDecComponent', () => {
  let component: CercaDecComponent;
  let fixture: ComponentFixture<CercaDecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CercaDecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CercaDecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
