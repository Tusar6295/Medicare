import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { BookModalComponent } from 'src/app/modals/book-modal/book-modal.component';
import { Appointment } from 'src/app/models/appointment';
import { Doctor } from 'src/app/models/doctor.model';
import { DoctorAppointmentsService } from 'src/app/services/doctor-appointments.service';
import { PatientAppointmentsService } from 'src/app/services/patient-appointments.service';
type StringPair = [string, string];
@Component({
  selector: 'app-slot-booking',
  templateUrl: './slot-booking.component.html',
  styleUrls: ['./slot-booking.component.css']
})
export class SlotBookingComponent implements OnInit {

  timeMap: string[] = [
    '10:00 to 10:15', '10:15 to 10:30', '10:30 to 10:45', '10:45 to 11:00',
    '11:00 to 11:15', '11:15 to 11:30', '11:30 to 11:45', '11:45 to 12:00',
    '12:00 to 12:15', '12:15 to 12:30', '12:30 to 12:45', '12:45 to 13:00',
    '14:00 to 14:15', '14:15 to 14:30', '14:30 to 14:45', '14:45 to 15:00',
    '15:00 to 15:15', '15:15 to 15:30', '15:30 to 15:45', '15:45 to 16:00',
    '16:00 to 16:15', '16:15 to 16:30', '16:30 to 16:45', '16:45 to 17:00'
  ];

  // slotMap: { [key: string]: boolean } = {};
  doctorAppointments: Appointment[] = [];
  patientAppointments: Appointment[] = [];
  // appointmentMap: {[key: string]:boolean} ={};
  otherPatientBookedSlots: {[key: string]:boolean} ={}
  currentPatientBookedSlots: {[key: string]:boolean} ={};
  bookedWithOtherDoctor: {[key: string]:boolean} ={};

  selectedDate: string = "";
  selectedSlot: string = "";
  slots: string[] = [];
  feedback: { review: string; rating: number; }[] = [];
  doctorDetails: Doctor = {
    id: 0,
    name: '',
    gender: '',
    qualification: '',
    email: '',
    fees: 0,
    experienceStart: '',
    specialization: '',
    leaveStart: '',
    leaveEnd: '',
    rating: 0,
    status: '',
    userName: '',
    password: '',
    profileImgUrl: ''
  };

  constructor(private doctorAppointmentsService: DoctorAppointmentsService,
    private dialog: MatDialog, private route: ActivatedRoute,
    private patientAppointmentsService: PatientAppointmentsService) {

  }

  availableDates: Date[] = [];

  ngOnInit(): void {
    this.generateDates();
    let doctorId: Number;
    this.route.params.subscribe(params => {
      doctorId = params['id'];

      this.doctorAppointmentsService.getParticularDoctor(doctorId).subscribe({
        next: (data: Doctor) => {
          this.doctorDetails = data;
          console.log("doctor fetched", data);
          console.log("doctor fetched", this.doctorDetails);
          this.loadReviews();
        },
        error: (err: HttpErrorResponse) => {
          console.log("Error in fetching doctor details", err);
        }
      })
    });

    this.selectedDate = "";
    this.selectedSlot = "";
  }

  generateDates() {
    const today = new Date();
    for (let i = 0; i < 7; i++) {
      const newDate = new Date(today.getTime() + ((i + 1) * 24 * 60 * 60 * 1000));
      this.availableDates.push(newDate);
    }
  }

  getStarArray(rating: number | undefined): number[] {
    if (rating === undefined)
      return Array(5).fill(0)
    const roundedRating = Math.round(rating);
    return Array(roundedRating).fill(0);
  }

