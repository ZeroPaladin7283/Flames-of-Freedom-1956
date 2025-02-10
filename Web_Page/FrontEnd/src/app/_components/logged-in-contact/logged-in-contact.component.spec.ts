import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInContactComponent } from './logged-in-contact.component';

describe('LoggedInContactComponent', () => {
  let component: LoggedInContactComponent;
  let fixture: ComponentFixture<LoggedInContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedInContactComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
