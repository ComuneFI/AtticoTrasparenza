import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaOrdRowComponent } from './tabella-ord-row.component';

describe('TabellaOrdRowComponent', () => {
  let component: TabellaOrdRowComponent;
  let fixture: ComponentFixture<TabellaOrdRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaOrdRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaOrdRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
