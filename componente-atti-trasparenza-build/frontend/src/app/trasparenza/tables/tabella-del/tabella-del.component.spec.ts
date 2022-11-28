import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabellaDelComponent } from './tabella-del.component';

describe('TabellaDelComponent', () => {
  let component: TabellaDelComponent;
  let fixture: ComponentFixture<TabellaDelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabellaDelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabellaDelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
