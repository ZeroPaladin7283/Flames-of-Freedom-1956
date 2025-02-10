import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInDevlogComponent } from './logged-in-devlog.component';

describe('LoggedInDevlogComponent', () => {
  let component: LoggedInDevlogComponent;
  let fixture: ComponentFixture<LoggedInDevlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedInDevlogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInDevlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
