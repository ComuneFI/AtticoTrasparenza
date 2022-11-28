import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaPdRowComponent } from './tabella-pd-row.component';

describe('TabellaPdRowComponent', () => {
  let component: TabellaPdRowComponent;
  let fixture: ComponentFixture<TabellaPdRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaPdRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaPdRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
