import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedinfooterComponent } from './loggedinfooter.component';

describe('LoggedinfooterComponent', () => {
  let component: LoggedinfooterComponent;
  let fixture: ComponentFixture<LoggedinfooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedinfooterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedinfooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
