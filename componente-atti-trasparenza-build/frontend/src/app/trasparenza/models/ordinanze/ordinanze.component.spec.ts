import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdinanzeComponent } from './ordinanze.component';

describe('OrdinanzeComponent', () => {
  let component: OrdinanzeComponent;
  let fixture: ComponentFixture<OrdinanzeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdinanzeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdinanzeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
