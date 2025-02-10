import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmindownloadComponent } from './admindownload.component';

describe('AdmindownloadComponent', () => {
  let component: AdmindownloadComponent;
  let fixture: ComponentFixture<AdmindownloadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmindownloadComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmindownloadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
