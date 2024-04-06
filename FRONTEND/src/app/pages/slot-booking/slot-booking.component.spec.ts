import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SlotBookingComponent } from './slot-booking.component';

describe('SlotBookingComponent', () => {
  let component: SlotBookingComponent;
  let fixture: ComponentFixture<SlotBookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SlotBookingComponent]
    });
    fixture = TestBed.createComponent(SlotBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
