import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaDecComponent } from './tabella-dec.component';

describe('TabellaDecComponent', () => {
  let component: TabellaDecComponent;
  let fixture: ComponentFixture<TabellaDecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaDecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaDecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