  onDateClick(date: Date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    const formattedDate = `${year}-${month}-${day}`;
    this.selectedDate = formattedDate;
    this.selectedSlot = "";
    console.log("selected date:", this.selectedDate)

    this.doctorAppointmentsService.getAppointments(
      this.doctorDetails.id,this.selectedDate).subscribe({
        next: (data) => {
          console.log("Appointments of doctor for date: " + this.selectedDate , data);
          this.doctorAppointments = data.cookies;
          console.log(this.doctorAppointments);

          this.otherPatientBookedSlots = {};
          this.currentPatientBookedSlots = {};

          this.doctorAppointments.forEach(appointment => {
            const key = appointment.slot;
            if (appointment.patientId !== parseInt(localStorage.getItem("patientId")?? '')) {
                this.otherPatientBookedSlots[key] = true;
            } else {
                this.currentPatientBookedSlots[key] = true;
            }
        });
        console.log("Other patient booked slots:", this.otherPatientBookedSlots);
        console.log("Current patient booked slots:", this.currentPatientBookedSlots);
        },
        error: (err: HttpErrorResponse) => {
          console.log("error", err);
        }
    })

    this.patientAppointmentsService.getPatientAppointments(
      localStorage.getItem("patientId"),
      this.selectedDate,
    ).subscribe({
      next: (data: any) => {
        console.log(data.cookies);
        this.bookedWithOtherDoctor = {};
        this.patientAppointments = data.cookies;
        this.patientAppointments.forEach(patientAppointment=>{
          const key = patientAppointment.slot;
          
          console.log(this.doctorDetails.id + "," +patientAppointment.doctorId)
          if(this.doctorDetails.id !== patientAppointment.doctorId){
            this.bookedWithOtherDoctor[key] = true;
          }

          console.log("Booked other doctorslots:", this.bookedWithOtherDoctor);
        }
        )
      },
      error: (err: HttpErrorResponse) => {
        console.log("Error", err);
      }
    })
  }

  isActive(date: Date): boolean {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    const formattedDate = `${year}-${month}-${day}`;
    return this.selectedDate == formattedDate;
  }

  onSlotClick(slot: string,i:number) {
    this.selectedSlot = slot;
    console.log("property", this.otherPatientBookedSlots.hasOwnProperty(i));
    console.log("property1", this.bookedWithOtherDoctor.hasOwnProperty(i));
    console.log("SLOT CLICKED!" + this.selectedSlot)

  }

  isSlotDisabled(slot: number): boolean {
    return this.otherPatientBookedSlots.hasOwnProperty(slot) || 
    this.currentPatientBookedSlots.hasOwnProperty(slot) ||
    this.bookedWithOtherDoctor.hasOwnProperty(slot);
  }

  loadReviews() {
    console.log(this.doctorDetails.id);
    this.doctorAppointmentsService.getReviews(this.doctorDetails.id).subscribe({
      next: (data: any) => {
        this.feedback = data;
        console.log("feedback ", data);
      },
      error: (err: HttpErrorResponse) => {
        console.log("Error in fetching reviews", err);
      }
    })
  }

  isSlotBookedByCurrentPatient(slot: number){
    return this.currentPatientBookedSlots.hasOwnProperty(slot);
  }

  isSlotBookedByOtherPatient(slot: number){
    return this.otherPatientBookedSlots.hasOwnProperty(slot);
  }

  isSlotBookedWithOtherDoctor(slot: number){
    return this.bookedWithOtherDoctor.hasOwnProperty(slot);
  }

  customOptions: OwlOptions = {
    loop: false,
    mouseDrag: true,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 700,
    navText: ['<i class="fa-solid fa-caret-left fa-2x"></i>', '<i class="fa-solid fa-caret-right fa-2x"></i>'],
    responsive: {
      0: {
        items: 2
      },
      400: {
        items: 3
      },
      740: {
        items: 4
      },
      940: {
        items: 4
      }
    },
    nav: true
  }

  reviewOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 700,
    navText: ['<i class="fa-solid fa-angle-left fa-2x"></i>', '<i class="fa-solid fa-angle-right fa-2x"></i>'],
    autoplay: true, 
    autoplayTimeout: 5000, 
    autoplaySpeed: 1000,
    autoplayHoverPause: true,
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 1
      },
      740: {
        items: 1
      },
      940: {
        items: 1
      }
    },
  }
}

