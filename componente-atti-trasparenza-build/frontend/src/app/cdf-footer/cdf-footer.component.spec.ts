import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CdfFooterComponent } from './cdf-footer.component';

describe('CdfFooterComponent', () => {
  let component: CdfFooterComponent;
  let fixture: ComponentFixture<CdfFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CdfFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CdfFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
