import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaAcComponent } from './tabella-ac.component';

describe('TabellaAcComponent', () => {
  let component: TabellaAcComponent;
  let fixture: ComponentFixture<TabellaAcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaAcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaAcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
