import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmincommunityComponent } from './admincommunity.component';

describe('AdmincommunityComponent', () => {
  let component: AdmincommunityComponent;
  let fixture: ComponentFixture<AdmincommunityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmincommunityComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmincommunityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
