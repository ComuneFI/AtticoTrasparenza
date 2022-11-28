import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaPdRowComponent } from './tabella-dec-row.component';

describe('TabellaDecRowComponent', () => {
  let component: TabellaDecRowComponent;
  let fixture: ComponentFixture<TabellaDecRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaDecRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaDecRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
