import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedinnavbarComponent } from './loggedinnavbar.component';

describe('LoggedinnavbarComponent', () => {
  let component: LoggedinnavbarComponent;
  let fixture: ComponentFixture<LoggedinnavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedinnavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoggedinnavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
