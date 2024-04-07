package com.nrifintech.medicalmanagementsystem.dto;

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.enums.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    
    private Long doctorId;
    private Long patientId;
    private AppointmentStatus appStatus;
    private LocalDate appDate;
    private Long appId;
    private Integer slot;
    private String specialization;
}
