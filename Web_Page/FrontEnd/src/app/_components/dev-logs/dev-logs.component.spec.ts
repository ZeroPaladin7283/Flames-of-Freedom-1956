import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevLogsComponent } from './dev-logs.component';

describe('DevLogsComponent', () => {
  let component: DevLogsComponent;
  let fixture: ComponentFixture<DevLogsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DevLogsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevLogsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
