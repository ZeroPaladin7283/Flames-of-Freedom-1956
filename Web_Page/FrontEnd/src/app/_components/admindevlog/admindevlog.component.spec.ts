import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmindevlogComponent } from './admindevlog.component';

describe('AdmindevlogComponent', () => {
  let component: AdmindevlogComponent;
  let fixture: ComponentFixture<AdmindevlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmindevlogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmindevlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
