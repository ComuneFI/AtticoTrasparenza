import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecretiComponent } from './decreti.component';

describe('DecretiComponent', () => {
  let component: DecretiComponent;
  let fixture: ComponentFixture<DecretiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecretiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecretiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
