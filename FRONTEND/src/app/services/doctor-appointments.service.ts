import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';



import { Observable } from 'rxjs';
import { baseUrl } from './helper';

@Injectable({
  providedIn: 'root'
})
export class DoctorAppointmentsService {
  private baseUrl = 'http://localhost:8083/appointment';
  private patientUrl = 'http://localhost:8083/patient';
  private feedbackUrl = 'http://localhost:8083/patient';
  private searchUrl = 'http://localhost:8083/search';
 
  constructor(private http: HttpClient) { }

  getAppointmentsOfDoctor(doctorId: any): Observable<any> {
    const requestBody = {
      doctorId: doctorId,
      appointmentStatus: 'PENDING'
    };
    return this.http.post<any>(`${this.searchUrl}/searchAppointments`, requestBody);
  }

  getAppointments(doctorId: number, date: string): Observable<any> {
    const status = "PENDING";
    return this.http.get<any>(`${this.baseUrl}/getDoctorAppointments?doctorId=${doctorId}&appDate=${date}&appStatus=${status}`);
  }

  getReviewsOfDoctor(doctorId: any, page: any, size: any): Observable<any> {
    const params = new HttpParams().set('id', doctorId).set('page', page).set('size', size);
    // Make the GET request with parameters
    return this.http.get<any>(`${this.feedbackUrl}/get-feedback`, { params });
  }

  getReviewsOfDoctor1(doctorId: any): Observable<any> {
    return this.http.get<any>(`${this.feedbackUrl}/get-feedback-once?id=${doctorId}`);
  }

  getReviews(doctorId: any): Observable<any> {
    const params = new HttpParams().set('doctorId', doctorId);
    // Make the GET request with parameters
    return this.http.get<any>(`${this.searchUrl}/feedback`, { params });
  }

  getParticularDoctor(doctorId: any): Observable<any> {
    console.log("INSDIE SERVICE")
    return this.http.get<any>(`${this.patientUrl}/get-particular-doctor/${doctorId}`);
  }

  bookDoctorAppointment(doctorId: any, patientId: any, appDate: any, slot: any): Observable<any> {
    const requestBody = {
      doctorId: doctorId,
      patientId: patientId,
      appDate: appDate,
      slot: slot
    };
    return this.http.post<any>(`${this.baseUrl}/book`, requestBody);
  }

  getUpcomingAppointments(doctorId: any): Observable<any[]> {
    return this.http.post<any>(`${this.searchUrl}/searchAppointments`, { doctorId });
  }
}




