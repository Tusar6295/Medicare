package com.nrifintech.medicalmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrifintech.medicalmanagementsystem.dto.AppointmentBookingRequestDTO;
import com.nrifintech.medicalmanagementsystem.dto.AppointmentSearchRequestDTO;
import com.nrifintech.medicalmanagementsystem.dto.AppointmentResponseDTO;
import com.nrifintech.medicalmanagementsystem.model.Appointment;
import com.nrifintech.medicalmanagementsystem.service.AppointmentService;
import com.nrifintech.medicalmanagementsystem.service.FeedbackService;
import com.nrifintech.medicalmanagementsystem.service.GenerateResponseService;
import com.nrifintech.medicalmanagementsystem.service.ReportGenerationService;
import com.nrifintech.medicalmanagementsystem.utility.enums.AppointmentStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    GenerateResponseService generateResponseService;

    @Autowired
    ReportGenerationService reportGenerationService;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/book")
    public ResponseEntity<Object> bookAppointment(
            @Valid @RequestBody AppointmentBookingRequestDTO appointmentBookingRequestDTO) {
        System.out.println("book nhi ho paaa rha");

        System.out.println(appointmentBookingRequestDTO);

        System.out.println("sdsdsdsdsd");
        return generateResponseService.generateResponse(
                "Appointment successfully booked",
                HttpStatus.OK,
                appointmentService.bookAppointment(appointmentBookingRequestDTO));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelAppointment(@PathVariable Long id) throws InvalidAttributeValueException {
        return generateResponseService.generateResponse(
                "Appointment successfully cancelled",
                HttpStatus.OK,
                appointmentService.cancelAppointment(id));
    }
    
    @GetMapping("/getDoctorAppointments")
    public ResponseEntity<Object> getAppointments(
            @RequestParam Long doctorId,
            @RequestParam LocalDate appDate,
            @RequestParam AppointmentStatus appStatus) {

        return generateResponseService.generateResponse(
            "Appointment successfully fetched",
            HttpStatus.OK,
            appointmentService.findAppointments(doctorId, appDate, appStatus));
    }

    @GetMapping("/getPatientAppointments")
    public ResponseEntity<Object> getPatientAppointments(
            @RequestParam Long patientId,
            @RequestParam LocalDate appDate,
            @RequestParam AppointmentStatus appStatus) {

        return generateResponseService.generateResponse(
            "Appointment successfully fetched",
            HttpStatus.OK,
            appointmentService.findPatientAppointments(patientId, appDate, appStatus));
    }
}
