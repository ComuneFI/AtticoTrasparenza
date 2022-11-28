import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaOrdComponent } from './tabella-ord.component';

describe('TabellaOrdComponent', () => {
  let component: TabellaOrdComponent;
  let fixture: ComponentFixture<TabellaOrdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaOrdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaOrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
