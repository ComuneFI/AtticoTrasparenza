import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaPdComponent } from './tabella-pd.component';

describe('TabellaPdComponent', () => {
  let component: TabellaPdComponent;
  let fixture: ComponentFixture<TabellaPdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaPdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaPdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
