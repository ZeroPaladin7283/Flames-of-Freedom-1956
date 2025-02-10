import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInMediaComponent } from './logged-in-media.component';

describe('LoggedInMediaComponent', () => {
  let component: LoggedInMediaComponent;
  let fixture: ComponentFixture<LoggedInMediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedInMediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
