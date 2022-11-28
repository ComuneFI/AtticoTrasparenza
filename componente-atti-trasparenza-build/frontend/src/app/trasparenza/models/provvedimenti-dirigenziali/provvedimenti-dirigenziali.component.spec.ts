import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvvedimentiDirigenzialiComponent } from './provvedimenti-dirigenziali.component';

describe('ProvvedimentiDirigenzialiComponent', () => {
  let component: ProvvedimentiDirigenzialiComponent;
  let fixture: ComponentFixture<ProvvedimentiDirigenzialiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvvedimentiDirigenzialiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvvedimentiDirigenzialiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
