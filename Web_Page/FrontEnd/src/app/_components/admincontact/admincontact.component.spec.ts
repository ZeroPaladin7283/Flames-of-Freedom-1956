import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmincontactComponent } from './admincontact.component';

describe('AdmincontactComponent', () => {
  let component: AdmincontactComponent;
  let fixture: ComponentFixture<AdmincontactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmincontactComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmincontactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
