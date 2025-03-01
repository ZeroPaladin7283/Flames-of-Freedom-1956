import { TestBed } from '@angular/core/testing';

import { DevLogsService } from './dev-logs.service';

describe('DevLogsService', () => {
  let service: DevLogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DevLogsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
