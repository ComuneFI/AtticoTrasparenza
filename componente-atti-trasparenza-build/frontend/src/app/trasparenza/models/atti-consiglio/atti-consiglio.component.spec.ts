import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttiConsiglioComponent } from './atti-consiglio.component';

describe('ProvvedimentiDirigenzialiComponent', () => {
  let component: AttiConsiglioComponent;
  let fixture: ComponentFixture<AttiConsiglioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttiConsiglioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttiConsiglioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
