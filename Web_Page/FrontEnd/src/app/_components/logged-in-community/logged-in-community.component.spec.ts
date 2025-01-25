import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInCommunityComponent } from './logged-in-community.component';

describe('LoggedInCommunityComponent', () => {
  let component: LoggedInCommunityComponent;
  let fixture: ComponentFixture<LoggedInCommunityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedInCommunityComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInCommunityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
