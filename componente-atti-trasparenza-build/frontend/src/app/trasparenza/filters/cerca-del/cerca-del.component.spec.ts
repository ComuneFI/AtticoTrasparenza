import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CercaDelComponent } from './cerca-del.component';

describe('CercaDelComponent', () => {
  let component: CercaDelComponent;
  let fixture: ComponentFixture<CercaDelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CercaDelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CercaDelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
