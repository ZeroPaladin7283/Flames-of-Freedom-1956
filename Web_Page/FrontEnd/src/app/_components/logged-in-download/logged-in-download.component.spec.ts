import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInDownloadComponent } from './logged-in-download.component';

describe('LoggedInDownloadComponent', () => {
  let component: LoggedInDownloadComponent;
  let fixture: ComponentFixture<LoggedInDownloadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedInDownloadComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedInDownloadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
