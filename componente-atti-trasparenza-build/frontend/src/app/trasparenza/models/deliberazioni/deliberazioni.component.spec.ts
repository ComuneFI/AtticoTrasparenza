import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliberazioniComponent } from './deliberazioni.component';

describe('DeliberazioniComponent', () => {
  let component: DeliberazioniComponent;
  let fixture: ComponentFixture<DeliberazioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliberazioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliberazioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
