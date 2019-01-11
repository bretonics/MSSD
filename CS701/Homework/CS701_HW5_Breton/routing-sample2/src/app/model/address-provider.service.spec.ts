import { TestBed, inject } from '@angular/core/testing';

import { AddressProviderService } from './address-provider.service';

describe('AddressProviderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddressProviderService]
    });
  });

  it('should ...', inject([AddressProviderService], (service: AddressProviderService) => {
    expect(service).toBeTruthy();
  }));
});
