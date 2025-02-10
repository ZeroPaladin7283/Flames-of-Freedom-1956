import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminmediaComponent } from './adminmedia.component';

describe('AdminmediaComponent', () => {
  let component: AdminmediaComponent;
  let fixture: ComponentFixture<AdminmediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminmediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminmediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
