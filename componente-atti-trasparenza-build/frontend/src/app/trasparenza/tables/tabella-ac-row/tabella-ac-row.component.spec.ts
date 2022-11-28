import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaAcRowComponent } from './tabella-ac-row.component';

describe('TabellaAcRowComponent', () => {
  let component: TabellaAcRowComponent;
  let fixture: ComponentFixture<TabellaAcRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaAcRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaAcRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
