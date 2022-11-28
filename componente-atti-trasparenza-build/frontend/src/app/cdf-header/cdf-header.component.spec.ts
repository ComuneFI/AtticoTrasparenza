import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CdfHeaderComponent } from './cdf-header.component';

describe('CdfHeaderComponent', () => {
  let component: CdfHeaderComponent;
  let fixture: ComponentFixture<CdfHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CdfHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CdfHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
